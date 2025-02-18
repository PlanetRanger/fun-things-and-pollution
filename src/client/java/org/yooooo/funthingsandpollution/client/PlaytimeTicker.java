package org.yooooo.funthingsandpollution.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class PlaytimeTicker implements ClientTickEvents.EndTick {
    private long totalPlaytimeMs=0;
    private long lastTickTimeMs=0;

    @Override
    public void onEndTick(MinecraftClient client) {

        if (client!=null) {

            if ( client.isIntegratedServerRunning() ||
                    client.isConnectedToLocalServer() ||
                    client.getCurrentServerEntry()!=null
            ) {
                long now=System.currentTimeMillis();
                if (lastTickTimeMs!=0) {
                    totalPlaytimeMs+=now-lastTickTimeMs;
                }
                lastTickTimeMs=now;
            } else {
                lastTickTimeMs=0;
            }
        }
    }

    public long getTotalPlaytimeInSec() {
        return totalPlaytimeMs/1000;
    }
}
