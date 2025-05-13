package mods.railcraft.common.blocks.machine.rolling_machine;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyRollingMachine implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.ROLLING_MACHINE;
    }
}
