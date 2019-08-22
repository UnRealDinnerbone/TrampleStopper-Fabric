package com.unrealdinnerbone.tramplestopper.compact;

import com.unrealdinnerbone.tramplestopper.TrampleConfig;
import com.unrealdinnerbone.tramplestopper.TrampleStopper;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class MMCompact implements ModMenuApi {

    @Override
    public String getModId() {
        return TrampleStopper.MOD_ID;
    }

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return (Function<Screen, Screen>) screen -> AutoConfig.getConfigScreen(TrampleConfig.class, screen).get();
}
}
