package com.twobyone.farmingmod;

import com.mojang.logging.LogUtils;
import com.twobyone.farmingmod.block.ModBlocks;
import com.twobyone.farmingmod.enchants.ModEnchantments;
import com.twobyone.farmingmod.creativetab.ModCreativeModeTab;
import com.twobyone.farmingmod.items.ModItems;
import com.twobyone.farmingmod.mixin.PoiTypesInvoker;
import com.twobyone.farmingmod.villager.ModVillagers;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FarmingMod.MODID)
public class FarmingMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "farmingmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace


    public FarmingMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEnchantments.register(modEventBus);

        ModVillagers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);



        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            PoiTypesInvoker.invokeGetBlockStates(ModBlocks.FARMERS_WORKBENCH.get()).forEach((state) -> PoiTypesInvoker.getTypeByState().put(state, ForgeRegistries.POI_TYPES.getHolder(ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, new ResourceLocation(FarmingMod.MODID, "farmers_workbench"))).get()));
        });
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event)
    {
        if (event.getTab() == ModCreativeModeTab.ITEMS_TAB) {
            event.accept(ModItems.ENCHANTED_CARROT);
            event.accept(ModItems.ENCHANTED_GOLDEN_CARROT);
            event.accept(ModItems.ENCHANTED_POTATO);
            event.accept(ModItems.ENCHANTED_BAKED_POTATO);
        }
        if (event.getTab() == ModCreativeModeTab.TOOLS_TAB) {
            event.accept(ModItems.BASIC_CARROT_HOE);
            event.accept(ModItems.BASIC_POTATO_HOE);
        }
        if (event.getTab() == ModCreativeModeTab.BLOCKS_TAB) {
            event.accept(ModBlocks.FARMERS_WORKBENCH);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
