package org.yooooo.funthingsandpollution;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yooooo.funthingsandpollution.blocks.ModBlocks;
import org.yooooo.funthingsandpollution.items.ModItems;
import org.yooooo.funthingsandpollution.world.gen.ModWorldGen;

public class Funthingsandpollution implements ModInitializer {
    public static final String MOD_ID= "fun-things-and-pollution";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info("Fun things and pollution mod has started");
        ModBlocks.registerBlocks();
        ModItems.registerItems();
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);



    }
}
