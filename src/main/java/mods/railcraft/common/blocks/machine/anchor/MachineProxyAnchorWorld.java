package mods.railcraft.common.blocks.machine.anchor;

import net.minecraft.client.renderer.texture.IIconRegister;

import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyAnchorWorld implements IMachineProxy {

    @Override
    public IMachine getMachine() {
        return Machines.WORLD_ANCHOR;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        Machines.WORLD_ANCHOR.registerIcons(iconRegister);
    }
}
