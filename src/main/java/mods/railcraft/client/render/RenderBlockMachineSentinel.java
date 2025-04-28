package mods.railcraft.client.render;

import mods.railcraft.common.blocks.RailcraftBlocks;

public class RenderBlockMachineSentinel extends BlockRenderer {

    public RenderBlockMachineSentinel() {
        super(RailcraftBlocks.getBlockMachineSentinel());
        addCombinedRenderer(0, new RenderSentinel());
    }
}
