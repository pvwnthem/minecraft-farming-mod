package com.twobyone.farmingmod.events;
import java.util.Random;

import com.twobyone.farmingmod.enchants.ModEnchantments;
import com.twobyone.farmingmod.FarmingMod;
import com.twobyone.farmingmod.items.Items;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FarmingMod.MODID)
public class ModEvents {

    public static boolean checkRandomChance(int n) {
        Random random = new Random();
        int randomNumber = random.nextInt(n);

        return randomNumber == 0;
    }






    public static void onCropBreak(String tier, BlockEvent.BreakEvent event, ItemStack item) {
        int value = tier.equals("basic") ? 1 : 5;
        if (event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getEnchantmentLevel(ModEnchantments.HARVESTING.get()) != 0) {
            value += event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getEnchantmentLevel(ModEnchantments.HARVESTING.get());

        }
        item.setCount(value);

        if (checkRandomChance(5)) {
            if(event.getPlayer().getInventory().isEmpty()) {
                event.getPlayer().getInventory().add(item);
                event.getPlayer().sendSystemMessage(Component.literal("Congratulations! An enchanted crop has been sent to your inventory thanks to your hoe!."));
            } else {
                event.getPlayer().sendSystemMessage(Component.literal("You were awarded a drop but your inventory is full, please clear up some space!"));
            }


        }


    }
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockState state = event.getState();
        if (state.getBlock() instanceof CropBlock) {
            String helditemname = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getDisplayName().getString();
            String blockname = state.getBlock().getName().getString();
            if (helditemname.contains("Hoe")) {
                String tier = helditemname.contains("Basic") ? "basic" : "advanced";
                if(helditemname.contains("Carrot") && blockname.equals("Carrots")) {
                    onCropBreak(tier, event, new ItemStack(Items.ENCHANTED_CARROT.get()));

                }
                if(helditemname.contains("Wheat") && blockname.equals("Wheat Crops")) {
                    // Items.ENCHANTED_WHEAT.get() when i add wheat
                    //onCropBreak(tier, event, new ItemStack(Items.ENCHANTED_CARROT.get()));
                }
                if (helditemname.contains("Potato") && blockname.equals("Potatoes")) {
                    // Items.ENCHANTED_POTATO.get() when i add potatoes
                    //onCropBreak(tier, event);
                }
            }


        }
    }

}

