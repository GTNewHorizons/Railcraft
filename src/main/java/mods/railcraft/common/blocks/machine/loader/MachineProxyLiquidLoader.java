package mods.railcraft.common.blocks.machine.loader;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyLiquidLoader implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.FLUID_LOADER;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.FLUID_LOADER.registerIcons(iconRegister);
    }
}
