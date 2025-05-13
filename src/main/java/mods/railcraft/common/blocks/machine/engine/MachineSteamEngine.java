package mods.railcraft.common.blocks.machine.engine;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.modules.ModuleManager.Module;

public class MachineSteamEngine extends Machine {

    public MachineSteamEngine(EngineType type) {
        super(Module.STEAM, getTileClass(type), "engine.steam." + type.getName(), 1, 1, 0);
    }

    @Override
    public Block createBlock(MachineProxy proxy) {
        Block engineBlock = new BlockMachine(0, proxy, false, 0).setBlockName("railcraft." + tag);
        engineBlock.setHarvestLevel("pickaxe", 2);
        return engineBlock;
    }

    private static Class<? extends TileEngineSteam> getTileClass(EngineType type) {
        return switch (type) {
            case HOBBY -> TileEngineSteamHobby.class;
            case LOW -> TileEngineSteamLow.class;
            case HIGH -> TileEngineSteamHigh.class;
        };
    }
}
