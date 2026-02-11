package fr.neutronstars.survival.client;

import fr.neutronstars.survival.client.graphics.texture.TexturePacks;
import fr.neutronstars.survival.client.world.ClientContext;
import fr.neutronstars.survival.client.world.block.BlockRegistry;
import fr.neutronstars.survival.client.world.entity.EntityContextRegistry;
import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.client.graphics.Display;
import fr.neutronstars.survival.client.level.Levels;
import fr.neutronstars.survival.client.netty.NettyClient;
import fr.neutronstars.survival.core.SurvivalCore;
import fr.neutronstars.survival.core.utils.ParameterLauncher;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.entity.EntityRegistry;
import fr.neutronstars.survival.core.world.entity.PlayerEntity;
import org.slf4j.Logger;

public class SurvivalClient extends SurvivalCore {
    private final Display display = new Display(this);
    private final Levels levels = new Levels(this);
    private final BlockRegistry blockRegistry = new BlockRegistry();
    private final EntityContextRegistry entityContextRegistry = new EntityContextRegistry();
    private final EntityRegistry<ClientContext> entityRegistry = new EntityRegistry<>();
    private final TexturePacks texturePacks = new TexturePacks();

    private NettyClient nettyClient;

    private World<ClientContext> world;
    private PlayerEntity<ClientContext> selfPlayer;

    public SurvivalClient(
        Logger logger,
        ParameterLauncher parameterLauncher,
        Injector injector
    ) {
        super(logger, parameterLauncher, injector);
    }

    public Display display() {
        return this.display;
    }

    public Levels levels() {
        return this.levels;
    }

    public NettyClient netty() {
        return this.nettyClient;
    }

    public BlockRegistry blockRegistry() {
        return this.blockRegistry;
    }

    public EntityContextRegistry entityContextRegistry() {
        return this.entityContextRegistry;
    }

    public TexturePacks texturePacks() {
        return this.texturePacks;
    }

    public void set(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
    }

    public EntityRegistry<ClientContext> entityRegistry() {
        return this.entityRegistry;
    }

    public World<ClientContext> world() {
        return this.world;
    }

    public void setWorld(World<ClientContext> world) {
        this.world = world;
    }

    public PlayerEntity<ClientContext> selfPlayer() {
        return this.selfPlayer;
    }

    public void setSelfPlayer(PlayerEntity<ClientContext> selfPlayer) {
        this.selfPlayer = selfPlayer;
    }

    public void shutdown(boolean closeDisplay) {
        if (closeDisplay) {
            this.display.close();
        }

        if (this.nettyClient != null) {
            this.nettyClient.stop();
        }
    }
}
