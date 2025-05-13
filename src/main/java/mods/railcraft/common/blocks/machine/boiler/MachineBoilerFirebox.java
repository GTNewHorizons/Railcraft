package mods.railcraft.common.blocks.machine.boiler;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerFirebox.FireboxType;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineBoilerFirebox extends Machine {

    public MachineBoilerFirebox(FireboxType type) {
        super(Module.STEAM, getTileClass(type), "boiler.firebox." + extraTag(type), 3, 1, 0, 0, 1, 1, 1, 1, 2);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block firebox = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        firebox.setHarvestLevel("pickaxe", 2);
        return firebox;
    }

    private static String extraTag(FireboxType type) {
        return switch (type) {
            case SOLID -> "solid";
            case LIQUID -> "liquid";
        };
    }

    private static Class<? extends TileBoilerFirebox> getTileClass(FireboxType type) {
        return switch (type) {
            case SOLID -> TileBoilerFireboxSolid.class;
            case LIQUID -> TileBoilerFireboxFluid.class;
        };
    }
}
