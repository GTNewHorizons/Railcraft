package mods.railcraft.client.render;

import mods.railcraft.common.blocks.machine.Machines;

public class RenderBlockMachineSentinel extends BlockRenderer {

    public RenderBlockMachineSentinel() {
        super(Machines.SENTINEL.getBlock());
        addCombinedRenderer(0, new RenderSentinel());
    }
}
