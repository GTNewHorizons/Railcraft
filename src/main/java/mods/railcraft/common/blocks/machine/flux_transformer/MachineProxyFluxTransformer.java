package mods.railcraft.common.blocks.machine.flux_transformer;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyFluxTransformer implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.FLUX_TRANSFORMER;
    }
}
