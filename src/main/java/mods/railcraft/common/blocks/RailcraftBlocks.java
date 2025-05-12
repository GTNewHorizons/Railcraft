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
import mods.railcraft.common.blocks.machine.boiler.MachineProxyBoilerTank;
import mods.railcraft.common.blocks.machine.boiler.MachineProxyFirebox;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerFirebox.FireboxType;
import mods.railcraft.common.blocks.machine.boiler.TileBoilerTank.TankPressure;
import mods.railcraft.common.blocks.machine.chest.MachineProxyChestMetals;
import mods.railcraft.common.blocks.machine.chest.MachineProxyChestVoid;
import mods.railcraft.common.blocks.machine.electric_feeder.MachineProxyElectricFeeder;
import mods.railcraft.common.blocks.machine.electric_feeder.MachineProxyElectricFeederAdmin;
import mods.railcraft.common.blocks.machine.engine.EngineType;
import mods.railcraft.common.blocks.machine.engine.MachineProxyEngine;
import mods.railcraft.common.blocks.machine.engraving_bench.MachineProxyEngravingBench;
import mods.railcraft.common.blocks.machine.flux_transformer.MachineProxyFluxTransformer;
import mods.railcraft.common.blocks.machine.force_track_emitter.MachineProxyForceTrackEmitter;
import mods.railcraft.common.blocks.machine.gamma.EnumMachineGamma;
import mods.railcraft.common.blocks.machine.gamma.MachineProxyGamma;
import mods.railcraft.common.blocks.machine.sentinel.MachineProxySentinel;
import mods.railcraft.common.blocks.machine.steam_producer.MachineProxyAdminSteamProducer;
import mods.railcraft.common.blocks.machine.tank.MachineProxyTankGauge;
import mods.railcraft.common.blocks.machine.tank.MachineProxyTankValve;
import mods.railcraft.common.blocks.machine.tank.MachineProxyTankWall;
import mods.railcraft.common.blocks.machine.tank.TankMaterial;
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

    public static Block registerBlockMachineAlpha() {
        if (blockMachineAlpha == null && RailcraftConfig.isBlockEnabled("machine.alpha")) {
            int[] lightOpacity = new int[16];
            Arrays.fill(lightOpacity, 255);
            blockMachineAlpha = new BlockMultiMachine(0, new MachineProxyAlpha(), true, lightOpacity)
                    .setBlockName("railcraft.machine.alpha");
            RailcraftRegistry.register(blockMachineAlpha, ItemMultiMachine.class);

            for (EnumMachineAlpha type : EnumMachineAlpha.values()) {
                switch (type) {
                    case FEED_STATION:
                        blockMachineAlpha.setHarvestLevel("axe", 1, type.ordinal());
                        break;
                    case TANK_WATER:
                        blockMachineAlpha.setHarvestLevel("axe", 0, type.ordinal());
                        // blockMachineAlpha.setHarvestLevel("crowbar", 0, type.ordinal());
                        break;
                    case WORLD_ANCHOR:
                    case PERSONAL_ANCHOR:
                        blockMachineAlpha.setHarvestLevel("pickaxe", 3, type.ordinal());
                        // blockMachineAlpha.setHarvestLevel("crowbar", 0, type.ordinal());
                        break;
                    case COKE_OVEN:
                        blockMachineAlpha.setHarvestLevel("pickaxe", 0, type.ordinal());
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
            blockFeeder = new BlockMachine(0, admin ?
                new MachineProxyElectricFeederAdmin() :
                new MachineProxyElectricFeeder(), true, 255)
                .setBlockName("railcraft.electric_feeder" + (admin ? ".admin" : ""));
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
