package mods.railcraft.common.blocks.machine.anchor;

import net.minecraft.client.renderer.texture.IIconRegister;

import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyAnchorPersonal implements IMachineProxy {

    @Override
    public IMachine getMachine(int meta) {
        return Machines.PERSONAL_ANCHOR;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        Machines.PERSONAL_ANCHOR.registerIcons(iconRegister);
    }
}
