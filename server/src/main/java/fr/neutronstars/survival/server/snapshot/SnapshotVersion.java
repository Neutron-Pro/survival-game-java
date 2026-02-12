package fr.neutronstars.survival.server.snapshot;

public class SnapshotVersion {
    private final long identifier;
    private long version;

    public SnapshotVersion(long identifier) {
        this.identifier = identifier;
    }

    public long identifier() {
        return this.identifier;
    }

    public long of() {
        return this.version;
    }

    public void increment() {
        this.version++;
    }
}
