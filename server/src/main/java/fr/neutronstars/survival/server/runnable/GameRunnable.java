package fr.neutronstars.survival.server.runnable;

import fr.neutronstars.survival.server.SurvivalServer;

public class GameRunnable implements Runnable {
    private final SurvivalServer server;

    public GameRunnable(SurvivalServer server) {
        this.server = server;
    }

    @Override
    public void run() {
        this.server.requests().handle();

        this.server.worlds().update();
        this.server.worlds().players().forEach(player -> {
            player.controls().update();
            this.server.snapshotService().send(
                this.server,
                this.server.snapshotService().createOf(player)
            );
        });
    }
}
