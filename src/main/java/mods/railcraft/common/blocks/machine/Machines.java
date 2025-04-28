package mods.railcraft.common.blocks.machine;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.RailcraftBlocks;
import mods.railcraft.common.blocks.machine.chest.TileChestMetals;
import mods.railcraft.common.blocks.machine.chest.TileChestVoid;
import mods.railcraft.common.blocks.machine.engine.EngineType;
import mods.railcraft.common.blocks.machine.engine.TileEngineSteamHigh;
import mods.railcraft.common.blocks.machine.engine.TileEngineSteamHobby;
import mods.railcraft.common.blocks.machine.engine.TileEngineSteamLow;
import mods.railcraft.common.blocks.machine.sentinel.TileSentinel;
import mods.railcraft.common.blocks.machine.wire.TileWire;
import mods.railcraft.common.modules.ModuleManager.Module;

public class Machines {

    public static final Machine ENGINE_STEAM_HOBBY = registerMachine(
            Module.STEAM,
            RailcraftBlocks.registerBlockEngine(EngineType.HOBBY),
            TileEngineSteamHobby.class,
            "engine.steam.hobby",
            1,
            1,
            0);
    public static final Machine ENGINE_STEAM_LOW = registerMachine(
            Module.STEAM,
            RailcraftBlocks.registerBlockEngine(EngineType.LOW),
            TileEngineSteamLow.class,
            "engine.steam.low",
            1,
            1,
            0);
    public static final Machine ENGINE_STEAM_HIGH = registerMachine(
            Module.STEAM,
            RailcraftBlocks.registerBlockEngine(EngineType.HIGH),
            TileEngineSteamHigh.class,
            "engine.steam.high",
            1,
            1,
            0);
    public static final Machine SENTINEL = registerMachine(
            Module.CHUNK_LOADING,
            RailcraftBlocks.registerBlockMachineSentinel(),
            TileSentinel.class,
            "anchor.sentinel",
            2,
            1,
            0,
            0,
            1,
            1,
            1,
            1);

    public static final Machine VOID_CHEST = registerMachine(
            Module.TRANSPORT,
            RailcraftBlocks.registerBlockMachineChestVoid(),
            TileChestVoid.class,
            "chest.void",
            1,
            1,
            0);
    public static final Machine METALS_CHEST = registerMachine(
            Module.TRANSPORT,
            RailcraftBlocks.registerBlockMachineChestMetals(),
            TileChestMetals.class,
            "chest.metals",
            1,
            1,
            0);

    public static final Machine WIRE = registerMachine(
            Module.ELECTRICITY,
            RailcraftBlocks.registerBlockMachineWire(),
            TileWire.class,
            "wire",
            1,
            1,
            0,
            0,
            0,
            0,
            0,
            0);

    public static Machine registerMachine(Module module, Block block, Class<? extends TileMachineBase> tileClass,
            String tag, int... textureInfo) {
        if (block != null) {
            return new Machine(module, block, tileClass, tag, textureInfo);
        }
        return null;
    }

    public static void init() {}
}
