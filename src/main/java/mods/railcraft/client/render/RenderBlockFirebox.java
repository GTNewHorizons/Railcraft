package mods.railcraft.client.render;

import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerFirebox.FireboxType;

public class RenderBlockFirebox extends BlockRenderer {

    public RenderBlockFirebox(FireboxType fireboxType) {
        super(getMachine(fireboxType).getBlock());
    }

    private static Machine getMachine(FireboxType fireboxType) {
        return switch (fireboxType) {
            case SOLID -> Machines.BOILER_FIREBOX_SOLID;
            case LIQUID -> Machines.BOILER_FIREBOX_LIQUID;
        };
    }
}
