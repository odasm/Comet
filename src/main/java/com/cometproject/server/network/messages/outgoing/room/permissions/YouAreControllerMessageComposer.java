package com.cometproject.server.network.messages.outgoing.room.permissions;

import com.cometproject.server.network.messages.headers.Composers;
import com.cometproject.server.network.messages.types.Composer;


public class YouAreControllerMessageComposer {
    public static Composer compose() {
        return new Composer(Composers.YouAreControllerMessageComposer);
    }
}
