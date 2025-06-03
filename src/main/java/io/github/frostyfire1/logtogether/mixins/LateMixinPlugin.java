package io.github.frostyfire1.logtogether.mixins;

import java.io.File;
import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

import io.github.frostyfire1.logtogether.Config;

@LateMixin
public class LateMixinPlugin implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.logtogether.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        // Config creation is put here because mixins get constructed before preinit
        Config.synchronizeConfiguration(new File("config/logtogether.cfg"));
        return Mixin.getLateMixins(loadedMods);
    }
}
