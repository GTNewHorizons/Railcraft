/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.boiler;

import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class TileBoilerTankHigh extends TileBoilerTank {

    public TileBoilerTankHigh() {
        super();
    }

    @Override
    public Machine getMachineType() {
        return Machines.BOILER_TANK_HIGH_PRESSURE;
    }
}
