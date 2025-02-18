package org.yooooo.funthingsandpollution.items;

import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.yooooo.funthingsandpollution.Funthingsandpollution;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModArmourMaterial {
    public static final RegistryEntry<ArmorMaterial> ENDERITE = register("enderite", Map.of(
            ArmorItem.Type.HELMET, 3,
            ArmorItem.Type.CHESTPLATE, 8,
            ArmorItem.Type.LEGGINGS, 6,
            ArmorItem.Type.BOOTS, 3
    ),
    15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            ()-> Ingredient.ofItems(ModItems.ENDERITE_SHARD),
            ArmorMaterials.NETHERITE.value().toughness(),
            ArmorMaterials.NETHERITE.value().knockbackResistance(),
    false);

    public  static RegistryEntry<ArmorMaterial> register(String id, Map<ArmorItem.Type, Integer> defensepoints,
                                                         int enchantability, RegistryEntry<SoundEvent> equipSound,
                                                         Supplier<Ingredient> repairIngredient, float toughness,
                                                         float knockbackResistance, boolean dyable) {
        List<ArmorMaterial.Layer> layers =List.of(
                new ArmorMaterial.Layer(Funthingsandpollution.id(id), "" , dyable)
        );

        var material=new ArmorMaterial(defensepoints, enchantability, equipSound, repairIngredient, layers, toughness, knockbackResistance);

        material=Registry.register(Registries.ARMOR_MATERIAL, Funthingsandpollution.id(id), material);

        return RegistryEntry.of(material);
    }
    public static void load() {}

    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ENDERITE_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERITE_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERITE_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDERITE_BOOTS, Models.GENERATED);
    }
}
