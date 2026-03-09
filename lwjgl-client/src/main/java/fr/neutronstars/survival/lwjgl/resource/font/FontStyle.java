package fr.neutronstars.survival.lwjgl.resource.font;

public enum FontStyle {
    THIN("thin"),
    THIN_ITALIC("thin-italic"),
    LIGHT("light"),
    LIGHT_ITALIC("light-italic"),
    MEDIUM("medium"),
    MEDIUM_ITALIC("medium-italic"),
    REGULAR("regular"),
    ITALIC("italic"),
    BOLD("bold"),
    BOLD_ITALIC("bold-italic"),
    BLACK("black"),
    BLACK_ITALIC("black-italic");

    private final String identifier;

    FontStyle(String identifier) {
        this.identifier = identifier;
    }

    public String identifier() {
        return identifier;
    }
}
