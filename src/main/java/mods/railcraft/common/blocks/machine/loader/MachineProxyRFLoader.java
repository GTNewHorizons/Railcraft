package mods.railcraft.common.blocks.machine.loader;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;
import net.minecraft.client.renderer.texture.IIconRegister;

public class MachineProxyRFLoader implements IMachineProxy {
    @Override
    public IMachine getMachine() {
        return Machines.RF_LOADER;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.RF_LOADER.registerIcons(iconRegister);
    }
}
