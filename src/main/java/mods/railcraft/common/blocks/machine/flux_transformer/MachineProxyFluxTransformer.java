package mods.railcraft.common.blocks.machine.flux_transformer;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyFluxTransformer implements IMachineProxy {

    @Override
    public IMachine getMachine() {
        return Machines.FLUX_TRANSFORMER;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.FLUX_TRANSFORMER.registerIcons(iconRegister);
    }
}
