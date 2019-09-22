package com.unrealdinnerbone.tramplestopper;

import me.sargunvohra.mcmods.autoconfig1.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TrampleStopper implements ModInitializer {

    public static final String MOD_ID = "tramplestopper";
    public static final Identifier FARMLAND_TRAMPLED;
    public static final Identifier FARMLAND_SAVED;
    public static final Logger LOGGER = LogManager.getLogger();

    static {
        FARMLAND_TRAMPLED = Registry.register(Registry.CUSTOM_STAT, new Identifier(MOD_ID, "farmland_trampled"), new Identifier(MOD_ID, "farmland_trampled"));
        FARMLAND_SAVED = Registry.register(Registry.CUSTOM_STAT, new Identifier(MOD_ID, "farmland_saved"), new Identifier(MOD_ID, "farmland_saved"));
    }


    @Override
	public void onInitialize() {
        LOGGER.info("[TrampleStopper] Loading!");
        AutoConfig.register(TrampleConfig.class, JanksonConfigSerializer::new);
    }

    public static TrampleResult onFarmlandTrample(World world, BlockPos blockPos, Entity entity, float height) {
        TrampleConfig trampleConfig = getConfig();
        TrampleResult trampleResult = trampleConfig.getType().getFunction().apply(trampleConfig, entity);
        return trampleResult;
        //Todo FIX THIs
//        else {
//            if(entity instanceof PlayerEntity) {
//                PlayerEntity playerEntit = (PlayerEntity) entity;
//                playerEntit.increaseStat(TrampleStopper.FARMLAND_TRAMPLED, 1);
//            }
//        }
    }

    public static TrampleConfig getConfig() {
        return AutoConfig.getConfigHolder(TrampleConfig.class).getConfig();
    }


}
