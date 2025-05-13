package mods.railcraft.common.blocks.machine.chest;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineChest extends Machine {

    public MachineChest(Class<? extends TileChestRailcraft> tile, String tag) {
        super(Module.TRANSPORT, tile, "chest." + tag, 1, 1, 0);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block chestBlock = new BlockMachine(0, proxy, false, 0).setBlockName("railcraft." + this.tag);
        chestBlock.setHarvestLevel("pickaxe", 2);
        return chestBlock;
    }
}
