package com.frostyfire1.logtogether.mixins.late.gregtech;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.frostyfire1.logtogether.LogTogetherLogger;

import gregtech.common.tools.ToolVajra;

@Mixin(value = ToolVajra.class, remap = false)
public class VajraHarvestLogMixin {

    @Inject(method = "onItemUseFirst", at = @At("HEAD"))
    public void gt5u$toolVajraLogActivity(ItemStack stack, EntityPlayer player, World world, int x, int y, int z,
        int side, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Boolean> cir) {
        if (!world.isRemote) {
            Block block = world.getBlock(x, y, z);
            int meta = block.getDamageValue(world, x, y, z);
            ItemStack targetBlockItemStack = new ItemStack(block, 1, meta);
            String itemStackDisplayName = targetBlockItemStack.getDisplayName();
            if (player.isSneaking()) {
                LogTogetherLogger.info(
                    String.format(
                        "VAJRA HARVEST: Player %s is force harvesting %s at %d,%d,%d!",
                        player.getDisplayName(),
                        itemStackDisplayName,
                        x,
                        y,
                        z));
            } else {
                LogTogetherLogger.info(
                    String.format(
                        "VAJRA HARVEST: Player %s is right clicking %s at %d,%d,%d!",
                        player.getDisplayName(),
                        itemStackDisplayName,
                        x,
                        y,
                        z));
            }
        }
    }
}
