package com.twobyone.farmingmod.villager;

import com.google.common.collect.ImmutableSet;
import com.ibm.icu.impl.locale.XCldrStub;
import com.twobyone.farmingmod.FarmingMod;
import com.twobyone.farmingmod.block.ModBlocks;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPE_DEFERRED_REGISTER =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, FarmingMod.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION_DEFERRED_REGISTER =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, FarmingMod.MODID);
    public static final RegistryObject<PoiType> FARMERS_WORKBENCH_POI = POI_TYPE_DEFERRED_REGISTER.register("farmers_workbench_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.FARMERS_WORKBENCH.get().getStateDefinition().getPossibleStates()),
                    1, 1));
    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockState", PoiType.class).invoke(null, FARMERS_WORKBENCH_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    public static void register (IEventBus eventBus) {
        POI_TYPE_DEFERRED_REGISTER.register(eventBus);
        VILLAGER_PROFESSION_DEFERRED_REGISTER.register(eventBus);
    }
}
