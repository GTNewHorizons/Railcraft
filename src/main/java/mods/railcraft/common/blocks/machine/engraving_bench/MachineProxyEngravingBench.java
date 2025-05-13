package mods.railcraft.common.blocks.machine.engraving_bench;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyEngravingBench implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.ENGRAVING_BENCH;
    }
}
