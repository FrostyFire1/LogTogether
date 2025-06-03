package com.frostyfire1.logtogether.mixins.late.gregtech;

import javax.annotation.Nonnull;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.frostyfire1.logtogether.LogTogetherLogger;
import com.frostyfire1.logtogether.LoggingHelper;

import gregtech.api.metatileentity.CommonBaseMetaTileEntity;
import gregtech.api.metatileentity.implementations.MTEMultiBlockBase;
import gregtech.api.util.shutdown.ShutDownReason;
import gregtech.api.util.shutdown.ShutDownReasonRegistry;

@Mixin(value = MTEMultiBlockBase.class, remap = false)
public class MultiBlockShutdownLogMixin {

    @Inject(method = "stopMachine(Lgregtech/api/util/shutdown/ShutDownReason;)V", at = @At("RETURN"))
    private void gt5u$MultiBlockShutdown(@Nonnull ShutDownReason reason, CallbackInfo ci) {
        MTEMultiBlockBase self = (MTEMultiBlockBase) (Object) this;
        if (reason.equals(ShutDownReasonRegistry.NONE)) return;

        LogTogetherLogger.info(getShutdownInfo(self, reason));
    }

    private String getShutdownInfo(MTEMultiBlockBase self, ShutDownReason reason) {
        String reasonDisplayString = reason.getDisplayString();
        return String.format(
            "MULTIBLOCK SHUTDOWN: %s%s HAS SHUTDOWN (%s)",
            self.getMetaName(),
            LoggingHelper.getDimensionInfo((CommonBaseMetaTileEntity) self.getBaseMetaTileEntity()),
            reasonDisplayString);
    }
}
