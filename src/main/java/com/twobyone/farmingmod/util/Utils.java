package com.twobyone.farmingmod.util;

import net.minecraft.world.entity.npc.VillagerTrades;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Utils {
    public static void shuffleList(List<VillagerTrades.ItemListing> list) {
        Random random = new Random();
        for (int i = list.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Collections.swap(list, i, j);
        }
    }







}
