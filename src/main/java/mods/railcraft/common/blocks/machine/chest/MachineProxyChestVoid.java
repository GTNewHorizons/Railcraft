package mods.railcraft.common.blocks.machine.chest;

import net.minecraft.client.renderer.texture.IIconRegister;

import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyChestVoid implements IMachineProxy {

    @Override
    public Machine getMachine(int meta) {
        return Machines.VOID_CHEST;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        getMachine(0).registerIcons(iconRegister);
    }
}
