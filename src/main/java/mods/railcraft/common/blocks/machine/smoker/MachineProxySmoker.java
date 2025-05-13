package mods.railcraft.common.blocks.machine.smoker;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxySmoker implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.SMOKER;
    }
}
