package com.github.einjerjar.mc.keymap;

import com.github.einjerjar.mc.keymap.config.KeymapConfig;
import com.github.einjerjar.mc.keymap.cross.CrossKeybinder;
import com.github.einjerjar.mc.keymap.cross.IntegrationRegistrar;
import com.github.einjerjar.mc.keymap.cross.TickEventRegistrar;
import com.github.einjerjar.mc.keymap.keys.layout.KeyLayout;
import com.github.einjerjar.mc.keymap.keys.registry.category.CategoryRegistry;
import com.github.einjerjar.mc.keymap.keys.registry.keymap.KeymapRegistry;
import com.mojang.blaze3d.platform.InputConstants;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Accessors(fluent = true, chain = true)
public class Keymap implements ModInitializer {
    @Getter protected static final String         MOD_ID       = "keymap";
    @Getter protected static final String         MOD_NAME     = "keymap";
    @Getter protected static final Logger         logger       = LogManager.getLogger();
    @Getter protected static final CrossKeybinder kmOpenMapper = new CrossKeybinder(
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_GRAVE
    );

    @Override
    public void onInitialize() {
        KeymapConfig.load();
        logger.info(KeymapConfig.instance().toString());

        KeyLayout.loadKeys();

        for (KeyLayout keyLayout : KeyLayout.layouts().values()) {
            logger.info("Layout for {} @ {}",
                    keyLayout.meta().code(),
                    keyLayout.meta().name());
        }

        KeymapRegistry.collect();
        CategoryRegistry.collect();

        TickEventRegistrar.execute();
        IntegrationRegistrar.execute();
    }

}