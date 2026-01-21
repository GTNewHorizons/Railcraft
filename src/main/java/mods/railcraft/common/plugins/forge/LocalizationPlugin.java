/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.plugins.forge;

import java.util.IllegalFormatException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.StatCollector;

import mods.railcraft.common.blocks.RailcraftTileEntity;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class LocalizationPlugin {

    public static final String ENGLISH = "en_US";
    private static final String GUI_TITLE_COLOR_TAG = "railcraft.gui.color.title";

    public static String translate(String tag) {
        return StatCollector.translateToLocal(tag).replace("\\n", "\n").replace("@", "%").replace("\\%", "@");
    }

    public static String translate(String tag, Object... args) {
        String text = translate(tag);

        try {
            return String.format(text, args);
        } catch (IllegalFormatException ex) {
            return "Format error: " + text;
        }
    }

    public static boolean hasTag(String tag) {
        return StatCollector.canTranslate(tag);
    }

    public static String translateGuiTitle(Entity entity) {
        return getGuiTitlePrefix() + entity.getCommandSenderName();
    }

    public static String translateGuiTitle(RailcraftTileEntity tile) {
        return getGuiTitlePrefix() + tile.getName();
    }

    public static String getGuiTitlePrefix() {
        String translated = StatCollector.translateToLocal(GUI_TITLE_COLOR_TAG);
        if (!GUI_TITLE_COLOR_TAG.equals(translated)) return translated;
        return "";
    }

    public static String getEntityLocalizationTag(Entity entity) {
        String s = EntityList.getEntityString(entity);

        if (s == null) {
            s = "generic";
        }

        return "entity." + s + ".name";
    }
}
