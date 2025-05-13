package mods.railcraft.common.blocks.machine.trade_station;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;
import mods.railcraft.common.blocks.machine.Machines;

public class MachineProxyTradeStation implements IMachineProxy {

    @Override
    public IMachine getMachine() {
        return Machines.TRADE_STATION;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        Machines.TRADE_STATION.registerIcons(iconRegister);
    }
}
