package com.unrealdinnerbone.tramplestopper.mixin;

import com.unrealdinnerbone.tramplestopper.TrampleStopper;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmlandBlock.class)
public class MixinBlockFarmland  {

    @Inject(method = "onLandedUpon(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;F)V", at = @At("HEAD"), cancellable = true)
    public void onFallen(World world, BlockPos blockPos, Entity entity, float height, CallbackInfo callbackInfo) {
        if(TrampleStopper.onFarmlandTrample(world, blockPos, entity, height)) {
            callbackInfo.cancel();
        }
    }
}