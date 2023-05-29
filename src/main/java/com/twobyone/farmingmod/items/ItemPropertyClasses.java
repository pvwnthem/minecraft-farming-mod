package com.twobyone.farmingmod.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

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
