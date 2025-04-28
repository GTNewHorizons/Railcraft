package mods.railcraft.common.blocks.machine;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.RailcraftBlocks;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerFirebox.FireboxType;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerFireboxFluid;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerFireboxSolid;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerTank.TankPressure;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerTankHigh;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerTankLow;
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

    public static final Machine BOILER_TANK_LOW_PRESSURE = registerMachine(
            Module.STEAM,
            RailcraftBlocks.registerBlockBoilerTank(TankPressure.LOW),
            TileBoilerTankLow.class,
            "boiler.tank.pressure.low",
            2,
            1,
            0,
            0,
            1,
            1,
            1,
            1);
    public static final Machine BOILER_TANK_HIGH_PRESSURE = registerMachine(
            Module.STEAM,
            RailcraftBlocks.registerBlockBoilerTank(TankPressure.HIGH),
            TileBoilerTankHigh.class,
            "boiler.tank.pressure.high",
            2,
            1,
            0,
            0,
            1,
            1,
            1,
            1);
    public static final Machine BOILER_FIREBOX_SOLID = registerMachine(
            Module.STEAM,
            RailcraftBlocks.registerBlockFirebox(FireboxType.SOLID),
            TileBoilerFireboxSolid.class,
            "boiler.firebox.solid",
            3,
            1,
            0,
            0,
            1,
            1,
            1,
            1,
            2);
    public static final Machine BOILER_FIREBOX_LIQUID = registerMachine(
            Module.STEAM,
            RailcraftBlocks.registerBlockFirebox(FireboxType.LIQUID),
            TileBoilerFireboxFluid.class,
            "boiler.firebox.liquid",
            3,
            1,
            0,
            0,
            1,
            1,
            1,
            1,
            2);
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
}
