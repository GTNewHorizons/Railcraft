package mods.railcraft.common.blocks.machine.turbine;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyTurbine implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.TURBINE;
    }
}
