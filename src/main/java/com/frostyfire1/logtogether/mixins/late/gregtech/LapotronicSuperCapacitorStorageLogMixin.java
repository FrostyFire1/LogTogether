package com.frostyfire1.logtogether.mixins.late.gregtech;

import java.math.BigInteger;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.frostyfire1.logtogether.LogTogetherLogger;
import com.frostyfire1.logtogether.LoggingHelper;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.CommonBaseMetaTileEntity;
import kekztech.common.tileentities.MTELapotronicSuperCapacitor;

@Mixin(value = MTELapotronicSuperCapacitor.class, remap = false)
public class LapotronicSuperCapacitorStorageLogMixin {

    @Shadow
    protected BigInteger stored;

    @Shadow
    private BigInteger capacity;
    @Unique
    private boolean logStorage;

    @Inject(method = "onRunningTick", at = @At("RETURN"))
    private void gt5u$LogLapotronicSuperCapacitorStorage(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        MTELapotronicSuperCapacitor self = (MTELapotronicSuperCapacitor) (Object) this;
        if (self.mRuntime % 100 == 0 && this.logStorage) {
            LogTogetherLogger.info(getLSCInfo(self));
        }
    }

    @Inject(method = "saveNBTData", at = @At("RETURN"))
    private void gt5u$SaveLoggingNBT(NBTTagCompound nbt, CallbackInfo ci) {
        nbt.setBoolean("LogTogether_LogStorage", this.logStorage);
    }

    @Inject(method = "loadNBTData", at = @At("RETURN"))
    private void gt5u$LoadLoggingNBT(NBTTagCompound nbt, CallbackInfo ci) {
        this.logStorage = nbt.getBoolean("LogTogether_LogStorage");
    }

    @Inject(method = "onScrewdriverRightClick", at = @At("HEAD"), cancellable = true)
    private void gt5u$ToggleStorageLogging(ForgeDirection side, EntityPlayer aPlayer, float aX, float aY, float aZ,
        ItemStack aTool, CallbackInfo ci) {
        if (aPlayer.isSneaking()) {
            this.logStorage = !this.logStorage;
            String toggle = this.logStorage ? "Enabled" : "Disabled";
            EnumChatFormatting color = this.logStorage ? EnumChatFormatting.GREEN : EnumChatFormatting.RED;
            aPlayer.addChatMessage(new ChatComponentText(color + toggle + " this LSC's storage logging."));
            ci.cancel();
        }
    }

    private String getLSCInfo(MTELapotronicSuperCapacitor self) {
        IGregTechTileEntity gte = self.getBaseMetaTileEntity();
        CommonBaseMetaTileEntity commonSelf = (CommonBaseMetaTileEntity) gte;
        return String.format("LSC STORAGE: %s/%s on LSC", this.stored.toString(), this.capacity.toString())
            + LoggingHelper.getDimensionInfo(commonSelf);
    }
}
