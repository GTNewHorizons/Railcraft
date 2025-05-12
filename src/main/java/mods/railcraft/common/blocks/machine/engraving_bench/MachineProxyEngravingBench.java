package mods.railcraft.common.blocks.machine.engraving_bench;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;
import net.minecraft.client.renderer.texture.IIconRegister;

public class MachineProxyEngravingBench implements IMachineProxy {
    @Override
    public IMachine getMachine(int meta) {
        return Machines.ENGRAVING_BENCH;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.ENGRAVING_BENCH.registerIcons(iconRegister);
    }
}
