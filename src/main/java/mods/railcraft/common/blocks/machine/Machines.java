package mods.railcraft.common.blocks.machine;

import java.util.Map;
import java.util.TreeMap;

import net.minecraft.block.Block;

import mods.railcraft.common.blocks.RailcraftBlocks;
import mods.railcraft.common.blocks.machine.blast_furnace.TileBlastFurnace;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerFirebox.FireboxType;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerFireboxFluid;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerFireboxSolid;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerTank.TankPressure;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerTankHigh;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerTankLow;
import mods.railcraft.common.blocks.machine.chest.TileChestMetals;
import mods.railcraft.common.blocks.machine.chest.TileChestVoid;
import mods.railcraft.common.blocks.machine.coke_oven.TileCokeOven;
import mods.railcraft.common.blocks.machine.electric_feeder.TileElectricFeeder;
import mods.railcraft.common.blocks.machine.electric_feeder.TileElectricFeederAdmin;
import mods.railcraft.common.blocks.machine.engine.EngineType;
import mods.railcraft.common.blocks.machine.engine.TileEngineSteamHigh;
import mods.railcraft.common.blocks.machine.engine.TileEngineSteamHobby;
import mods.railcraft.common.blocks.machine.engine.TileEngineSteamLow;
import mods.railcraft.common.blocks.machine.engraving_bench.TileEngravingBench;
import mods.railcraft.common.blocks.machine.feed_station.TileFeedStation;
import mods.railcraft.common.blocks.machine.flux_transformer.MachineFluxTransformer;
import mods.railcraft.common.blocks.machine.flux_transformer.TileFluxTransformer;
import mods.railcraft.common.blocks.machine.force_track_emitter.MachineForceTrackEmitter;
import mods.railcraft.common.blocks.machine.force_track_emitter.TileForceTrackEmitter;
import mods.railcraft.common.blocks.machine.rock_crusher.TileRockCrusher;
import mods.railcraft.common.blocks.machine.rolling_machine.TileRollingMachine;
import mods.railcraft.common.blocks.machine.sentinel.TileSentinel;
import mods.railcraft.common.blocks.machine.smoker.TileSmoker;
import mods.railcraft.common.blocks.machine.steam_producer.TileAdminSteamProducer;
import mods.railcraft.common.blocks.machine.steam_trap.TileSteamTrapAuto;
import mods.railcraft.common.blocks.machine.steam_trap.TileSteamTrapManual;
import mods.railcraft.common.blocks.machine.tank.MachineTank;
import mods.railcraft.common.blocks.machine.tank.TankMaterial;
import mods.railcraft.common.blocks.machine.tank.TileTankBase;
import mods.railcraft.common.blocks.machine.tank.TileTankGauge;
import mods.railcraft.common.blocks.machine.tank.TileTankValve;
import mods.railcraft.common.blocks.machine.tank.TileTankWall;
import mods.railcraft.common.blocks.machine.tank_water.TileTankWater;
import mods.railcraft.common.blocks.machine.trade_station.TileTradeStation;
import mods.railcraft.common.blocks.machine.wire.TileWire;
import mods.railcraft.common.modules.ModuleManager.Module;

public class Machines {

    public static final Map<TankMaterial, Machine> tankWalls = new TreeMap<>();
    public static final Map<TankMaterial, Machine> tankValves = new TreeMap<>();
    public static final Map<TankMaterial, Machine> tankGauges = new TreeMap<>();

    public static Machine SMOKER = registerMachine(
            Module.STRUCTURES,
            RailcraftBlocks.registerBlockSmoker(),
            TileSmoker.class,
            "smoker",
            3,
            1,
            0,
            1,
            2,
            2,
            2,
            2);

    public static Machine TRADE_STATION = registerMachine(
            Module.AUTOMATION,
            RailcraftBlocks.registerBlockTradeStation(),
            TileTradeStation.class,
            "trade_station",
            3,
            1,
            0,
            0,
            1,
            1,
            2,
            1);
    public static Machine COKE_OVEN = registerMachine(
            Module.FACTORY,
            RailcraftBlocks.registerBlockCokeOven(),
            TileCokeOven.class,
            "coke_oven",
            3,
            1,
            0,
            0,
            0,
            0,
            1,
            0,
            1,
            2);
    public static Machine ROLLING_MACHINE = registerMachine(
            Module.FACTORY,
            RailcraftBlocks.registerBlockRollingMachine(),
            TileRollingMachine.class,
            "rolling_machine",
            3,
            1,
            0,
            1,
            2,
            2,
            2,
            2);
    public static Machine STEAM_TRAP_MANUAL = registerMachine(
            Module.EXTRAS,
            RailcraftBlocks.registerBlockSteamTrap(false),
            TileSteamTrapManual.class,
            "steam_trap",
            3,
            1,
            0,
            2,
            1,
            1,
            1,
            1,
            0,
            1,
            2);
    public static Machine STEAM_TRAP_AUTO = registerMachine(
            Module.EXTRAS,
            RailcraftBlocks.registerBlockSteamTrap(true),
            TileSteamTrapAuto.class,
            "steam_trap.auto",
            4,
            1,
            0,
            2,
            1,
            1,
            1,
            1,
            0,
            1,
            2,
            3);
    public static Machine FEED_STATION = registerMachine(
            Module.AUTOMATION,
            RailcraftBlocks.registerBlockFeedStation(),
            TileFeedStation.class,
            "feed_station",
            2,
            1,
            0,
            0,
            1,
            1,
            1,
            1);

    public static Machine BLAST_FURNACE = registerMachine(
            Module.FACTORY,
            RailcraftBlocks.registerBlockBlastFurnace(),
            TileBlastFurnace.class,
            "blast_furnace",
            3,
            1,
            0,
            0,
            0,
            0,
            1,
            0,
            1,
            2);

    public static Machine TANK_WATER = registerMachine(
            Module.TRANSPORT,
            RailcraftBlocks.registerBlockTankWater(),
            TileTankWater.class,
            "tank_water",
            2,
            1,
            0,
            0,
            1,
            1,
            1,
            1);

    public static final Machine ROCK_CRUSHER = registerMachine(
            Module.FACTORY,
            RailcraftBlocks.registerBlockRockCrusher(),
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

    public static final Machine ELECTRIC_FEEDER = registerMachine(
            Module.ELECTRICITY,
            RailcraftBlocks.registerBlockElectricFeeder(false),
            TileElectricFeeder.class,
            "electric_feeder",
            1,
            1,
            0);

    public static final Machine ELECTRIC_FEEDER_ADMIN = registerMachine(
            Module.ELECTRICITY,
            RailcraftBlocks.registerBlockElectricFeeder(true),
            TileElectricFeederAdmin.class,
            "electric_feeder.admin",
            2,
            1,
            0,
            0,
            0,
            0,
            0,
            0,
            1);

    public static final Machine ADMIN_STEAM_PRODUCER = registerMachine(
            Module.STEAM,
            RailcraftBlocks.registerBlockAdminSteamProducer(),
            TileAdminSteamProducer.class,
            "admin_steam_producer",
            2,
            1,
            0,
            0,
            0,
            0,
            0,
            0,
            1);

    public static final Machine FORCE_TRACK_EMITTER = registerForceTrackEmitter("force_track_emitter");

    public static final Machine FLUX_TRANSFORMER = registerFluxTransformer("flux_transformer");

    public static final Machine ENGRAVING_BENCH = registerMachine(
            Module.EMBLEM,
            RailcraftBlocks.registerBlockEngravingBench(),
            TileEngravingBench.class,
            "engraving_bench",
            4,
            1,
            0,
            1,
            3,
            3,
            3,
            3,
            2);

    private static Machine registerMachine(Module module, Block block, Class<? extends TileMachineBase> tileClass,
            String tag, int... textureInfo) {
        if (block != null) {
            return new Machine(module, block, tileClass, tag, textureInfo);
        }
        return null;
    }

    private static Machine registerFluxTransformer(String tag) {
        Block block = RailcraftBlocks.registerBlockFluxTransformer();
        if (block != null) {
            return new MachineFluxTransformer(Module.ELECTRICITY, block, TileFluxTransformer.class, tag);
        }
        return null;
    }

    private static Machine registerForceTrackEmitter(String tag) {
        Block block = RailcraftBlocks.registerBlockForceTrackEmitter();
        if (block != null) {
            return new MachineForceTrackEmitter(Module.ELECTRICITY, block, TileForceTrackEmitter.class, tag);
        }
        return null;
    }

    public static void initTanks() {
        for (TankMaterial mat : TankMaterial.values()) {
            if (mat.module.isEnabled()) {
                tankWalls.put(mat, registerTankWall(mat));
                tankValves.put(mat, registerTankValve(mat));
                tankGauges.put(mat, registerTankGauge(mat));
            }
        }
    }

    private static MachineTank registerMachineTank(Block block, TankMaterial material,
            Class<? extends TileTankBase> tileClass, String tag, int... textureInfo) {
        if (block != null) {
            return new MachineTank(material.module, material, block, tileClass, tag, textureInfo);
        }
        return null;
    }

    private static Machine registerTankWall(TankMaterial material) {
        return registerMachineTank(
                RailcraftBlocks.registerBlockTankWall(material),
                material,
                TileTankWall.class,
                "tank." + material.name + ".wall",
                2,
                1,
                0,
                0,
                1,
                1,
                1,
                1);
    }

    private static Machine registerTankGauge(TankMaterial material) {
        return registerMachineTank(
                RailcraftBlocks.registerBlockTankGauge(material),
                material,
                TileTankGauge.class,
                "tank." + material.name + ".gauge",
                1,
                5,
                0,
                0,
                0,
                0,
                0,
                0,
                1,
                2,
                3,
                4);
    }

    private static Machine registerTankValve(TankMaterial material) {
        return registerMachineTank(
                RailcraftBlocks.registerBlockTankValve(material),
                material,
                TileTankValve.class,
                "tank." + material.name + ".valve",
                4,
                1,
                0,
                0,
                1,
                1,
                1,
                1,
                2,
                3);
    }
}
