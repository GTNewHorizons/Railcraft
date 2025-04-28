/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.client.render;

import mods.railcraft.common.blocks.RailcraftBlocks;
import mods.railcraft.common.blocks.machine.beta.EnumMachineBeta;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class RenderBlockMachineBeta extends BlockRenderer {

    public RenderBlockMachineBeta() {
        super(RailcraftBlocks.getBlockMachineBeta());

        addBlockRenderer(EnumMachineBeta.BOILER_TANK_LOW_PRESSURE.ordinal(), new RenderBoilerTank());
        addBlockRenderer(EnumMachineBeta.BOILER_TANK_HIGH_PRESSURE.ordinal(), new RenderBoilerTank());
    }
}
