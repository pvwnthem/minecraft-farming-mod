package com.twobyone.farmingmod.block;

import com.twobyone.farmingmod.FarmingMod;
import com.twobyone.farmingmod.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCK_DEFERRED_REGISTER =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FarmingMod.MODID);


    public static final RegistryObject<Block> FARMERS_WORKBENCH = registerBlock("farmers_workbench",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2f)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCK_DEFERRED_REGISTER.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCK_DEFERRED_REGISTER.register(eventBus);
    }

}
