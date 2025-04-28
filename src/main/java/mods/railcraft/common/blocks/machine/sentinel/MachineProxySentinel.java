package mods.railcraft.common.blocks.machine.sentinel;

import net.minecraft.client.renderer.texture.IIconRegister;

import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxySentinel implements IMachineProxy {

    @Override
    public IMachine getMachine(int meta) {
        return Machines.SENTINEL;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        Machines.SENTINEL.registerIcons(iconRegister);
    }
}
