package com.unrealdinnerbone.tramplestopper.compact;

import com.unrealdinnerbone.tramplestopper.TrampleConfig;
import com.unrealdinnerbone.tramplestopper.TrampleStopper;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1.AutoConfig;
import net.minecraft.client.gui.Screen;

import java.util.Optional;
import java.util.function.Supplier;

public class MMCompact implements ModMenuApi {

    @Override
    public String getModId() {
        return TrampleStopper.MOD_ID;
    }

    @Override
    public Optional<Supplier<Screen>> getConfigScreen(Screen screen) {
        return Optional.of(AutoConfig.getConfigScreen(TrampleConfig.class, screen));
    }

}
