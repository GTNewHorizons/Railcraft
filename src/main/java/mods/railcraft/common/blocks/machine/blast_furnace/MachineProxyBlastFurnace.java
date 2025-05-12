package mods.railcraft.common.blocks.machine.blast_furnace;

import net.minecraft.client.renderer.texture.IIconRegister;

import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyBlastFurnace implements IMachineProxy {

    @Override
    public IMachine getMachine(int meta) {
        return Machines.BLAST_FURNACE;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        Machines.BLAST_FURNACE.registerIcons(iconRegister);
    }
}
