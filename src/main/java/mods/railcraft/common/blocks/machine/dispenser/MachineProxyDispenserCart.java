package mods.railcraft.common.blocks.machine.dispenser;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyDispenserCart implements IMachineProxy {

    @Override
    public Machine getMachine() {
        return Machines.CART_DISPENSER;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.CART_DISPENSER.registerIcons(iconRegister);
    }
}
