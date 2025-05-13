package mods.railcraft.common.blocks.machine.rolling_machine;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyRollingMachine implements IMachineProxy {

    @Override
    public IMachine getMachine() {
        return Machines.ROLLING_MACHINE;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.ROLLING_MACHINE.registerIcons(iconRegister);
    }
}
