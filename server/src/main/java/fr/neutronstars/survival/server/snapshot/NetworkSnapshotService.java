package fr.neutronstars.survival.server.snapshot;

import fr.neutronstars.survival.core.network.PlayerConnection;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.network.packet.out.SnapshotPlayOutPacket;

public class NetworkSnapshotService extends AbstractSnapshotService {

    @Override
    public void send(SurvivalServer server, Snapshot snapshot) {
        if (snapshot.isEmpty()) {
            return;
        }
        final PlayerConnection connection = server.network().playerOf(snapshot.owner().id());

        if (connection != null) {
            connection.send(new SnapshotPlayOutPacket(snapshot));
        }

        snapshot.synchronizedSnapshot().update(snapshot);
    }
}
