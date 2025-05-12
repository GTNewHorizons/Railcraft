package mods.railcraft.common.blocks.machine.steam_producer;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyAdminSteamProducer implements IMachineProxy {

    @Override
    public IMachine getMachine(int meta) {
        return Machines.ADMIN_STEAM_PRODUCER;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.ADMIN_STEAM_PRODUCER.registerIcons(iconRegister);
    }
}
