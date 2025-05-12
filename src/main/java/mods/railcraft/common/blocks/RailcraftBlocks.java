/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.BlockMultiMachine;
import mods.railcraft.common.blocks.machine.ItemMachine;
import mods.railcraft.common.blocks.machine.ItemMultiMachine;
import mods.railcraft.common.blocks.machine.alpha.EnumMachineAlpha;
import mods.railcraft.common.blocks.machine.alpha.MachineProxyAlpha;
import mods.railcraft.common.blocks.machine.blast_furnace.MachineProxyBlastFurnace;
import mods.railcraft.common.blocks.machine.boiler.MachineProxyBoilerTank;
import mods.railcraft.common.blocks.machine.boiler.MachineProxyFirebox;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerFirebox.FireboxType;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerTank.TankPressure;
import mods.railcraft.common.blocks.machine.chest.MachineProxyChestMetals;
import mods.railcraft.common.blocks.machine.chest.MachineProxyChestVoid;
import mods.railcraft.common.blocks.machine.coke_oven.MachineProxyCokeOven;
import mods.railcraft.common.blocks.machine.electric_feeder.MachineProxyElectricFeeder;
import mods.railcraft.common.blocks.machine.electric_feeder.MachineProxyElectricFeederAdmin;
import mods.railcraft.common.blocks.machine.engine.EngineType;
import mods.railcraft.common.blocks.machine.engine.MachineProxyEngine;
import mods.railcraft.common.blocks.machine.engraving_bench.MachineProxyEngravingBench;
import mods.railcraft.common.blocks.machine.feed_station.MachineProxyFeedStation;
import mods.railcraft.common.blocks.machine.flux_transformer.MachineProxyFluxTransformer;
import mods.railcraft.common.blocks.machine.force_track_emitter.MachineProxyForceTrackEmitter;
import mods.railcraft.common.blocks.machine.gamma.EnumMachineGamma;
import mods.railcraft.common.blocks.machine.gamma.MachineProxyGamma;
import mods.railcraft.common.blocks.machine.rock_crusher.MachineProxyRockCrusher;
import mods.railcraft.common.blocks.machine.rolling_machine.MachineProxyRollingMachine;
import mods.railcraft.common.blocks.machine.sentinel.MachineProxySentinel;
import mods.railcraft.common.blocks.machine.smoker.MachineProxySmoker;
import mods.railcraft.common.blocks.machine.steam_oven.MachineProxySteamOven;
import mods.railcraft.common.blocks.machine.steam_producer.MachineProxyAdminSteamProducer;
import mods.railcraft.common.blocks.machine.steam_trap.MachineProxySteamTrap;
import mods.railcraft.common.blocks.machine.tank.MachineProxyTankGauge;
import mods.railcraft.common.blocks.machine.tank.MachineProxyTankValve;
import mods.railcraft.common.blocks.machine.tank.MachineProxyTankWall;
import mods.railcraft.common.blocks.machine.tank.TankMaterial;
import mods.railcraft.common.blocks.machine.tank_water.MachineProxyTankWater;
import mods.railcraft.common.blocks.machine.trade_station.MachineProxyTradeStation;
import mods.railcraft.common.blocks.machine.turbine.MachineProxyTurbine;
import mods.railcraft.common.blocks.machine.wire.MachineProxyWire;
import mods.railcraft.common.blocks.signals.BlockSignalRailcraft;
import mods.railcraft.common.blocks.signals.ItemSignal;
import mods.railcraft.common.blocks.tracks.BlockTrack;
import mods.railcraft.common.blocks.tracks.BlockTrackElevator;
import mods.railcraft.common.blocks.tracks.ItemTrack;
import mods.railcraft.common.core.Railcraft;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.items.ItemRail.EnumRail;
import mods.railcraft.common.items.RailcraftItem;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import mods.railcraft.common.plugins.forge.CreativePlugin;
import mods.railcraft.common.plugins.forge.RailcraftRegistry;

public class RailcraftBlocks {

    private static Block blockMachineAlpha;
    private static Block blockMachineTurbine;
    private static Block blockMachineSteamOven;
    private static Block blockMachineSmoker;
    private static Block blockMachineTradeStation;
    private static Block blockMachineCokeOven;
    private static Block blockMachineRollingMachine;
    private static Block blockMachineSteamTrapManual;
    private static Block blockMachineSteamTrapAuto;
    private static Block blockMachineFeedStation;
    private static Block blockMachineBlastFurnace;
    private static Block blockMachineTankWater;
    private static Block blockMachineRockCrusher;
    private static Block blockMachineGamma;
    private static Map<TankMaterial, Block> tankWalls = new TreeMap<>();
    private static Map<TankMaterial, Block> tankGauges = new TreeMap<>();
    private static Map<TankMaterial, Block> tankValves = new TreeMap<>();
    private static Block blockBoilerTankLowPressure;
    private static Block blockBoilerTankHighPressure;
    private static Block blockBoilerFireboxSolid;
    private static Block blockBoilerFireboxLiquid;
    private static Block blockEngineHobby;
    private static Block blockEngineLow;
    private static Block blockEngineHigh;
    private static Block blockMachineWire;
    private static Block blockMachineElectricFeeder;
    private static Block blockMachineElectricFeederAdmin;
    private static Block blockMachineAdminSteamProducer;
    private static Block blockMachineForceTrackEmitter;
    private static Block blockMachineFluxTransformer;
    private static Block blockMachineEngravingBench;
    private static Block blockMachineSentinel;
    private static Block blockMachineChestVoid;
    private static Block blockMachineChestMetals;
    private static Block blockTrack;
    private static Block blockRailElevator;
    private static Block blockSignal;

    public static void registerBlockTrack() {
        if (blockTrack == null && RailcraftConfig.isBlockEnabled("track")) {
            int renderId = Railcraft.getProxy().getRenderId();
            blockTrack = new BlockTrack(renderId).setBlockName("railcraft.track");
            RailcraftRegistry.register(blockTrack, ItemTrack.class);
        }
    }

    public static Block getBlockTrack() {
        return blockTrack;
    }

    public static void registerBlockRailElevator() {
        if (blockRailElevator == null && RailcraftConfig.isBlockEnabled("elevator")) {
            int renderId = Railcraft.getProxy().getRenderId();
            blockRailElevator = new BlockTrackElevator(renderId).setBlockName("railcraft.track.elevator");
            RailcraftRegistry.register(blockRailElevator, ItemBlockRailcraft.class);
            blockRailElevator.setHarvestLevel("crowbar", 0);
            ItemStack stackElevator = new ItemStack(blockRailElevator, 8);
            CraftingPlugin.addShapedRecipe(
                    stackElevator,
                    "IRI",
                    "ISI",
                    "IRI",
                    'I',
                    RailcraftConfig.useOldRecipes() ? "ingotGold"
                            : RailcraftItem.rail.getRecipeObject(EnumRail.ADVANCED),
                    'S',
                    RailcraftConfig.useOldRecipes() ? "ingotIron"
                            : RailcraftItem.rail.getRecipeObject(EnumRail.STANDARD),
                    'R',
                    "dustRedstone");
        }
    }

    public static Block getBlockElevator() {
        return blockRailElevator;
    }

    public static Block registerBlockSteamOven() {
        if (blockMachineSteamOven == null) {
            blockMachineSteamOven = new BlockMachine(0, new MachineProxySteamOven(), true, 255)
                    .setBlockName("steam_oven");
            RailcraftRegistry.register(blockMachineSteamOven, ItemMachine.class);
            blockMachineSteamOven.setHarvestLevel("pickaxe", 2);
        }
        return blockMachineSteamOven;
    }

    public static Block getBlockSteamOven() {
        return blockMachineSteamOven;
    }

    public static Block registerBlockTurbine() {
        if (blockMachineTurbine == null) {
            blockMachineTurbine = new BlockMachine(0, new MachineProxyTurbine(), true, 255).setBlockName("turbine");
            RailcraftRegistry.register(blockMachineTurbine, ItemMachine.class);
            blockMachineTurbine.setHarvestLevel("pickaxe", 2);
        }
        return blockMachineTurbine;
    }

    public static Block getBlockTurbine() {
        return blockMachineTurbine;
    }

    public static Block registerBlockSmoker() {
        if (blockMachineSmoker == null) {
            blockMachineSmoker = new BlockMachine(0, new MachineProxySmoker(), true, 255).setBlockName("smoker");
            RailcraftRegistry.register(blockMachineSmoker, ItemMachine.class);
            blockMachineSmoker.setHarvestLevel("pickaxe", 2);
        }
        return blockMachineSmoker;
    }

    public static Block getBlockSmoker() {
        return blockMachineSmoker;
    }

    public static Block registerBlockTradeStation() {
        if (blockMachineTradeStation == null) {
            blockMachineTradeStation = new BlockMachine(0, new MachineProxyTradeStation(), true, 255)
                    .setBlockName("trade_station");
            RailcraftRegistry.register(blockMachineTradeStation, ItemMachine.class);
            blockMachineTradeStation.setHarvestLevel("pickaxe", 2);
        }
        return blockMachineTradeStation;
    }

    public static Block getBlockTradeStation() {
        return blockMachineTradeStation;
    }

    public static Block registerBlockCokeOven() {
        if (blockMachineCokeOven == null) {
            blockMachineCokeOven = new BlockMachine(0, new MachineProxyCokeOven(), true, 255).setBlockName("coke_oven");
            RailcraftRegistry.register(blockMachineCokeOven, ItemMachine.class);
            blockMachineCokeOven.setHarvestLevel("pickaxe", 0);
        }
        return blockMachineCokeOven;
    }

    public static Block getBlockCokeOven() {
        return blockMachineCokeOven;
    }

    public static Block registerBlockRollingMachine() {
        if (blockMachineRollingMachine == null) {
            blockMachineRollingMachine = new BlockMachine(0, new MachineProxyRollingMachine(), true, 255)
                    .setBlockName("rolling_machine");
            RailcraftRegistry.register(blockMachineRollingMachine, ItemMachine.class);
            blockMachineRollingMachine.setHarvestLevel("pickaxe", 2);
        }
        return blockMachineRollingMachine;
    }

    public static Block getBlockRollingMachine() {
        return blockMachineRollingMachine;
    }

    public static Block registerBlockSteamTrap(boolean auto) {
        Block steampTrap = getBlockSteamTrap(auto);
        if (steampTrap == null) {
            steampTrap = new BlockMachine(0, new MachineProxySteamTrap(auto), true, 255)
                    .setBlockName("steam_trap" + (auto ? ".auto" : ""));
            RailcraftRegistry.register(steampTrap, ItemMachine.class);
            steampTrap.setHarvestLevel("pickaxe", 2);
        }
        return steampTrap;
    }

    public static Block getBlockSteamTrap(boolean auto) {
        return auto ? blockMachineSteamTrapAuto : blockMachineSteamTrapManual;
    }

    public static Block registerBlockFeedStation() {
        if (blockMachineFeedStation == null) {
            blockMachineFeedStation = new BlockMachine(0, new MachineProxyFeedStation(), true, 255)
                    .setBlockName("feed_station");
            RailcraftRegistry.register(blockMachineFeedStation, ItemMachine.class);
            blockMachineFeedStation.setHarvestLevel("axe", 1);
        }
        return blockMachineFeedStation;
    }

    public static Block getBlockFeedStation() {
        return blockMachineFeedStation;
    }

    public static Block registerBlockBlastFurnace() {
        if (blockMachineBlastFurnace == null) {
            blockMachineBlastFurnace = new BlockMachine(0, new MachineProxyBlastFurnace(), true, 255)
                    .setBlockName("blast_furnace");
            RailcraftRegistry.register(blockMachineBlastFurnace, ItemMachine.class);
            blockMachineBlastFurnace.setHarvestLevel("pickaxe", 2);
        }
        return blockMachineBlastFurnace;
    }

    public static Block getBlockBlastFurnace() {
        return blockMachineBlastFurnace;
    }

    public static Block registerBlockTankWater() {
        if (blockMachineTankWater == null) {
            blockMachineTankWater = new BlockMachine(0, new MachineProxyTankWater(), true, 255)
                    .setBlockName("tank_water");
            RailcraftRegistry.register(blockMachineTankWater, ItemMachine.class);
            blockMachineTankWater.setHarvestLevel("axe", 0);
        }
        return blockMachineTankWater;
    }

    public static Block getBlockTankWater() {
        return blockMachineTankWater;
    }

    public static Block registerBlockRockCrusher() {
        if (blockMachineRockCrusher == null) {
            blockMachineRockCrusher = new BlockMachine(0, new MachineProxyRockCrusher(), true, 255)
                    .setBlockName("rock_crusher");
            RailcraftRegistry.register(blockMachineRockCrusher, ItemMachine.class);
            blockMachineRockCrusher.setHarvestLevel("pickaxe", 2);
        }
        return blockMachineRockCrusher;
    }

    public static Block getBlockRockCrusher() {
        return blockMachineRockCrusher;
    }

    public static Block registerBlockMachineAlpha() {
        if (blockMachineAlpha == null && RailcraftConfig.isBlockEnabled("machine.alpha")) {
            int[] lightOpacity = new int[16];
            Arrays.fill(lightOpacity, 255);
            blockMachineAlpha = new BlockMultiMachine(0, new MachineProxyAlpha(), true, lightOpacity)
                    .setBlockName("railcraft.machine.alpha");
            RailcraftRegistry.register(blockMachineAlpha, ItemMultiMachine.class);

            for (EnumMachineAlpha type : EnumMachineAlpha.values()) {
                switch (type) {
                    case WORLD_ANCHOR:
                    case PERSONAL_ANCHOR:
                        blockMachineAlpha.setHarvestLevel("pickaxe", 3, type.ordinal());
                        // blockMachineAlpha.setHarvestLevel("crowbar", 0, type.ordinal());
                        break;
                    default:
                        blockMachineAlpha.setHarvestLevel("pickaxe", 2, type.ordinal());
                        // blockMachineAlpha.setHarvestLevel("crowbar", 0, type.ordinal());
                }
            }
        }
        return blockMachineAlpha;
    }

    public static Block getBlockMachineAlpha() {
        return blockMachineAlpha;
    }

    public static Block registerBlockTankWall(TankMaterial material) {
        Block wall = getBlockTankWall(material);
        if (wall == null) {
            int renderId = Railcraft.getProxy().getRenderId();
            wall = new BlockMachine(renderId, new MachineProxyTankWall(material), false, 0)
                    .setBlockName("railcraft.tank.wall." + material.name);
            RailcraftRegistry.register(wall, ItemMachine.class);
            wall.setHarvestLevel("pickaxe", 2);
            tankWalls.put(material, wall);
        }
        return wall;
    }

    public static Block getBlockTankWall(TankMaterial material) {
        return tankWalls.get(material);
    }

    public static Block registerBlockTankGauge(TankMaterial material) {
        Block gauge = getBlockTankGauge(material);
        if (gauge == null) {
            int renderId = Railcraft.getProxy().getRenderId();
            gauge = new BlockMachine(renderId, new MachineProxyTankGauge(material), false, 0)
                    .setBlockName("railcraft.tank.gauge." + material.name);
            RailcraftRegistry.register(gauge, ItemMachine.class);
            gauge.setHarvestLevel("pickaxe", 2);
            tankGauges.put(material, gauge);
        }
        return gauge;
    }

    public static Block getBlockTankGauge(TankMaterial material) {
        return tankGauges.get(material);
    }

    public static Block registerBlockTankValve(TankMaterial material) {
        Block valve = getBlockTankValve(material);
        if (valve == null) {
            int renderId = Railcraft.getProxy().getRenderId();
            valve = new BlockMachine(renderId, new MachineProxyTankValve(material), false, 0)
                    .setBlockName("railcraft.tank.valve." + material.name);
            RailcraftRegistry.register(valve, ItemMachine.class);
            valve.setHarvestLevel("pickaxe", 2);
            tankValves.put(material, valve);
        }
        return valve;
    }

    public static Block getBlockTankValve(TankMaterial material) {
        return tankValves.get(material);
    }

    public static Block getBlockFirebox(FireboxType fireboxType) {
        return switch (fireboxType) {
            case SOLID -> blockBoilerFireboxSolid;
            case LIQUID -> blockBoilerFireboxLiquid;
        };
    }

    public static Block registerBlockFirebox(FireboxType fireboxType) {
        Block firebox = getBlockFirebox(fireboxType);
        if (firebox == null) {
            int renderId = Railcraft.getProxy().getRenderId();
            firebox = new BlockMachine(renderId, new MachineProxyFirebox(fireboxType), true, 255)
                    .setBlockName("railcraft.boiler.firebox." + fireboxType.getName());
            RailcraftRegistry.register(firebox, ItemMachine.class);
            firebox.setHarvestLevel("pickaxe", 2);
        }
        return firebox;
    }

    public static Block getBlockBoilerTank(TankPressure pressure) {
        return switch (pressure) {
            case LOW -> blockBoilerTankLowPressure;
            case HIGH -> blockBoilerTankHighPressure;
        };
    }

    public static Block registerBlockBoilerTank(TankPressure pressure) {
        Block block = getBlockBoilerTank(pressure);
        if (block == null) {
            int renderId = Railcraft.getProxy().getRenderId();
            block = new BlockMachine(renderId, new MachineProxyBoilerTank(pressure), false, 0)
                    .setBlockName("railcraft.boiler.tank." + pressure.getName());
            RailcraftRegistry.register(block, ItemMachine.class);
            block.setHarvestLevel("pickaxe", 2);
        }
        return block;
    }

    public static Block registerBlockMachineSentinel() {
        if (blockMachineSentinel == null) {
            int renderId = Railcraft.getProxy().getRenderId();
            blockMachineSentinel = new BlockMachine(renderId, new MachineProxySentinel(), false, 255)
                    .setBlockName("railcraft.sentinel");
            RailcraftRegistry.register(blockMachineSentinel, ItemMachine.class);
            blockMachineSentinel.setHarvestLevel("pickaxe", 3);
        }
        return blockMachineSentinel;
    }

    public static Block getBlockMachineSentinel() {
        return blockMachineSentinel;
    }

    public static Block registerBlockMachineChestVoid() {
        if (blockMachineChestVoid == null) {
            int renderId = Railcraft.getProxy().getRenderId();
            blockMachineChestVoid = new BlockMachine(renderId, new MachineProxyChestVoid(), false, 0)
                    .setBlockName("railcraft.chest.void");
            RailcraftRegistry.register(blockMachineChestVoid, ItemMachine.class);
            blockMachineChestVoid.setHarvestLevel("pickaxe", 2);
        }
        return blockMachineChestVoid;
    }

    public static Block getBlockMachineChestVoid() {
        return blockMachineChestVoid;
    }

    public static Block registerBlockMachineChestMetals() {
        if (blockMachineChestMetals == null) {
            int renderId = Railcraft.getProxy().getRenderId();
            blockMachineChestMetals = new BlockMachine(renderId, new MachineProxyChestMetals(), false, 0)
                    .setBlockName("railcraft.chest.metals");
            RailcraftRegistry.register(blockMachineChestMetals, ItemMachine.class);
            blockMachineChestMetals.setHarvestLevel("pickaxe", 2);
        }
        return blockMachineChestMetals;
    }

    public static Block getBlockMachineChestMetals() {
        return blockMachineChestMetals;
    }

    public static Block registerBlockMachineGamma() {
        if (blockMachineGamma == null && RailcraftConfig.isBlockEnabled("machine.gamma")) {

            int[] lightOpacity = new int[16];
            Arrays.fill(lightOpacity, 255);
            lightOpacity[EnumMachineGamma.FLUID_LOADER.ordinal()] = 0;
            lightOpacity[EnumMachineGamma.FLUID_UNLOADER.ordinal()] = 0;
            blockMachineGamma = new BlockMultiMachine(0, new MachineProxyGamma(), false, lightOpacity)
                    .setBlockName("railcraft.machine.gamma");
            blockMachineGamma.setCreativeTab(CreativeTabs.tabTransport);
            RailcraftRegistry.register(blockMachineGamma, ItemMultiMachine.class);

            for (EnumMachineGamma type : EnumMachineGamma.values()) {
                switch (type) {
                    default:
                        blockMachineGamma.setHarvestLevel("pickaxe", 2, type.ordinal());
                        // blockMachineGamma.setHarvestLevel("crowbar", 0, type.ordinal());
                }
            }
        }
        return blockMachineGamma;
    }

    public static Block getBlockMachineGamma() {
        return blockMachineGamma;
    }

    public static Block registerBlockMachineWire() {
        if (blockMachineWire == null) {
            int renderId = Railcraft.getProxy().getRenderId();
            blockMachineWire = new BlockMachine(renderId, new MachineProxyWire(), false, 0)
                    .setBlockName("railcraft.wire");
            blockMachineWire.setCreativeTab(CreativePlugin.RAILCRAFT_TAB);
            RailcraftRegistry.register(blockMachineWire, ItemMachine.class);
            blockMachineWire.setHarvestLevel("pickaxe", 2);
        }
        return blockMachineWire;
    }

    public static Block getBlockMachineWire() {
        return blockMachineWire;
    }

    public static Block registerBlockEngine(EngineType engineType) {
        Block engineBlock = getBlockEngine(engineType);
        if (engineBlock == null) {
            engineBlock = new BlockMachine(
                    Railcraft.getProxy().getRenderId(),
                    new MachineProxyEngine(engineType),
                    false,
                    0).setBlockName("railcraft.engine." + engineType.getName());
            RailcraftRegistry.register(engineBlock, ItemMachine.class);
            engineBlock.setHarvestLevel("pickaxe", 2);
        }
        return engineBlock;
    }

    public static Block getBlockEngine(EngineType engineType) {
        return switch (engineType) {
            case HOBBY -> blockEngineHobby;
            case LOW -> blockEngineLow;
            case HIGH -> blockEngineHigh;
        };
    }

    public static Block registerBlockElectricFeeder(boolean admin) {
        Block blockFeeder = admin ? blockMachineElectricFeederAdmin : blockMachineElectricFeeder;
        if (blockFeeder == null) {
            blockFeeder = new BlockMachine(
                    0,
                    admin ? new MachineProxyElectricFeederAdmin() : new MachineProxyElectricFeeder(),
                    true,
                    255).setBlockName("railcraft.electric_feeder" + (admin ? ".admin" : ""));
            RailcraftRegistry.register(blockFeeder, ItemMachine.class);
            blockFeeder.setHarvestLevel("pickaxe", 2);
        }
        return blockFeeder;
    }

    public static Block getBlockElectricFeeder(boolean admin) {
        return admin ? blockMachineElectricFeederAdmin : blockMachineElectricFeeder;
    }

    public static Block registerBlockAdminSteamProducer() {
        if (blockMachineAdminSteamProducer == null) {
            blockMachineAdminSteamProducer = new BlockMachine(0, new MachineProxyAdminSteamProducer(), true, 255)
                    .setBlockName("railcraft.machine.admin_steam_producer");
            RailcraftRegistry.register(blockMachineAdminSteamProducer, ItemMachine.class);
            blockMachineAdminSteamProducer.setHarvestLevel("pickaxe", 2);
        }
        return blockMachineAdminSteamProducer;
    }

    public static Block getBlockAdminSteamProducer() {
        return blockMachineAdminSteamProducer;
    }

    public static Block registerBlockFluxTransformer() {
        if (blockMachineFluxTransformer == null) {
            blockMachineFluxTransformer = new BlockMachine(0, new MachineProxyFluxTransformer(), true, 255)
                    .setBlockName("railcraft.machine.flux_transformer");
            RailcraftRegistry.register(blockMachineFluxTransformer, ItemMachine.class);
            blockMachineFluxTransformer.setHarvestLevel("pickaxe", 2);
        }
        return blockMachineFluxTransformer;
    }

    public static Block getBlockFluxTransformer() {
        return blockMachineFluxTransformer;
    }

    public static Block registerBlockForceTrackEmitter() {
        if (blockMachineForceTrackEmitter == null) {
            blockMachineForceTrackEmitter = new BlockMachine(0, new MachineProxyForceTrackEmitter(), true, 255)
                    .setBlockName("railcraft.machine.force_track_emitter");
            RailcraftRegistry.register(blockMachineForceTrackEmitter, ItemMachine.class);
            blockMachineForceTrackEmitter.setHarvestLevel("pickaxe", 2);
        }
        return blockMachineForceTrackEmitter;
    }

    public static Block getBlockForceTrackEmitter() {
        return blockMachineForceTrackEmitter;
    }

    public static Block registerBlockEngravingBench() {
        if (blockMachineEngravingBench == null) {
            blockMachineEngravingBench = new BlockMachine(0, new MachineProxyEngravingBench(), true, 255)
                    .setBlockName("railcraft.machine.engraving_bench");
            RailcraftRegistry.register(blockMachineEngravingBench, ItemMachine.class);
            blockMachineEngravingBench.setHarvestLevel("pickaxe", 0);
        }
        return blockMachineEngravingBench;
    }

    public static Block getBlockEngravingBench() {
        return blockMachineEngravingBench;
    }

    public static void registerBlockSignal() {
        if (blockSignal == null && RailcraftConfig.isBlockEnabled("signal")) {
            int renderId = Railcraft.getProxy().getRenderId();
            blockSignal = new BlockSignalRailcraft(renderId);
            RailcraftRegistry.register(blockSignal, ItemSignal.class);
        }
    }

    public static Block getBlockSignal() {
        return blockSignal;
    }
}
