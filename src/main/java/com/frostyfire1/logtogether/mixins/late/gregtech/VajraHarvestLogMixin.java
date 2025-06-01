package com.frostyfire1.logtogether.mixins.late.gregtech;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.frostyfire1.logtogether.LogTogether;

import gregtech.common.tools.ToolVajra;

@Mixin(value = ToolVajra.class, remap = false)
public class VajraHarvestLogMixin {

    @Inject(method = "onItemUseFirst", at = @At("HEAD"))
    public void gt5u$toolVajraLogActivity(ItemStack stack, EntityPlayer player, World world, int x, int y, int z,
        int side, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Boolean> cir) {
        if (!world.isRemote) {
            Block block = world.getBlock(x, y, z);
            if (player.isSneaking()) {
                LogTogether.LOG.info(
                    "LogTogether: Player {} is force harvesting {} with vajra at {},{},{}!",
                    player.getDisplayName(),
                    block.getLocalizedName(),
                    x,
                    y,
                    z);
            } else {
                LogTogether.LOG.info(
                    "LogTogether: Player {} is right clicking {} with vajra at {},{},{}!",
                    player.getDisplayName(),
                    block.getLocalizedName(),
                    x,
                    y,
                    z);
            }
        }
    }
}
