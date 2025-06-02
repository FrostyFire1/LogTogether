package com.frostyfire1.logtogether;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static int GREGTECH_LAG_WARNING_MICROSECOND_THRESHOLD;
    public static boolean FORCE_GREGTECH_TILE_ENTITY_PROFILING;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        GREGTECH_LAG_WARNING_MICROSECOND_THRESHOLD = configuration.getInt(
            "GregTech Lag Warning Microsecond Threshold",
            Configuration.CATEGORY_GENERAL,
            500,
            1,
            Integer.MAX_VALUE,
            "Prints a warning if any gregtech tile entity takes too long to tick");
        FORCE_GREGTECH_TILE_ENTITY_PROFILING = configuration.getBoolean(
            "Force GregTech TileEntity Profiling",
            Configuration.CATEGORY_GENERAL,
            false,
            "By default time profiling only starts once a tile entity has been right clicked with a scanner. If true, this setting forces time profiling on server start");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
