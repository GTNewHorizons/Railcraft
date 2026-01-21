/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.client.gui;

import net.minecraft.entity.Entity;

import mods.railcraft.common.gui.containers.RailcraftContainer;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public abstract class EntityGui extends GuiContainerRailcraft {

    private final Entity entity;

    public EntityGui(Entity entity, RailcraftContainer container, String texture) {
        super(container, texture);
        this.entity = entity;
    }

    protected String getGuiTitle() {
        return LocalizationPlugin.translateGuiTitle(entity);
    }

    // @Override
    // public void updateScreen()
    // {
    // super.updateScreen();
    // if(entity.isDead) {
    // mc.thePlayer.closeScreen();
    // }
    // }
}
