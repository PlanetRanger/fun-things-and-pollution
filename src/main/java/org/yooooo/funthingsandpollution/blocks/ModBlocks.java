package org.yooooo.funthingsandpollution.blocks;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.PickaxeItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.yooooo.funthingsandpollution.Funthingsandpollution;

public class ModBlocks {
    public static final Block ENDERITE_ORE = registerBlock(
        "enderite_ore",
         new Block(AbstractBlock.Settings.create()
                 .strength(Blocks.DEEPSLATE_DIAMOND_ORE.getHardness(), Blocks.BEDROCK.getBlastResistance())
                 .requiresTool()
                 .sounds(BlockSoundGroup.ANCIENT_DEBRIS))
    );
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK,Identifier.of(Funthingsandpollution.MOD_ID, name),block);
    }
    private  static void registerBlockItem(String name, Block block) {

        Registry.register(
                Registries.ITEM,
                Identifier.of(Funthingsandpollution.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerBlocks() {
        Funthingsandpollution.LOGGER.info("Registering mod blocks for: " + Funthingsandpollution.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(ModBlocks.ENDERITE_ORE);
        });
    }

}
