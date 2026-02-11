package fr.neutronstars.survival.core.injector.core.injection.scanner;

import fr.neutronstars.survival.core.injector.api.annotation.Inject;
import fr.neutronstars.survival.core.injector.api.annotation.Provider;
import fr.neutronstars.survival.core.injector.api.exception.InjectorScannerException;
import fr.neutronstars.survival.core.injector.api.exception.ScopeNotFoundException;
import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.core.injector.api.scope.Scope;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarFile;

class ScannerResolver {
    private final Injector injector;

    protected ScannerResolver(Injector injector) {
        this.injector = injector;
    }

    protected Set<Class<?>> classes(ClassLoader classLoader, String packageRoot) {
        final Set<Class<?>> classes = new HashSet<>();
        final String path = packageRoot.replace('.', '/');

        try {
            final Enumeration<URL> resources = classLoader.getResources(path);

            while (resources.hasMoreElements()) {
                final URL resource = resources.nextElement();
                final String protocol = resource.getProtocol();

                if ("file".equals(protocol)) {
                    final File directory = new File(resource.getFile());
                    if (directory.exists()) {
                        this.findClassesInDirectory(classLoader, packageRoot, directory, classes);
                    }
                } else if ("jar".equals(protocol)) {
                    final String jarPath = resource.getPath()
                        .substring(5, resource.getPath().indexOf("!"));
                    try (JarFile jarFile = new JarFile(URLDecoder.decode(jarPath, "UTF-8"))) {
                        this.findClassesInJar(classLoader, packageRoot, jarFile, classes);
                    }
                }
            }
        } catch (IOException e) {
            throw new InjectorScannerException("Error scanning classes for package " + packageRoot, e);
        }

        return classes;
    }

    protected Scope scope(Class<?> clazz) {
        for (final Annotation annotation : clazz.getAnnotations()) {
            final Class<? extends Annotation> annotationClazz = annotation.annotationType();
            if (annotationClazz.isAnnotationPresent(fr.neutronstars.survival.core.injector.api.annotation.Scope.class)) {
                final Scope scope = this.injector.service().scopes().of(annotationClazz);
                if (scope == null) {
                    throw new ScopeNotFoundException(
                        String.format(
                            "Scope with annotation <%s> not found !",
                            annotationClazz.getName()
                        )
                    );
                }
                return scope;
            }
        }
        return this.injector.configuration().scope();
    }

    protected Class<?> type(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Provider.class)) {
            return clazz.getAnnotation(Provider.class).value();
        }
        return clazz;
    }

    private void findClassesInDirectory(
        ClassLoader classLoader,
        String packageRoot,
        File directory,
        Set<Class<?>> classes
    ) {
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                this.findClassesInDirectory(
                    classLoader,
                    packageRoot + "." + file.getName(),
                    file,
                    classes
                );
            } else if (file.getName().endsWith(".class")) {
                this.addClass(
                    classes,
                    packageRoot + '.' + file.getName().substring(0, file.getName().length() - 6),
                    classLoader
                );
            }
        }
    }

    private void findClassesInJar(
        ClassLoader classLoader,
        String packageRoot,
        JarFile jarFile,
        Set<Class<?>> classes
    ) {
        final String path = packageRoot.replace('.', '/');
        jarFile.stream()
            .filter(entry -> entry.getName().startsWith(path) && entry.getName().endsWith(".class"))
            .forEach(entry -> this.addClass(
                classes,
                entry.getName().replace('/', '.').substring(0, entry.getName().length() - 6),
                classLoader
            ));
    }

    private void addClass(Set<Class<?>> classes, String className, ClassLoader classLoader) {
        try {
            final Class<?> clazz = Class.forName(className, false, classLoader);
            if (clazz.isAnnotationPresent(Inject.class)) {
                final Inject inject = clazz.getAnnotation(Inject.class);
                if (inject.value().equalsIgnoreCase(this.injector.configuration().name())) {
                    classes.add(clazz);
                }
            }
        } catch (ClassNotFoundException ignored) {}
    }
}
