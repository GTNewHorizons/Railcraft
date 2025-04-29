package mods.railcraft.common.blocks.machine;

import java.util.TreeMap;

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
import mods.railcraft.common.blocks.machine.tank.MachineTank;
import mods.railcraft.common.blocks.machine.tank.TankMaterial;
import mods.railcraft.common.blocks.machine.tank.TileTankBase;
import mods.railcraft.common.blocks.machine.tank.TileTankGauge;
import mods.railcraft.common.blocks.machine.tank.TileTankValve;
import mods.railcraft.common.blocks.machine.tank.TileTankWall;
import mods.railcraft.common.blocks.machine.wire.TileWire;
import mods.railcraft.common.modules.ModuleManager.Module;

public class Machines {

    public static final TreeMap<TankMaterial, Machine> tankWalls = new TreeMap<>();
    public static final TreeMap<TankMaterial, Machine> tankValves = new TreeMap<>();
    public static final TreeMap<TankMaterial, Machine> tankGauges = new TreeMap<>();

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

    private static Machine registerMachine(Module module, Block block, Class<? extends TileMachineBase> tileClass,
            String tag, int... textureInfo) {
        if (block != null) {
            return new Machine(module, block, tileClass, tag, textureInfo);
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
