package com.twobyone.farmingmod.creativetab;

import com.twobyone.farmingmod.FarmingMod;
import com.twobyone.farmingmod.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import com.twobyone.farmingmod.items.Items;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FarmingMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static CreativeModeTab TOOLS_TAB;
    public static CreativeModeTab ITEMS_TAB;
    public static CreativeModeTab BLOCKS_TAB;
    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        BLOCKS_TAB = event.registerCreativeModeTab(new ResourceLocation(FarmingMod.MODID, "blocks_tab"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.FARMERS_WORKBENCH.get())).title(
                        Component.translatable("creativemode.blocks_tab")
                ));
        ITEMS_TAB = event.registerCreativeModeTab(new ResourceLocation(FarmingMod.MODID, "farming_tab"),
                builder -> builder.icon(() -> new ItemStack(Items.ENCHANTED_CARROT.get())).title(
                        Component.translatable("creativemode.farming_tab")
                ));
        TOOLS_TAB = event.registerCreativeModeTab(new ResourceLocation(FarmingMod.MODID, "tools_tab"),
                builder -> builder.icon(() -> new ItemStack(Items.BASIC_CARROT_HOE.get())).title(
                        Component.translatable("creativemode.tools_tab")
                ));
    }
}
