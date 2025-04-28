/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.BlockMultiMachine;
import mods.railcraft.common.blocks.machine.ItemMachine;
import mods.railcraft.common.blocks.machine.ItemMultiMachine;
import mods.railcraft.common.blocks.machine.alpha.EnumMachineAlpha;
import mods.railcraft.common.blocks.machine.alpha.MachineProxyAlpha;
import mods.railcraft.common.blocks.machine.beta.EnumMachineBeta;
import mods.railcraft.common.blocks.machine.beta.MachineProxyBeta;
import mods.railcraft.common.blocks.machine.chest.MachineProxyChestMetals;
import mods.railcraft.common.blocks.machine.chest.MachineProxyChestVoid;
import mods.railcraft.common.blocks.machine.engine.EngineType;
import mods.railcraft.common.blocks.machine.engine.MachineProxyEngine;
import mods.railcraft.common.blocks.machine.epsilon.EnumMachineEpsilon;
import mods.railcraft.common.blocks.machine.epsilon.MachineProxyEpsilon;
import mods.railcraft.common.blocks.machine.gamma.EnumMachineGamma;
import mods.railcraft.common.blocks.machine.gamma.MachineProxyGamma;
import mods.railcraft.common.blocks.machine.sentinel.MachineProxySentinel;
import mods.railcraft.common.blocks.machine.wire.MachineProxyWire;
import mods.railcraft.common.blocks.machine.zeta.EnumMachineEta;
import mods.railcraft.common.blocks.machine.zeta.EnumMachineZeta;
import mods.railcraft.common.blocks.machine.zeta.MachineProxyEta;
import mods.railcraft.common.blocks.machine.zeta.MachineProxyZeta;
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
    private static Block blockMachineBeta;
    private static Block blockMachineGamma;
    private static Block blockEngineHobby;
    private static Block blockEngineLow;
    private static Block blockEngineHigh;
    private static Block blockMachineWire;
    private static Block blockMachineEpsilon;
    private static Block blockMachineSentinel;
    private static Block blockMachineChestVoid;
    private static Block blockMachineChestMetals;
    private static Block blockTrack;
    private static Block blockRailElevator;
    private static Block blockSignal;
    private static Block blockMachineZeta;
    private static Block blockMachineEta;

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

    public static Block registerBlockMachineBeta() {
        if (blockMachineBeta == null && RailcraftConfig.isBlockEnabled("machine.beta")) {

            int renderId = Railcraft.getProxy().getRenderId();
            int[] lightOpacity = new int[16];
            Arrays.fill(lightOpacity, 255);
            lightOpacity[EnumMachineBeta.TANK_IRON_WALL.ordinal()] = 0;
            lightOpacity[EnumMachineBeta.TANK_IRON_VALVE.ordinal()] = 0;
            lightOpacity[EnumMachineBeta.TANK_IRON_GAUGE.ordinal()] = 0;
            lightOpacity[EnumMachineBeta.TANK_STEEL_WALL.ordinal()] = 0;
            lightOpacity[EnumMachineBeta.TANK_STEEL_VALVE.ordinal()] = 0;
            lightOpacity[EnumMachineBeta.TANK_STEEL_GAUGE.ordinal()] = 0;
            lightOpacity[EnumMachineBeta.BOILER_TANK_LOW_PRESSURE.ordinal()] = 0;
            lightOpacity[EnumMachineBeta.BOILER_TANK_HIGH_PRESSURE.ordinal()] = 0;
            blockMachineBeta = new BlockMultiMachine(renderId, new MachineProxyBeta(), false, lightOpacity)
                    .setBlockName("railcraft.machine.beta");
            RailcraftRegistry.register(blockMachineBeta, ItemMultiMachine.class);

            for (EnumMachineBeta type : EnumMachineBeta.values()) {
                blockMachineBeta.setHarvestLevel("pickaxe", 2, type.ordinal());
            }
        }
        return blockMachineBeta;
    }

    public static Block getBlockMachineBeta() {
        return blockMachineBeta;
    }

    public static Block registerBlockMachineSentinel() {
        if (blockMachineSentinel == null) {
            int renderId = Railcraft.getProxy().getRenderId();
            blockMachineSentinel = new BlockMachine(renderId, new MachineProxySentinel(), false, 255)
                    .setBlockName("railcraft.sentinel");
            RailcraftRegistry.register(blockMachineSentinel, ItemMachine.class);
            blockMachineSentinel.setHarvestLevel("pickaxe", 3, 0);
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
            blockMachineChestVoid.setHarvestLevel("pickaxe", 2, 0);
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
            blockMachineChestMetals.setHarvestLevel("pickaxe", 2, 0);
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
            engineBlock.setHarvestLevel("pickaxe", 2, 0);
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

    public static Block registerBlockMachineEpsilon() {
        if (blockMachineEpsilon == null && RailcraftConfig.isBlockEnabled("machine.epsilon")) {
            int[] lightOpacity = new int[16];
            Arrays.fill(lightOpacity, 255);
            blockMachineEpsilon = new BlockMultiMachine(0, new MachineProxyEpsilon(), true, lightOpacity)
                    .setBlockName("railcraft.machine.epsilon");
            RailcraftRegistry.register(blockMachineEpsilon, ItemMultiMachine.class);

            for (EnumMachineEpsilon type : EnumMachineEpsilon.values()) {
                switch (type) {
                    default:
                        blockMachineEpsilon.setHarvestLevel("pickaxe", 2, type.ordinal());
                        // blockMachineEpsilon.setHarvestLevel("crowbar", 0, type.ordinal());
                }
            }
        }
        return blockMachineEpsilon;
    }

    public static Block getBlockMachineEpsilon() {
        return blockMachineEpsilon;
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

    public static Block registerBlockMachineZeta() {
        if (blockMachineZeta == null && RailcraftConfig.isBlockEnabled("machine.advtank")) {
            int renderId = Railcraft.getProxy().getRenderId();
            int[] lightOpacity = new int[EnumMachineZeta.values().length];
            Arrays.fill(lightOpacity, 255);
            for (int i = 0; i < EnumMachineZeta.values().length; i++) {
                lightOpacity[i] = 0;
            }
            blockMachineZeta = new BlockMultiMachine(renderId, new MachineProxyZeta(), false, lightOpacity)
                    .setBlockName("railcraft.machine.zeta");
            RailcraftRegistry.register(blockMachineZeta, ItemMultiMachine.class);

            for (EnumMachineZeta type : EnumMachineZeta.values()) {
                switch (type) {
                    default:
                        blockMachineZeta.setHarvestLevel("pickaxe", 3, type.ordinal());
                }
            }
        }
        return blockMachineZeta;
    }

    public static Block getBlockMachineZeta() {
        return blockMachineZeta;
    }

    public static Block registerBlockMachineEta() {
        if (blockMachineEta == null && RailcraftConfig.isBlockEnabled("machine.advtank")) {
            int renderId = Railcraft.getProxy().getRenderId();
            int[] lightOpacity = new int[EnumMachineEta.values().length];
            Arrays.fill(lightOpacity, 255);
            for (int i = 0; i < EnumMachineEta.values().length; i++) {
                lightOpacity[i] = 0;
            }
            blockMachineEta = new BlockMultiMachine(renderId, new MachineProxyEta(), false, lightOpacity)
                    .setBlockName("railcraft.machine.eta");
            RailcraftRegistry.register(blockMachineEta, ItemMultiMachine.class);

            for (EnumMachineEta type : EnumMachineEta.values()) {
                switch (type) {
                    default:
                        blockMachineEta.setHarvestLevel("pickaxe", 4, type.ordinal());
                }
            }
        }
        return blockMachineEta;
    }

    public static Block getBlockMachineEta() {
        return blockMachineEta;
    }
}
