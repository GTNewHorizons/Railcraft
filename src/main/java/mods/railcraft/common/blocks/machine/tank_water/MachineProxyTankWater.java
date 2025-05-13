package mods.railcraft.common.blocks.machine.tank_water;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyTankWater implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.TANK_WATER;
    }
}
