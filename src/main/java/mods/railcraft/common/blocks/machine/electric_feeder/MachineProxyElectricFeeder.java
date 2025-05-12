package mods.railcraft.common.blocks.machine.electric_feeder;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;
import net.minecraft.client.renderer.texture.IIconRegister;

public class MachineProxyElectricFeeder implements IMachineProxy {
    @Override
    public IMachine getMachine(int meta) {
        return Machines.ELECTRIC_FEEDER;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.ELECTRIC_FEEDER.registerIcons(iconRegister);
    }
}
