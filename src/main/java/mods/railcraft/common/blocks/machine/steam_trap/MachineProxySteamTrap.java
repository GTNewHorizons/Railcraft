package mods.railcraft.common.blocks.machine.steam_trap;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxySteamTrap implements IMachineProxy {

    private boolean auto;

    public MachineProxySteamTrap(boolean auto) {
        this.auto = auto;
    }

    @Override
    public Machine getMachine() {
        return auto ? Machines.STEAM_TRAP_AUTO : Machines.STEAM_TRAP_MANUAL;
    }
}
