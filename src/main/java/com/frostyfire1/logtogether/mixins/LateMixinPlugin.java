package com.frostyfire1.logtogether.mixins;

import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

@LateMixin
public class LateMixinPlugin implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.logtogether.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        return Mixin.getLateMixins(loadedMods);
    }
}
