/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

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
import mods.railcraft.common.plugins.forge.RailcraftRegistry;

public class RailcraftBlocks {

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
