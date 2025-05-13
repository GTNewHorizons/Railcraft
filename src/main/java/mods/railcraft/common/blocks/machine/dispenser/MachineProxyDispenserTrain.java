package mods.railcraft.common.blocks.machine.dispenser;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyDispenserTrain implements IMachineProxy {

    @Override
    public IMachine getMachine() {
        return Machines.TRAIN_DISPENSER;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.TRAIN_DISPENSER.registerIcons(iconRegister);
    }
}
