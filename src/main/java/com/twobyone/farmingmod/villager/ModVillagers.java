package com.twobyone.farmingmod.villager;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.ibm.icu.impl.locale.XCldrStub;
import com.twobyone.farmingmod.FarmingMod;
import com.twobyone.farmingmod.block.ModBlocks;
import com.twobyone.farmingmod.enchants.ModEnchantments;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPE_DEFERRED_REGISTER =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, FarmingMod.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION_DEFERRED_REGISTER =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, FarmingMod.MODID);
    public static final RegistryObject<PoiType> FARMERS_WORKBENCH_POI = POI_TYPE_DEFERRED_REGISTER.register("farmers_workbench_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.FARMERS_WORKBENCH.get().getStateDefinition().getPossibleStates()),
                    1, 1));
    public static final RegistryObject<VillagerProfession> SENIOR_FARMER = VILLAGER_PROFESSION_DEFERRED_REGISTER.register("senior_farmer",
            () -> new VillagerProfession("senior_farmer", x -> x.get() == FARMERS_WORKBENCH_POI.get(),
                    x -> x.get() == FARMERS_WORKBENCH_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_TOOLSMITH));
    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class, "registerBlockStates", PoiType.class).invoke(null, FARMERS_WORKBENCH_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    public static void register (IEventBus eventBus) {
        POI_TYPE_DEFERRED_REGISTER.register(eventBus);
        VILLAGER_PROFESSION_DEFERRED_REGISTER.register(eventBus);
    }
}