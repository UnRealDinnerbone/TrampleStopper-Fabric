package com.unrealdinnerbone.tramplestopper;

import me.sargunvohra.mcmods.autoconfig1.ConfigData;
import me.sargunvohra.mcmods.autoconfig1.annotation.Config;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

import java.util.function.BiFunction;

@Config(name = "tramplestopper")
public class TrampleConfig implements ConfigData
{
    private Type type = Type.FEATHER_FALLING;

    private int featherFallingLevel = 1;

    public int getFeatherFallingLevel() {
        return featherFallingLevel;
    }

    public Type getType() {
        return type;
    }

    public static enum Type {
        FEATHER_FALLING((trampleConfig, entity) -> {
            if (entity instanceof PlayerEntity) {
                PlayerEntity entityPlayer = (PlayerEntity) entity;
                for (ItemStack itemStack : entityPlayer.getArmorItems()) {
                    if (itemStack.getItem() instanceof ArmorItem) {
                        ArmorItem armorItem = (ArmorItem) itemStack.getItem();
                        if(armorItem.getSlotType() == EquipmentSlot.FEET) {
                            if (EnchantmentHelper.getLevel(Enchantments.FEATHER_FALLING, itemStack) >= trampleConfig.getFeatherFallingLevel()) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }),
        NEVER((trampleConfig, entity) -> false),
        ALWAYS((trampleConfig, entity) -> true);

        private final BiFunction<TrampleConfig, Entity, Boolean> function;

        Type(BiFunction<TrampleConfig, Entity, Boolean> function) {
            this.function = function;
        }

        public BiFunction<TrampleConfig, Entity, Boolean> getFunction() {
            return function;
        }
    }
}