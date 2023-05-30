package com.twobyone.farmingmod.items;

import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.twobyone.farmingmod.FarmingMod;

public class Items {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FarmingMod.MODID);

    public static final RegistryObject<Item> ENCHANTED_CARROT = ITEMS.register("enchanted_carrot",
            () -> new ItemPropertyClasses.EnchantmentGlintItem(new Item.Properties()));
    public static final RegistryObject<Item> ENCHANTED_GOLDEN_CARROT = ITEMS.register("enchanted_golden_carrot",
            () -> new ItemPropertyClasses.EnchantmentGlintItem(new Item.Properties()));

    public static final RegistryObject<Item> ENCHANTED_POTATO = ITEMS.register("enchanted_potato",
            () -> new ItemPropertyClasses.EnchantmentGlintItem(new Item.Properties()));

    public static final RegistryObject<Item> ENCHANTED_BAKED_POTATO = ITEMS.register("enchanted_baked_potato",
            () -> new ItemPropertyClasses.EnchantmentGlintItem(new Item.Properties()));
    public static final RegistryObject<HoeItem> BASIC_CARROT_HOE = ITEMS.register("basic_carrot_hoe",
            () -> new HoeItem(Tiers.BASIC, 1, 4.5f, new Item.Properties()));

    public static final RegistryObject<HoeItem> BASIC_POTATO_HOE = ITEMS.register("basic_potato_hoe",
            () -> new HoeItem(Tiers.BASIC, 1, 4.5f, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static class Tiers {
        public static final Tier BASIC = new ForgeTier(
                2,
                1500,
                4.5f,
                1,
                250,
                null,
                () -> Ingredient.EMPTY

        );
    }
}
