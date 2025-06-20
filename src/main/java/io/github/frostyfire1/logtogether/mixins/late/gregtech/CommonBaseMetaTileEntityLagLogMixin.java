package io.github.frostyfire1.logtogether.mixins.late.gregtech;

import static io.github.frostyfire1.logtogether.Constants.MICROSECONDS;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gregtech.api.metatileentity.CommonBaseMetaTileEntity;
import io.github.frostyfire1.logtogether.Config;
import io.github.frostyfire1.logtogether.LogTogetherLogger;
import io.github.frostyfire1.logtogether.LoggingHelper;

@Mixin(value = CommonBaseMetaTileEntity.class, remap = false)
public class CommonBaseMetaTileEntityLagLogMixin {

    @Shadow
    @Final
    private int[] mTimeStatistics;

    @Shadow
    private int mTimeStatisticsIndex;

    @Shadow
    private boolean hasTimeStatisticsStarted;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        this.hasTimeStatisticsStarted = Config.FORCE_GREGTECH_TILE_ENTITY_PROFILING;
    }

    @Inject(method = "updateEntity", at = @At("TAIL"))
    public void gt5u$commonBaseMetaTileEntityLagLog(CallbackInfo ci) {
        int timing = mTimeStatistics[this.mTimeStatisticsIndex];
        if (timing > Config.GREGTECH_LAG_WARNING_MICROSECOND_THRESHOLD * MICROSECONDS) {
            LogTogetherLogger.info(String.format("LAG WARNING: %s", getWarningInfo(timing)));
        }
    }

    private String getWarningInfo(int timing) {
        CommonBaseMetaTileEntity self = (CommonBaseMetaTileEntity) (Object) this;
        return String.format(
            "%s took %.3fus to tick",
            self.getMetaTileEntity()
                .getClass()
                .getName(),
            (double) timing / MICROSECONDS) + LoggingHelper.getDimensionInfo(self);

    }
}
