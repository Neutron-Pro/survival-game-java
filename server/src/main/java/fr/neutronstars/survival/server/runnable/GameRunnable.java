package fr.neutronstars.survival.server.runnable;

import fr.neutronstars.survival.server.SurvivalServer;

public class GameRunnable implements Runnable {
    private final SurvivalServer server;

    public GameRunnable(SurvivalServer server) {
        this.server = server;
    }

    @Override
    public void run() {
        server.packets().buffer().flush();

        this.server.worlds().update();
        this.server.worlds().packetSynchronized().updateEntities();
        this.server.worlds().players().forEach(player -> player.controls().update());
    }
}
