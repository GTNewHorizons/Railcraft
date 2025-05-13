package mods.railcraft.common.blocks.machine.chest;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyChestMetals implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.METALS_CHEST;
    }
}
