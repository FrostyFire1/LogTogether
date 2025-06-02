package com.frostyfire1.logtogether;

import gregtech.api.metatileentity.CommonBaseMetaTileEntity;

public class LoggingHelper {

    public static String getDimensionInfo(CommonBaseMetaTileEntity tileEntity) {
        return String.format(
            " at %d,%d,%d in dimension %d",
            tileEntity.xCoord,
            tileEntity.yCoord,
            tileEntity.zCoord,
            tileEntity.getWorldObj().provider.dimensionId);
    }
}
