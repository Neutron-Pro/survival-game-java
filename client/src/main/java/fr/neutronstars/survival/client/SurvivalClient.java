package fr.neutronstars.survival.client;

import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.client.network.NetworkClient;
import fr.neutronstars.survival.core.SurvivalCore;
import fr.neutronstars.survival.core.utils.ParameterLauncher;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.entity.PlayerEntity;
import org.slf4j.Logger;

public class SurvivalClient extends SurvivalCore {

    private NetworkClient networkClient;

    private World world;
    private PlayerEntity selfPlayer;

    public SurvivalClient(
        Logger logger,
        ParameterLauncher parameterLauncher,
        Injector injector
    ) {
        super(logger, parameterLauncher, injector);
    }

    public NetworkClient netty() {
        return this.networkClient;
    }

    public void set(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public World world() {
        return this.world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public PlayerEntity selfPlayer() {
        return this.selfPlayer;
    }

    public void setSelfPlayer(PlayerEntity selfPlayer) {
        this.selfPlayer = selfPlayer;
    }

    public void shutdown() {
        if (this.networkClient != null) {
            this.networkClient.stop();
        }
    }
}
