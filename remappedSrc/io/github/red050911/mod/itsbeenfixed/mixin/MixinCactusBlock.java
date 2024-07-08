package io.github.implicitsaber.mod.nomoreweirdness.mixin;

import io.github.implicitsaber.mod.nomoreweirdness.ItsBeenFixed;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CactusBlock.class)
public class MixinCactusBlock {

    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    private void injectEntityCollisionHead(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo info) {
        if(entity instanceof ItemEntity && !world.getGameRules().getBoolean(ItsBeenFixed.CACTI_BREAK_ITEMS_RULE)) {
            info.cancel();
            return;
        }
        if(entity instanceof BoatEntity && !world.getGameRules().getBoolean(ItsBeenFixed.CACTI_BREAK_BOATS_RULE)) {
            info.cancel();
            return;
        }
        if(entity instanceof AbstractMinecartEntity && !world.getGameRules().getBoolean(ItsBeenFixed.CACTI_BREAK_MINECARTS_RULE)) {
            info.cancel();
        }
    }

}
