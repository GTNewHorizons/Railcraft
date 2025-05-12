package mods.railcraft.common.blocks.machine.feed_station;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyFeedStation implements IMachineProxy {

    @Override
    public IMachine getMachine(int meta) {
        return Machines.FEED_STATION;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.FEED_STATION.registerIcons(iconRegister);
    }
}
