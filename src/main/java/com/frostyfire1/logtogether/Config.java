package com.frostyfire1.logtogether;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static int GREGTECH_LAG_WARNING_MICROSECOND_THRESHOLD;
    public static boolean FORCE_GREGTECH_TILE_ENTITY_PROFILING;

    public static boolean LOG_VAJRA_HARVESTING;
    public static boolean LOG_TILE_ENTITY_LAG;
    public static boolean LOG_LSC_STORAGE;
    public static boolean LOG_MULTIBLOCK_SHUTDOWN;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        initGregTechMixinConfig(configuration);
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    private static void initGregTechMixinConfig(Configuration configuration) {
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

        LOG_VAJRA_HARVESTING = configuration.getBoolean(
            "Log Vajra Harvesting",
            Configuration.CATEGORY_GENERAL,
            false,
            "Logs every instance of right click harvesting something with the vajra.");
        LOG_TILE_ENTITY_LAG = configuration.getBoolean(
            "Log Tile Entity Lag",
            Configuration.CATEGORY_GENERAL,
            false,
            "Logs every time an entity took too long (see: GregTech Lag Warning Microsecond Threshold) to update in a tick.");
        LOG_LSC_STORAGE = configuration.getBoolean(
            "Log LSC Storage",
            Configuration.CATEGORY_GENERAL,
            false,
            "Logs LSC Storage every 5 seconds. Logging for any LSC must be activated by shift right clicking the controller with a screwdriver.");
        LOG_MULTIBLOCK_SHUTDOWN = configuration.getBoolean(
            "Log Multiblock Shutdowns",
            Configuration.CATEGORY_GENERAL,
            false,
            "Logs every instance of a multiblock unexpectedly shutting down.");

    }

}
