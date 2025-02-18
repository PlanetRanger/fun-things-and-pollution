package org.yooooo.funthingsandpollution.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;




public class EcoProgressBar implements HudRenderCallback {
    private static final Identifier FILLED_ECO=Identifier.tryParse(FunthingsandpollutionClient.MOD_ID, "images/eco_bar_full2.png");
    private static final Identifier EMPTY_ECO=Identifier.tryParse(FunthingsandpollutionClient.MOD_ID, "images/eco_bar_empty2.png");
    private static final int TEXTURE_SIZE=12;
    private static final int BAR_LVLS=5;
    private static final int X_OFFSET=3;
    private static final int X_STEP=18;
    private static final int Y_OFFSET=-54;
    private static final long LVL_TIME_SEC=3600; //5hr
    //private static final long LVL_TIME_SEC=60; //5m
    //private static final long LVL_TIME_SEC=15; //75s
    //private static final long LVL_TIME_SEC=5; //25s
    private static final long FULL_BAR_TIME_SEC=BAR_LVLS*LVL_TIME_SEC;

    private PlaytimeTicker playtimeTicker;
    public EcoProgressBar(PlaytimeTicker ticker) {
        playtimeTicker=ticker;
    }

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        int x=0;
        int y=0;
        MinecraftClient client=MinecraftClient.getInstance();
        if (client!=null) {

            int width=client.getWindow().getScaledWidth();
            int height=client.getWindow().getScaledHeight();

            x=width/2+X_OFFSET;
            y=height+Y_OFFSET;
        }

        drawContext.setShaderColor(1.0F,1.0F,1.0F,1.0F);

        int emptyLvls=lvlCount(playtimeTicker.getTotalPlaytimeInSec());


        for (int i=0; i < emptyLvls;i++) {
            drawContext.drawTexture(EMPTY_ECO, x+(i*X_STEP),y,0,0, TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
        }

        for (int i=emptyLvls; i < BAR_LVLS;i++) {
            drawContext.drawTexture(FILLED_ECO, x+(i*X_STEP),y,0,0, TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
        }
        if (emptyLvls==5) {
            disconnect(client);
        }

    }

    private void disconnect(MinecraftClient client) {
        if (client == null || client.world == null) {
            return;
        }

        client.world.disconnect();
        client.disconnect();//new SaveLevelScreen(new TranslatableText("menu.savingLevel")));

        final TitleScreen titleScreen = new TitleScreen();
        if (client.isInSingleplayer()) {
            client.setScreen(titleScreen);
        } else {
            client.setScreen(new MultiplayerScreen(titleScreen));
        }
    }

    private int lvlCount(long playtime) {
        long levels=playtime/LVL_TIME_SEC;
        return (int) Math.min(levels,BAR_LVLS);
    }
}
