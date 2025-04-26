/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.delta;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class MachineProxyWire implements IMachineProxy {

    @Override
    public IMachine getMachine(int meta) {
        return MachineWire.INSTANCE;
    }

    @Override
    public List<? extends IMachine> getCreativeList() {
        ArrayList<IMachine> list = new ArrayList<IMachine>();
        list.add(MachineWire.INSTANCE);
        return list;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        MachineWire.registerIcons(iconRegister);
    }
}
