package fr.neutronstars.survival.server.network.listener;

import fr.neutronstars.survival.core.event.Event;
import fr.neutronstars.survival.core.event.Listener;
import fr.neutronstars.survival.server.SurvivalServer;
import fr.neutronstars.survival.server.world.entity.ServerPlayerEntity;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

public abstract class PlayerListener<T extends Event> implements Listener<T> {
    protected final SurvivalServer server;

    protected PlayerListener(SurvivalServer server) {
        this.server = server;
    }

    public void update(Channel channel, long id, String username) {
        channel.attr(AttributeKey.valueOf("identifier")).set(id);
        channel.attr(AttributeKey.valueOf("username")).set(username);
    }

    public Long identifierOf(Channel channel) {
        final AttributeKey<Long> identifier = AttributeKey.valueOf("identifier");
        return channel.hasAttr(identifier) ? channel.attr(identifier).get() : null;
    }

    public ServerPlayerEntity retrieve(Channel channel) {
        final Long id = this.identifierOf(channel);
        return id != null ? this.server.worlds().of(id) : null;
    }

    public ServerPlayerEntity retrieve(long id) {
        return this.server.worlds().of(id);
    }
}
