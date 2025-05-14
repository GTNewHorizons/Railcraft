package mods.railcraft.common.blocks.machine;

import java.util.Map;
import java.util.TreeMap;

import mods.railcraft.common.blocks.machine.anchor.MachineAnchor;
import mods.railcraft.common.blocks.machine.anchor.TileAnchorAdmin;
import mods.railcraft.common.blocks.machine.anchor.TileAnchorPassive;
import mods.railcraft.common.blocks.machine.anchor.TileAnchorPersonal;
import mods.railcraft.common.blocks.machine.anchor.TileAnchorWorld;
import mods.railcraft.common.blocks.machine.blast_furnace.MachineBlastFurnace;
import mods.railcraft.common.blocks.machine.boiler.MachineBoilerFirebox;
import mods.railcraft.common.blocks.machine.boiler.MachineBoilerTank;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerFirebox.FireboxType;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerTank.TankPressure;
import mods.railcraft.common.blocks.machine.chest.MachineChest;
import mods.railcraft.common.blocks.machine.chest.TileChestMetals;
import mods.railcraft.common.blocks.machine.chest.TileChestVoid;
import mods.railcraft.common.blocks.machine.coke_oven.MachineCokeOven;
import mods.railcraft.common.blocks.machine.dispenser.TileDispenserCart;
import mods.railcraft.common.blocks.machine.dispenser.TileDispenserTrain;
import mods.railcraft.common.blocks.machine.electric_feeder.MachineElectricFeeder;
import mods.railcraft.common.blocks.machine.electric_feeder.MachineElectricFeederAdmin;
import mods.railcraft.common.blocks.machine.engine.EngineType;
import mods.railcraft.common.blocks.machine.engine.MachineSteamEngine;
import mods.railcraft.common.blocks.machine.engraving_bench.MachineEngravingBench;
import mods.railcraft.common.blocks.machine.feed_station.MachineFeedStation;
import mods.railcraft.common.blocks.machine.flux_transformer.MachineFluxTransformer;
import mods.railcraft.common.blocks.machine.force_track_emitter.MachineForceTrackEmitter;
import mods.railcraft.common.blocks.machine.loader.MachineItemLoader;
import mods.railcraft.common.blocks.machine.loader.MachineItemUnloader;
import mods.railcraft.common.blocks.machine.loader.MachineLiquidLoader;
import mods.railcraft.common.blocks.machine.loader.MachineLiquidUnloader;
import mods.railcraft.common.blocks.machine.loader.MachineRFLoader;
import mods.railcraft.common.blocks.machine.loader.MachineSideLoader;
import mods.railcraft.common.blocks.machine.loader.TileEnergyLoader;
import mods.railcraft.common.blocks.machine.loader.TileEnergyUnloader;
import mods.railcraft.common.blocks.machine.loader.TileItemLoaderAdvanced;
import mods.railcraft.common.blocks.machine.loader.TileItemUnloaderAdvanced;
import mods.railcraft.common.blocks.machine.loader.TileRFLoader;
import mods.railcraft.common.blocks.machine.loader.TileRFUnloader;
import mods.railcraft.common.blocks.machine.rock_crusher.MachineRockCrusher;
import mods.railcraft.common.blocks.machine.rolling_machine.MachineRollingMachine;
import mods.railcraft.common.blocks.machine.sentinel.MachineSentinel;
import mods.railcraft.common.blocks.machine.smoker.MachineSmoker;
import mods.railcraft.common.blocks.machine.steam_oven.MachineSteamOven;
import mods.railcraft.common.blocks.machine.steam_producer.MachineAdminSteamProducer;
import mods.railcraft.common.blocks.machine.steam_trap.MachineSteamTrapAuto;
import mods.railcraft.common.blocks.machine.steam_trap.MachineSteamTrapManual;
import mods.railcraft.common.blocks.machine.tank.MachineTankGauge;
import mods.railcraft.common.blocks.machine.tank.MachineTankValve;
import mods.railcraft.common.blocks.machine.tank.MachineTankWall;
import mods.railcraft.common.blocks.machine.tank.TankMaterial;
import mods.railcraft.common.blocks.machine.tank_water.MachineTankWater;
import mods.railcraft.common.blocks.machine.trade_station.MachineTradeStation;
import mods.railcraft.common.blocks.machine.turbine.MachineTurbine;
import mods.railcraft.common.blocks.machine.wire.MachineWire;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.modules.ModuleManager.Module;
import mods.railcraft.common.util.collections.ItemMap;

public class Machines {

    public static final Map<TankMaterial, Machine> tankWalls = createTankWalls();
    public static final Map<TankMaterial, Machine> tankValves = createTankValves();
    public static final Map<TankMaterial, Machine> tankGauges = createTankGauges();

    public static final MachineAnchor WORLD_ANCHOR = new MachineAnchor(
            TileAnchorWorld.class,
            "world",
            3,
            RailcraftConfig.anchorFuelWorld);
    public static final MachineAnchor PERSONAL_ANCHOR = new MachineAnchor(
            TileAnchorPersonal.class,
            "personal",
            3,
            RailcraftConfig.anchorFuelPersonal);
    public static final MachineAnchor ADMIN_ANCHOR = new MachineAnchor(
            TileAnchorAdmin.class,
            "admin",
            2,
            new ItemMap<Float>());
    public static final MachineAnchor PASSIVE_ANCHOR = new MachineAnchor(
            TileAnchorPassive.class,
            "passive",
            2,
            RailcraftConfig.anchorFuelPassive);
    public static final Machine SENTINEL = new MachineSentinel();

    public static final Machine TURBINE = new MachineTurbine();
    public static final Machine STEAM_OVEN = new MachineSteamOven();
    public static final Machine SMOKER = new MachineSmoker();
    public static final Machine TRADE_STATION = new MachineTradeStation();
    public static final Machine COKE_OVEN = new MachineCokeOven();
    public static final Machine ROLLING_MACHINE = new MachineRollingMachine();
    public static final Machine STEAM_TRAP_MANUAL = new MachineSteamTrapManual();
    public static final Machine STEAM_TRAP_AUTO = new MachineSteamTrapAuto();
    public static final Machine FEED_STATION = new MachineFeedStation();
    public static final Machine BLAST_FURNACE = new MachineBlastFurnace();
    public static final Machine TANK_WATER = new MachineTankWater();
    public static final Machine ROCK_CRUSHER = new MachineRockCrusher();

    public static final Machine BOILER_TANK_LOW_PRESSURE = new MachineBoilerTank(TankPressure.LOW);
    public static final Machine BOILER_TANK_HIGH_PRESSURE = new MachineBoilerTank(TankPressure.HIGH);
    public static final Machine BOILER_FIREBOX_SOLID = new MachineBoilerFirebox(FireboxType.SOLID);
    public static final Machine BOILER_FIREBOX_LIQUID = new MachineBoilerFirebox(FireboxType.LIQUID);

    public static final Machine ENGINE_STEAM_HOBBY = new MachineSteamEngine(EngineType.HOBBY);
    public static final Machine ENGINE_STEAM_LOW = new MachineSteamEngine(EngineType.LOW);
    public static final Machine ENGINE_STEAM_HIGH = new MachineSteamEngine(EngineType.HIGH);

    public static final Machine VOID_CHEST = new MachineChest(TileChestVoid.class, "void");
    public static final Machine METALS_CHEST = new MachineChest(TileChestMetals.class, "metals");

    public static final Machine WIRE = new MachineWire();

    public static final Machine ELECTRIC_FEEDER = new MachineElectricFeeder();
    public static final Machine ELECTRIC_FEEDER_ADMIN = new MachineElectricFeederAdmin();

    public static final Machine ADMIN_STEAM_PRODUCER = new MachineAdminSteamProducer();

    public static final Machine FORCE_TRACK_EMITTER = new MachineForceTrackEmitter();
    public static final Machine FLUX_TRANSFORMER = new MachineFluxTransformer();

    public static final Machine ENGRAVING_BENCH = new MachineEngravingBench();

    public static final Machine ITEM_LOADER = new MachineItemLoader();
    public static final Machine ITEM_UNLOADER = new MachineItemUnloader();

    public static final Machine ITEM_LOADER_ADVANCED = new MachineSideLoader(
            Module.TRANSPORT,
            TileItemLoaderAdvanced.class,
            "loader.item.advanced");
    public static final Machine ITEM_UNLOADER_ADVANCED = new MachineSideLoader(
            Module.TRANSPORT,
            TileItemUnloaderAdvanced.class,
            "unloader.item.advanced");

    public static final MachineLiquidLoader FLUID_LOADER = new MachineLiquidLoader();
    public static final MachineLiquidUnloader FLUID_UNLOADER = new MachineLiquidUnloader();

    public static final Machine ENERGY_LOADER = new MachineSideLoader(
            Module.IC2,
            TileEnergyLoader.class,
            "loader.energy");
    public static final Machine ENERGY_UNLOADER = new MachineSideLoader(
            Module.IC2,
            TileEnergyUnloader.class,
            "unloader.energy");

    public static final Machine CART_DISPENSER = new MachineSideLoader(
            Module.AUTOMATION,
            TileDispenserCart.class,
            "dispenser.cart");
    public static final Machine TRAIN_DISPENSER = new MachineSideLoader(
            Module.TRAIN,
            TileDispenserTrain.class,
            "dispenser.train");

    public static final Machine RF_LOADER = new MachineRFLoader(TileRFLoader.class, "loader.rf");
    public static final Machine RF_UNLOADER = new MachineRFLoader(TileRFUnloader.class, "unloader.rf");

    private static Map<TankMaterial, Machine> createTankWalls() {
        Map<TankMaterial, Machine> map = new TreeMap<TankMaterial, Machine>();
        for (TankMaterial material : TankMaterial.values()) {
            map.put(material, new MachineTankWall(material));
        }
        return map;
    }

    private static Map<TankMaterial, Machine> createTankGauges() {
        Map<TankMaterial, Machine> map = new TreeMap<TankMaterial, Machine>();
        for (TankMaterial material : TankMaterial.values()) {
            map.put(material, new MachineTankGauge(material));
        }
        return map;
    }

    private static Map<TankMaterial, Machine> createTankValves() {
        Map<TankMaterial, Machine> map = new TreeMap<TankMaterial, Machine>();
        for (TankMaterial material : TankMaterial.values()) {
            map.put(material, new MachineTankValve(material));
        }
        return map;
    }
}
