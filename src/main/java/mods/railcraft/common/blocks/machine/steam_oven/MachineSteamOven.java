package mods.railcraft.common.blocks.machine.steam_oven;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineSteamOven extends Machine {

    public MachineSteamOven() {
        super(Module.FACTORY, TileSteamOven.class, "steam_oven", 4, 2, 2, 2, 3, 3, 6, 3, 0, 1, 4, 5);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block steamOven = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        steamOven.setHarvestLevel("pickaxe", 2);
        return steamOven;
    }
}
