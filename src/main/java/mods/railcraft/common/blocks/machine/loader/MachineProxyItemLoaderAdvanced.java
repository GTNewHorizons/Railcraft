package mods.railcraft.common.blocks.machine.loader;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyItemLoaderAdvanced implements IMachineProxy {

    @Override
    public IMachine getMachine() {
        return Machines.ITEM_LOADER_ADVANCED;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.ITEM_LOADER_ADVANCED.registerIcons(iconRegister);
    }
}
