package mods.railcraft.common.blocks.machine.feed_station;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineFeedStation extends Machine {

    public MachineFeedStation() {
        super(Module.AUTOMATION, TileFeedStation.class, "feed_station", 2, 1, 0, 0, 1, 1, 1, 1);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block feedStation = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        feedStation.setHarvestLevel("axe", 1);
        return feedStation;
    }
}
