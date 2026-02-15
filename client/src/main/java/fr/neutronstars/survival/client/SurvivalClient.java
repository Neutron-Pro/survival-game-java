package fr.neutronstars.survival.client;

import fr.neutronstars.survival.client.controls.ControlMapping;
import fr.neutronstars.survival.client.graphics.texture.TexturePacks;
import fr.neutronstars.survival.client.world.ClientBlockContextGenerator;
import fr.neutronstars.survival.client.world.ClientContext;
import fr.neutronstars.survival.client.world.block.BlockRegistry;
import fr.neutronstars.survival.client.world.entity.EntityContextRegistry;
import fr.neutronstars.survival.core.injector.api.injection.Injector;
import fr.neutronstars.survival.client.graphics.Display;
import fr.neutronstars.survival.client.level.Levels;
import fr.neutronstars.survival.client.network.NetworkClient;
import fr.neutronstars.survival.core.SurvivalCore;
import fr.neutronstars.survival.core.utils.ParameterLauncher;
import fr.neutronstars.survival.core.world.World;
import fr.neutronstars.survival.core.world.entity.EntityRegistry;
import fr.neutronstars.survival.core.world.entity.PlayerEntity;
import fr.neutronstars.survival.core.world.generator.BlockContextGenerator;
import org.slf4j.Logger;

public class SurvivalClient extends SurvivalCore {
    private final Display display = new Display(this);
    private final Levels levels = new Levels(this);
    private final BlockRegistry blockRegistry = new BlockRegistry();
    private final EntityContextRegistry entityContextRegistry = new EntityContextRegistry();
    private final ClientBlockContextGenerator blockContextGenerator;
    private final EntityRegistry<ClientContext> entityRegistry = new EntityRegistry<>();
    private final TexturePacks texturePacks = new TexturePacks();
    private final ControlMapping controlMapping = new ControlMapping();

    private NetworkClient networkClient;

    private World world;
    private PlayerEntity selfPlayer;

    public SurvivalClient(
        Logger logger,
        ParameterLauncher parameterLauncher,
        Injector injector
    ) {
        super(logger, parameterLauncher, injector);
        this.blockContextGenerator = new ClientBlockContextGenerator(this);
    }

    public Display display() {
        return this.display;
    }

    public Levels levels() {
        return this.levels;
    }

    public NetworkClient netty() {
        return this.networkClient;
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

    public ControlMapping controlMapping() {
        return this.controlMapping;
    }

    public void set(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public EntityRegistry<ClientContext> entityRegistry() {
        return this.entityRegistry;
    }

    public World world() {
        return this.world;
    }

    @Override
    public BlockContextGenerator blockContextGenerator() {
        return this.blockContextGenerator;
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

    public void shutdown(boolean closeDisplay) {
        if (closeDisplay) {
            this.display.close();
        }

        if (this.networkClient != null) {
            this.networkClient.stop();
        }
    }
}
