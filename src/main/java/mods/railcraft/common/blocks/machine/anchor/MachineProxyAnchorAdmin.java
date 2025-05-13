package mods.railcraft.common.blocks.machine.anchor;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyAnchorAdmin implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.ADMIN_ANCHOR;
    }
}
