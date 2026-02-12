package fr.neutronstars.survival.core.network;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PacketBuffer {
    private final Queue<Runnable> queue = new ConcurrentLinkedQueue<>();

    public void add(Runnable runnable) {
        this.queue.add(runnable);
    }

    public void flush() {
        Runnable runnable;
        while ((runnable = this.queue.poll()) != null) {
            runnable.run();
        }
    }

    public void clear() {
        this.queue.clear();
    }
}
