package org.yooooo.funthingsandpollution.items;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.yooooo.funthingsandpollution.Funthingsandpollution;

public class ModItems {
    public static final Item ENDERITE_SHARD = registerItem(
        "enderite_shard",
        new Item(new Item.Settings().maxDamage(Items.IRON_INGOT.getDefaultStack().getMaxDamage()+1))
    );

    private  static Item registerItem(String name, Item item) {

        return Registry.register(
                Registries.ITEM,
                Identifier.of(Funthingsandpollution.MOD_ID, name),
                item);
    }

    public static void registerItems() {
        Funthingsandpollution.LOGGER.info("Registering mod Items for: " + Funthingsandpollution.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(ModItems.ENDERITE_SHARD);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(ModItems.ENDERITE_AXE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(ModItems.ENDERITE_AXE);
        });
    }
    public static final Item ENDERITE_AXE = registerItem("enderite_axe",
            new AxeItem(EnderiteToolMaterial.INSTANCE,
                    new AxeItem.Settings().attributeModifiers(AxeItem.createAttributeModifiers(
                            EnderiteToolMaterial.INSTANCE, 6.0f, -2.0f)))
    );

    public static final Item ENDERITE_HELMET = registerItem("enderite_helmet",
            new ArmorItem(ModArmourMaterial.ENDERITE, ArmorItem.Type.HELMET, new Item.Settings()));
    public static final Item ENDERITE_CHESTPLATE = registerItem("enderite_chestplate",
            new ArmorItem(ModArmourMaterial.ENDERITE, ArmorItem.Type.CHESTPLATE, new Item.Settings()));
    public static final Item ENDERITE_LEGGINGS = registerItem("enderite_leggings",
            new ArmorItem(ModArmourMaterial.ENDERITE, ArmorItem.Type.LEGGINGS, new Item.Settings()));
    public static final Item ENDERITE_BOOTS = registerItem("enderite_boots",
            new ArmorItem(ModArmourMaterial.ENDERITE, ArmorItem.Type.BOOTS, new Item.Settings()));

    public static class EnderiteToolMaterial implements ToolMaterial {
            public static final EnderiteToolMaterial INSTANCE = new EnderiteToolMaterial();

        @Override
        public int getDurability() {
            return ToolMaterials.NETHERITE.getDurability()+120;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return ToolMaterials.NETHERITE.getMiningSpeedMultiplier();
        }

        @Override
        public float getAttackDamage() {
            return ToolMaterials.NETHERITE.getAttackDamage()+1f;
        }

        @Override
        public TagKey<Block> getInverseTag() {
            return ToolMaterials.NETHERITE.getInverseTag();
        }

        @Override
        public int getEnchantability() {
            return ToolMaterials.NETHERITE.getEnchantability();
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(ENDERITE_SHARD);
        }

        @Override
        public ToolComponent createComponent(TagKey<Block> tag) {
            return ToolMaterial.super.createComponent(tag);
        }
    }
}