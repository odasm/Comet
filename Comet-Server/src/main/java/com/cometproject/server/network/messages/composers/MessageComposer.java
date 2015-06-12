package com.cometproject.server.network.messages.composers;

import com.cometproject.api.networking.messages.IComposer;
import com.cometproject.api.networking.messages.IMessageComposer;
import com.cometproject.server.boot.Comet;
import com.cometproject.server.network.messages.types.Composer;
import com.cometproject.server.network.sessions.SessionManager;
import io.netty.buffer.ByteBuf;

public abstract class MessageComposer implements IMessageComposer {
    private boolean isCancelled = false;

    public MessageComposer() {
    }

    public final IComposer writeMessage(ByteBuf buf) {
        return this.writeMessageImpl(buf);
    }

    public final Composer writeMessageImpl(ByteBuf buffer) {
        final Composer composer = new Composer(this.getId(), buffer);

        // Do anything we need to do with the buffer.

        try {
            this.compose(composer);
        } catch (Exception e) {
            Comet.getServer().getLogger().error("Error during message composing", e);
            throw e;
        } finally {
            this.dispose();
        }

        return composer;
    }

    public abstract short getId();

    public abstract void compose(IComposer msg);

    public void dispose() {

    }
}
