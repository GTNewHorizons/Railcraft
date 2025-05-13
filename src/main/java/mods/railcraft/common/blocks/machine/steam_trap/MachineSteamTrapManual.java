package mods.railcraft.common.blocks.machine.steam_trap;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineSteamTrapManual extends Machine {

    public MachineSteamTrapManual() {
        super(Module.EXTRAS, TileSteamTrapManual.class, "steam_trap", 3, 1, 0, 2, 1, 1, 1, 1, 0, 1, 2);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block steamTrapManual = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        steamTrapManual.setHarvestLevel("pickaxe", 2);
        return steamTrapManual;
    }
}
