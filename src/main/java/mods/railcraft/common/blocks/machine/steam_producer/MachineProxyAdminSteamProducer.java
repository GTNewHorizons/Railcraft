package mods.railcraft.common.blocks.machine.steam_producer;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyAdminSteamProducer implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.ADMIN_STEAM_PRODUCER;
    }
}
