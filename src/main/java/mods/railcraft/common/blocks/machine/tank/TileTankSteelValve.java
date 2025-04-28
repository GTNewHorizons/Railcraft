/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.tank;

import static mods.railcraft.common.blocks.machine.tank.TileTankSteelWall.STEEL_TANK;

import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.Machines;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class TileTankSteelValve extends TileTankIronValve {

    @Override
    public IMachine getMachineType() {
        return Machines.TANK_STEEL_VALVE;
    }

    @Override
    public MetalTank getTankType() {
        return STEEL_TANK;
    }

    @Override
    public int getCapacityPerBlock() {
        return CAPACITY_PER_BLOCK_STEEL;
    }
}
