package com.twobyone.farmingmod.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
