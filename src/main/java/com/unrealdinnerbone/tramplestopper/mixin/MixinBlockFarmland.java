package com.unrealdinnerbone.tramplestopper.mixin;

import com.unrealdinnerbone.tramplestopper.TrampleStopper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmBlock.class)
public class MixinBlockFarmland  {

    @Inject(method = "fallOn(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;F)V", at = @At("HEAD"), cancellable = true)
    public void onFallen(Level level, BlockPos blockPos, Entity entity, float height, CallbackInfo callbackInfo) {
        if(TrampleStopper.onFarmlandTrample(entity)) {
            callbackInfo.cancel();
        }
    }
}