package mods.railcraft.common.blocks.machine.steam_oven;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxySteamOven implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.STEAM_OVEN;
    }
}
