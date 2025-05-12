package mods.railcraft.common.blocks.machine.steam_trap;

import net.minecraft.client.renderer.texture.IIconRegister;

import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxySteamTrap implements IMachineProxy {

    private boolean auto;

    public MachineProxySteamTrap(boolean auto) {
        this.auto = auto;
    }

    @Override
    public IMachine getMachine(int meta) {
        return auto ? Machines.STEAM_TRAP_AUTO : Machines.STEAM_TRAP_MANUAL;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        (auto ? Machines.STEAM_TRAP_AUTO : Machines.STEAM_TRAP_MANUAL).registerIcons(iconRegister);
    }
}
