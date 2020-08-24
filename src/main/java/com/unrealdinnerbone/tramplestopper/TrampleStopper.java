package com.unrealdinnerbone.tramplestopper;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TrampleStopper implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
	public void onInitialize() {
        LOGGER.info("Loading!");
    }

    public static boolean onFarmlandTrample(Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            for (ItemStack itemStack : player.getArmorSlots()) {
                if (itemStack.getItem() instanceof ArmorItem) {
                    ArmorItem armorItem = (ArmorItem) itemStack.getItem();
                    if (armorItem.getSlot() == EquipmentSlot.FEET) {
                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FALL_PROTECTION, itemStack) >= 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
