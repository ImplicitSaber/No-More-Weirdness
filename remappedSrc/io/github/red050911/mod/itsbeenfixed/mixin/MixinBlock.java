package io.github.red050911.mod.itsbeenfixed.mixin;

import io.github.red050911.mod.itsbeenfixed.ItsBeenFixed;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class MixinBlock {

    @Inject(at = @At("HEAD"), method = "onLandedUpon", cancellable = true)
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo ci) {
        if(state.getBlock() instanceof LeveledCauldronBlock && state.getBlock().equals(Blocks.WATER_CAULDRON) && world.getGameRules().getBoolean(ItsBeenFixed.WATER_CAULDRONS_BREAK_FALL)) {
            ci.cancel();
        }
    }
}
