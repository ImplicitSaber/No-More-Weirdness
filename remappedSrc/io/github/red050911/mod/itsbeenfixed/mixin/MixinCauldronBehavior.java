package io.github.implicitsaber.mod.nomoreweirdness.mixin;

import io.github.implicitsaber.mod.nomoreweirdness.ItsBeenFixed;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CauldronBehavior.class)
public interface MixinCauldronBehavior {

    @Inject(at = @At("RETURN"), method = "fillCauldron")
    private static void fillCauldron(World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack, BlockState state, SoundEvent soundEvent, CallbackInfoReturnable<ActionResult> cir) {
        if(!world.isClient) {
            if(world.getDimension().isUltrawarm() && state.getBlock().equals(Blocks.WATER_CAULDRON) && !world.getGameRules().getBoolean(ItsBeenFixed.CAULDRONS_ALLOW_NETHER_WATER)) {
                world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 1.0f);
                int x = pos.getX();
                int y = pos.getY() + 1;
                int z = pos.getZ();
                for (int l = 0; l < 8; ++l) {
                    world.addParticle(ParticleTypes.LARGE_SMOKE, (double)x + Math.random(), (double)y + Math.random(), (double)z + Math.random(), 0.0, 0.0, 0.0);
                }
            }
        }
    }

}
