package mods.railcraft.common.blocks.machine.rock_crusher;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineRockCrusher extends Machine {

    public MachineRockCrusher() {
        super(
                Module.FACTORY,
                TileRockCrusher.class,
                "rock_crusher",
                4,
                3,
                3,
                11,
                3,
                3,
                7,
                3,
                7,
                0,
                1,
                2,
                4,
                6,
                8,
                9,
                10);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block rockCrusher = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        rockCrusher.setHarvestLevel("pickaxe", 2);
        return rockCrusher;
    }
}
