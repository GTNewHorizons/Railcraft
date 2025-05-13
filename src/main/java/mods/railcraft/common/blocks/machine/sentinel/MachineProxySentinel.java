package mods.railcraft.common.blocks.machine.sentinel;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxySentinel implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.SENTINEL;
    }
}
