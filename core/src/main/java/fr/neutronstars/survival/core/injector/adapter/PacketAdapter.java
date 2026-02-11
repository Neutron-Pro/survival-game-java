package fr.neutronstars.survival.core.injector.adapter;

import fr.neutronstars.survival.core.injector.api.injection.adapter.ClassAdapter;
import fr.neutronstars.survival.core.SurvivalCore;
import fr.neutronstars.survival.core.netty.PlayInPacket;

public class PacketAdapter implements ClassAdapter<PlayInPacket> {
    private final SurvivalCore core;

    public PacketAdapter(SurvivalCore core) {
        this.core = core;
    }

    @Override
    public Class<PlayInPacket> type() {
        return PlayInPacket.class;
    }

    @Override
    public void accept(Class<? extends PlayInPacket> clazz) {
        this.core.packets().register(clazz);
    }
}
