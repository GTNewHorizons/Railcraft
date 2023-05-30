/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.alpha;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;

import mods.railcraft.common.blocks.machine.IEnumMachine;
import mods.railcraft.common.blocks.machine.IMachineProxy;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class MachineProxyAlpha implements IMachineProxy {

    @Override
    public IEnumMachine getMachine(int meta) {
        return EnumMachineAlpha.fromId(meta);
    }

    @Override
    public List<? extends IEnumMachine> getCreativeList() {
        return EnumMachineAlpha.getCreativeList();
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        EnumMachineAlpha.registerIcons(iconRegister);
    }
}
