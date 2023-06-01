package com.twobyone.farmingmod.items;


import net.minecraft.world.item.*;


public class ItemPropertyClasses {
    public static class EnchantmentGlintItem extends Item {

        public EnchantmentGlintItem(Properties properties) {
            super(properties);
        }

        @Override
        public boolean isFoil(ItemStack stack) {
            return true;
        }
    }
}
