package mods.railcraft.common.blocks.machine.rock_crusher;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyRockCrusher implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.ROCK_CRUSHER;
    }
}
