package com.unrealdinnerbone.tramplestopper;

import me.sargunvohra.mcmods.autoconfig1.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stat;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TrampleStopper implements ModInitializer {

    public static final String MOD_ID = "tramplestopper";
    public static Logger LOGGER = LogManager.getLogger();
    public static Identifier stat;


    @Override
	public void onInitialize() {
        LOGGER.info("[TrampleStopper] Loading!");
        AutoConfig.register(TrampleConfig.class, JanksonConfigSerializer::new);
        stat = Registry.register(Registry.CUSTOM_STAT, "farmland_trampled", new Identifier(MOD_ID, "farmland_trampled"));
    }

    public static boolean onFarmlandTrample(World world, BlockPos blockPos, Entity entity, float height) {
        TrampleConfig trampleConfig = getConfig();
        return trampleConfig.getType().getFunction().apply(trampleConfig, entity);
    }

    public static TrampleConfig getConfig() {
        return AutoConfig.getConfigHolder(TrampleConfig.class).getConfig();
    }

}
