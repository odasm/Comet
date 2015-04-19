package com.cometproject.server.network.messages.outgoing.room.items.wired;

import com.cometproject.server.network.messages.composers.MessageComposer;
import com.cometproject.server.network.messages.headers.Composers;
import com.cometproject.server.network.messages.types.Composer;


public class WiredRewardMessageComposer extends MessageComposer {
    private final int reason;

    public WiredRewardMessageComposer(final int reason) {
        this.reason = reason;
    }

    @Override
    public short getId() {
        return Composers.WiredRewardMessageComposer;
    }

    @Override
    public void compose(Composer msg) {
        // 1-5 = error
        // 6-7 = success (rewardMisc, rewardBadge)
        msg.writeInt(reason);
    }
}