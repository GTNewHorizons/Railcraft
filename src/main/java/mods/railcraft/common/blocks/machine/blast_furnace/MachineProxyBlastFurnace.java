package mods.railcraft.common.blocks.machine.blast_furnace;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyBlastFurnace implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.BLAST_FURNACE;
    }
}
