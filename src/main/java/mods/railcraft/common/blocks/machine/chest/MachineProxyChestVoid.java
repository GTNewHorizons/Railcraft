package mods.railcraft.common.blocks.machine.chest;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyChestVoid implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.VOID_CHEST;
    }
}
