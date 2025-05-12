/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.modules;

import net.minecraft.init.Items;
import net.minecraftforge.oredict.ShapedOreRecipe;

import mods.railcraft.api.crafting.RailcraftCraftingManager;
import mods.railcraft.common.blocks.frame.BlockFrame;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;
import mods.railcraft.common.items.ItemElectricMeter;
import mods.railcraft.common.items.ItemPlate.EnumPlate;
import mods.railcraft.common.items.RailcraftItem;
import mods.railcraft.common.items.RailcraftPartItems;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import mods.railcraft.common.util.crafting.RotorRepairRecipe;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info/>
 */
public class ModuleElectricity extends RailcraftModule {

    @Override
    public void initFirst() {
        ItemElectricMeter.register();
        BlockFrame.registerBlock();

        Machine turbine = Machines.TURBINE;
        if (turbine != null) {
            CraftingPlugin.addShapedRecipe(
                    turbine.getItem(3),
                    "BPB",
                    "P P",
                    "BPB",
                    'P',
                    RailcraftItem.plate.getRecipeObject(EnumPlate.STEEL),
                    'B',
                    "blockSteel");

            RailcraftPartItems.getTurbineRotor();

            CraftingPlugin.addRecipe(new RotorRepairRecipe());

            // ItemStack rotor = RailcraftPartItems.getTurbineRotor();
            // rotor.setItemDamage(25000);
            // CraftingPlugin.addShapelessRecipe(rotor, RailcraftPartItems.getTurbineRotor());
        }

        Machine electric_feeder = Machines.ELECTRIC_FEEDER;
        if (electric_feeder != null) CraftingPlugin.addShapedRecipe(
                electric_feeder.getItem(),
                "PCP",
                "CCC",
                "PCP",
                'P',
                RailcraftItem.plate.getRecipeObject(EnumPlate.TIN),
                'C',
                "ingotCopper");

        electric_feeder = Machines.ELECTRIC_FEEDER_ADMIN;

        Machine forceTrackEmitter = Machines.FORCE_TRACK_EMITTER;
        if (forceTrackEmitter.getBlock() != null) CraftingPlugin.addShapedRecipe(
                forceTrackEmitter.getItem(),
                "PCP",
                "CDC",
                "PCP",
                'P',
                RailcraftItem.plate.getRecipeObject(EnumPlate.TIN),
                'D',
                "blockDiamond",
                'C',
                "ingotCopper");

        Machine fluxTransformer = Machines.FLUX_TRANSFORMER;
        if (fluxTransformer.getBlock() != null) CraftingPlugin.addShapedRecipe(
                fluxTransformer.getItem(2),
                "PGP",
                "GRG",
                "PGP",
                'P',
                RailcraftItem.plate.getRecipeObject(EnumPlate.COPPER),
                'G',
                "ingotGold",
                'R',
                "blockRedstone");

        Machine wire = Machines.WIRE;
        if (wire.getBlock() != null) RailcraftCraftingManager.rollingMachine.getRecipeList().add(
                new ShapedOreRecipe(
                        wire.getItem(8),
                        "LPL",
                        "PCP",
                        "LPL",
                        'C',
                        "blockCopper",
                        'P',
                        Items.paper,
                        'L',
                        "ingotLead"));
    }
}
