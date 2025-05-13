package mods.railcraft.common.blocks.machine.rock_crusher;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyRockCrusher implements IMachineProxy {

    @Override
    public IMachine getMachine() {
        return Machines.ROCK_CRUSHER;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.ROCK_CRUSHER.registerIcons(iconRegister);
    }
}
