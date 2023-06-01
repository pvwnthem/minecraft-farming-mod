package com.twobyone.farmingmod.events;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.twobyone.farmingmod.enchants.ModEnchantments;
import com.twobyone.farmingmod.FarmingMod;
import com.twobyone.farmingmod.items.ModItems;
import com.twobyone.farmingmod.util.Utils;
import com.twobyone.farmingmod.villager.ModVillagers;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FarmingMod.MODID)
public class ModEvents {

    public static boolean checkRandomChance(int n) {
        Random random = new Random();
        int randomNumber = random.nextInt(n);

        return randomNumber == 0;
    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == ModVillagers.SENIOR_FARMER.get()) {
            //Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            //ItemStack book = new ItemStack(net.minecraft.world.item.Items.ENCHANTED_BOOK);
            //EnchantmentInstance enchantment = new EnchantmentInstance(ModEnchantments.HARVESTING.get(), 3);
            //EnchantmentHelper.setEnchantments(new Map(enchantment, 1), book);
            //System.out.println(book.getAllEnchantments().values());

            //VillagerTrades.ItemListing trade = new Trade(net.minecraft.world.item.Items.EMERALD, 12, book.getItem().asItem(), 1, 2, 20);

            //List<VillagerTrades.ItemListing> list = event.getTrades().get(1);
            //list.add(trade);
            //event.getTrades().put(1, list);

            ItemStack ench_carrot = new ItemStack(ModItems.ENCHANTED_CARROT.get());
            ItemStack ench_golden_carrot = new ItemStack(ModItems.ENCHANTED_GOLDEN_CARROT.get());
            ItemStack ench_potato = new ItemStack(ModItems.ENCHANTED_POTATO.get());
            ItemStack ench_baked_potato = new ItemStack(ModItems.ENCHANTED_BAKED_POTATO.get());

            VillagerTrades.ItemListing buy_ench_carrot = new Trade(net.minecraft.world.item.Items.EMERALD, 2, ench_carrot.getItem(), 1, 12, 2);
            VillagerTrades.ItemListing sell_ench_carrot = new Trade(ench_carrot.getItem(), 1, net.minecraft.world.item.Items.EMERALD, 1, 12, 2);

            VillagerTrades.ItemListing buy_ench_golden_carrot = new Trade(net.minecraft.world.item.Items.EMERALD, 16, ench_golden_carrot.getItem(), 1, 12, 4);
            VillagerTrades.ItemListing sell_ench_golden_carrot = new Trade(ench_golden_carrot.getItem(), 1, net.minecraft.world.item.Items.EMERALD, 8, 12, 4);

            VillagerTrades.ItemListing buy_ench_potato = new Trade(net.minecraft.world.item.Items.EMERALD, 2, ench_potato.getItem(), 1, 12, 2);
            VillagerTrades.ItemListing sell_ench_potato = new Trade(ench_potato.getItem(), 1, net.minecraft.world.item.Items.EMERALD, 1, 12, 2);

            VillagerTrades.ItemListing buy_ench_baked_potato = new Trade(net.minecraft.world.item.Items.EMERALD, 16, ench_baked_potato.getItem(), 1, 12, 4);
            VillagerTrades.ItemListing sell_ench_baked_potato  = new Trade(ench_baked_potato.getItem(), 1, net.minecraft.world.item.Items.EMERALD, 8, 12, 4);


            List<VillagerTrades.ItemListing> list = event.getTrades().get(1);
            list.add(buy_ench_carrot);
            list.add(buy_ench_potato);
            list.add(sell_ench_carrot);
            list.add(sell_ench_potato);
            Utils.shuffleList(list);
            event.getTrades().put(1, list);

            List<VillagerTrades.ItemListing> list2 = event.getTrades().get(2);
            list2.add(buy_ench_golden_carrot);
            list2.add(sell_ench_golden_carrot);
            list2.add(buy_ench_baked_potato);
            list2.add(sell_ench_baked_potato);
            Utils.shuffleList(list2);
            event.getTrades().put(2, list2);

        }
    }

    static class Trade implements VillagerTrades.ItemListing {
        private final Item buyingItem;
        private final Item sellingItem;
        private final int buyingAmount;
        private final int sellingAmount;
        private final int maxUses;
        private final int givenExp;
        private final float priceMultiplier;

        public Trade(ItemLike buyingItem, int buyingAmount, ItemLike sellingItem, int sellingAmount, int maxUses,
                     int givenExp) {
            this.buyingItem = buyingItem.asItem();
            this.buyingAmount = buyingAmount;
            this.sellingItem = sellingItem.asItem();
            this.sellingAmount = sellingAmount;
            this.maxUses = maxUses;
            this.givenExp = givenExp;
            this.priceMultiplier = -0.1F;
        }

        public MerchantOffer getOffer(Entity entity, RandomSource random) {
            return new MerchantOffer(new ItemStack(this.buyingItem, this.buyingAmount),
                    new ItemStack(sellingItem, sellingAmount), maxUses, givenExp, priceMultiplier);
        }
    }

    static class ItemsForEmeraldsTrade implements VillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForEmeraldsTrade(Block p_i50528_1_, int p_i50528_2_, int p_i50528_3_, int p_i50528_4_,
                                     int p_i50528_5_) {
            this(new ItemStack(p_i50528_1_), p_i50528_2_, p_i50528_3_, p_i50528_4_, p_i50528_5_);
        }

        public ItemsForEmeraldsTrade(Item p_i50529_1_, int p_i50529_2_, int p_i50529_3_, int p_i50529_4_) {
            this(new ItemStack(p_i50529_1_), p_i50529_2_, p_i50529_3_, 12, p_i50529_4_);
        }

        public ItemsForEmeraldsTrade(Item p_i50530_1_, int p_i50530_2_, int p_i50530_3_, int p_i50530_4_,
                                     int p_i50530_5_) {
            this(new ItemStack(p_i50530_1_), p_i50530_2_, p_i50530_3_, p_i50530_4_, p_i50530_5_);
        }

        public ItemsForEmeraldsTrade(ItemStack p_i50531_1_, int p_i50531_2_, int p_i50531_3_, int p_i50531_4_,
                                     int p_i50531_5_) {
            this(p_i50531_1_, p_i50531_2_, p_i50531_3_, p_i50531_4_, p_i50531_5_, 0.05F);
        }

        public ItemsForEmeraldsTrade(ItemStack p_i50532_1_, int p_i50532_2_, int p_i50532_3_, int p_i50532_4_,
                                     int p_i50532_5_, float p_i50532_6_) {
            this.itemStack = p_i50532_1_;
            this.emeraldCost = p_i50532_2_;
            this.numberOfItems = p_i50532_3_;
            this.maxUses = p_i50532_4_;
            this.villagerXp = p_i50532_5_;
            this.priceMultiplier = p_i50532_6_;
        }

        public MerchantOffer getOffer(Entity p_221182_1_, RandomSource p_221182_2_) {
            return new MerchantOffer(new ItemStack(net.minecraft.world.item.Items.EMERALD, this.emeraldCost),
                    new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp,
                    this.priceMultiplier);
        }
    }



    public static void onCropBreak(String tier, BlockEvent.BreakEvent event, ItemStack item) {
        int value = tier.equals("basic") ? 1 : 5;
        int bonus = 0;
        if (event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getEnchantmentLevel(ModEnchantments.HARVESTING.get()) != 0) {
            bonus += event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getEnchantmentLevel(ModEnchantments.HARVESTING.get());

        }
        item.setCount(value);

        if (checkRandomChance(50 - bonus)) {
            if(event.getPlayer().getInventory().getFreeSlot() != -1) {
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
                    onCropBreak(tier, event, new ItemStack(ModItems.ENCHANTED_CARROT.get()));

                }
                if(helditemname.contains("Wheat") && blockname.equals("Wheat Crops")) {
                    // Items.ENCHANTED_WHEAT.get() when i add wheat
                    //onCropBreak(tier, event, new ItemStack(Items.ENCHANTED_CARROT.get()));
                }
                if (helditemname.contains("Potato") && blockname.equals("Potatoes")) {

                    onCropBreak(tier, event, new ItemStack(ModItems.ENCHANTED_POTATO.get()));
                }
            }


        }
    }

}

