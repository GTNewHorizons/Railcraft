/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.anchor;

import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class TileAnchorAdmin extends TileAnchorWorld {

    @Override
    public Machine getMachineType() {
        return Machines.ADMIN_ANCHOR;
    }

    @Override
    public boolean needsFuel() {
        return false;
    }
}
