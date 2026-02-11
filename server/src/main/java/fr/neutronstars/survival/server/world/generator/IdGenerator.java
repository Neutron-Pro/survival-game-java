package fr.neutronstars.survival.server.world.generator;

import fr.neutronstars.survival.core.world.generator.Generator;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator implements Generator<Long> {
    private final AtomicLong atomicId = new AtomicLong();

    @Override
    public Long generate() {
        return this.atomicId.incrementAndGet();
    }
}
