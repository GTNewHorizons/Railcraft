/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.tank;

import net.minecraft.util.IIcon;

import mods.railcraft.common.blocks.machine.Machine;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class TileTankWall extends TileTankBase {

    public TileTankWall(TankMaterial material) {
        super(material);
    }

    @Override
    public Machine getMachineType() {
        return Tanks.getWall(material);
    }

    @Override
    public IIcon getIcon(int side) {
        return getMachineType().getTexture(side);
    }
}
