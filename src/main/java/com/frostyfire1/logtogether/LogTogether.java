package com.frostyfire1.logtogether;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.filter.RegexFilter;
import org.apache.logging.log4j.core.layout.PatternLayout;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = LogTogether.MODID, version = Tags.VERSION, name = "LogTogether", acceptedMinecraftVersions = "[1.7.10]")
public class LogTogether {

    public static final String MODID = "logtogether";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(
        clientSide = "com.frostyfire1.logtogether.ClientProxy",
        serverSide = "com.frostyfire1.logtogether.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public static void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration config = context.getConfiguration();

        PatternLayout layout = PatternLayout
            .createLayout("[%d{HH:mm:ss}] [%t/%level] [%logger/%X{mod}]: %msg%n", config, null, "UTF-8", "true");

        // This filter will only be applied to the message itself. if useRawMsg is false and the string is formatted
        // then
        // The filter gets the pre-formatted string.
        RegexFilter filter = RegexFilter.createFilter(
            "^LogTogether:.*",
            "true",
            RegexFilter.Result.ACCEPT.toString(),
            Filter.Result.DENY.toString());

        FileAppender appender = FileAppender.createAppender(
            "logs/LogTogether.log",
            "true",
            "false",
            "LogTogether",
            "true",
            "false",
            "false",
            layout,
            filter,
            "false",
            null,
            config);
        appender.start();

        LoggerConfig loggerConfig = config.getLoggerConfig(LOG.getName());
        loggerConfig.addAppender(appender, null, null);
        context.updateLoggers();
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
