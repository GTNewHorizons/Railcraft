package mods.railcraft.common.blocks.machine.tank_water;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyTankWater implements IMachineProxy {

    @Override
    public IMachine getMachine() {
        return Machines.TANK_WATER;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.TANK_WATER.registerIcons(iconRegister);
    }
}
