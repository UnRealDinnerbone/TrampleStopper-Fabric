package com.unrealdinnerbone.tramplestopper;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TrampleStopper implements ModInitializer {


    @Override
	public void onInitialize() {
        System.out.println("Loading TrampleStopper");
	}

    public static boolean onFarmlandTrample(World world, BlockPos blockPos, Entity entity, float height) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity entityPlayer = (PlayerEntity) entity;
            for (ItemStack itemStack : entityPlayer.getItemsArmor()) {
                if (itemStack.getItem() instanceof ArmorItem) {
                    ArmorItem armorItem = (ArmorItem) itemStack.getItem();
                    if(armorItem.getSlotType() == EquipmentSlot.FEET) {
                        if (EnchantmentHelper.getLevel(Enchantments.FEATHER_FALLING, itemStack) > 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
