package mods.railcraft.common.blocks.machine.electric_feeder;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyElectricFeederAdmin implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.ELECTRIC_FEEDER_ADMIN;
    }
}
