package mods.railcraft.common.blocks.machine.coke_oven;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyCokeOven implements IMachineProxy {

    @Override
    public IMachine getMachine(int meta) {
        return Machines.COKE_OVEN;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.COKE_OVEN.registerIcons(iconRegister);
    }
}
