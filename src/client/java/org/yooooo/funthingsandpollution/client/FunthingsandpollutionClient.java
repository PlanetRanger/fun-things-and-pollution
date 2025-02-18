package org.yooooo.funthingsandpollution.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunthingsandpollutionClient implements ClientModInitializer {
    public static final String MOD_ID= "fun-things-and-pollution";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitializeClient() {
        LOGGER.info("Fun things and pollution mod (client) has started");
        PlaytimeTicker playtimeTicker=new PlaytimeTicker();
        ClientTickEvents.END_CLIENT_TICK.register(playtimeTicker);
        HudRenderCallback.EVENT.register(new EcoProgressBar(playtimeTicker));
    }

}
