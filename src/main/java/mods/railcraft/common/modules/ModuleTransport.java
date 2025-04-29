/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.modules;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import mods.railcraft.common.blocks.detector.EnumDetector;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;
import mods.railcraft.common.blocks.machine.alpha.EnumMachineAlpha;
import mods.railcraft.common.blocks.machine.gamma.EnumMachineGamma;
import mods.railcraft.common.blocks.machine.tank.TankMaterial;
import mods.railcraft.common.blocks.machine.tank.Tanks;
import mods.railcraft.common.carts.EnumCart;
import mods.railcraft.common.items.ItemMagnifyingGlass;
import mods.railcraft.common.items.ItemNotepad;
import mods.railcraft.common.items.RailcraftToolItems;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import mods.railcraft.common.util.crafting.CartFilterRecipe;
import mods.railcraft.common.util.inventory.InvTools;
import mods.railcraft.common.util.misc.EnumColor;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class ModuleTransport extends RailcraftModule {

    @Override
    public void initFirst() {
        ItemNotepad.registerItem();
        if (ItemNotepad.item != null) {
            ItemStack magGlass = ItemMagnifyingGlass.getItem();
            CraftingPlugin.addShapedRecipe(
                    new ItemStack(ItemNotepad.item),
                    "IF",
                    "XP",
                    'I',
                    new ItemStack(Items.dye, 1, 0),
                    'F',
                    Items.feather,
                    'X',
                    magGlass,
                    'P',
                    Items.paper);
        }

        EnumMachineAlpha alpha = EnumMachineAlpha.TANK_WATER;
        if (alpha.register()) CraftingPlugin.addShapedRecipe(
                alpha.getItem(6),
                "WWW",
                "ISI",
                "WWW",
                'I',
                "ingotIron",
                'S',
                "slimeball",
                'W',
                "plankWood");

        Machines.initTanks();

        Machine voidChest = Machines.VOID_CHEST;
        if (voidChest.isAvailable()) CraftingPlugin.addShapedRecipe(
                voidChest.getItem(),
                "OOO",
                "OPO",
                "OOO",
                'O',
                new ItemStack(Blocks.obsidian),
                'P',
                new ItemStack(Items.ender_pearl));

        EnumMachineGamma itemLoader = EnumMachineGamma.ITEM_LOADER;
        if (itemLoader.register()) {
            ItemStack stack = itemLoader.getItem();
            ItemStack detector = EnumDetector.ITEM.getItem();
            if (detector == null) detector = new ItemStack(Blocks.stone_pressure_plate);
            CraftingPlugin.addShapedRecipe(
                    stack,
                    "SSS",
                    "SLS",
                    "SDS",
                    'S',
                    "cobblestone",
                    'D',
                    detector,
                    'L',
                    new ItemStack(Blocks.hopper));

            itemLoader = EnumMachineGamma.ITEM_LOADER_ADVANCED;
            if (itemLoader.register()) CraftingPlugin.addShapedRecipe(
                    itemLoader.getItem(),
                    "IRI",
                    "RLR",
                    "ISI",
                    'I',
                    "ingotSteel",
                    'R',
                    "dustRedstone",
                    'S',
                    RailcraftToolItems.getSteelShovel(),
                    'L',
                    stack);
        }

        EnumMachineGamma itemUnloader = EnumMachineGamma.ITEM_UNLOADER;
        if (itemUnloader.register()) {
            ItemStack stack = itemUnloader.getItem();
            ItemStack detector = EnumDetector.ITEM.getItem();
            if (detector == null) detector = new ItemStack(Blocks.stone_pressure_plate);
            CraftingPlugin.addShapedRecipe(
                    stack,
                    "SSS",
                    "SDS",
                    "SLS",
                    'S',
                    "cobblestone",
                    'D',
                    detector,
                    'L',
                    new ItemStack(Blocks.hopper));

            itemUnloader = EnumMachineGamma.ITEM_UNLOADER_ADVANCED;
            if (itemUnloader.register()) CraftingPlugin.addShapedRecipe(
                    itemUnloader.getItem(),
                    "IRI",
                    "RLR",
                    "ISI",
                    'I',
                    "ingotSteel",
                    'R',
                    "dustRedstone",
                    'S',
                    RailcraftToolItems.getSteelShovel(),
                    'L',
                    stack);
        }

        EnumMachineGamma liquidLoader = EnumMachineGamma.FLUID_LOADER;

        if (liquidLoader.register()) {
            ItemStack detector = EnumDetector.TANK.getItem();
            if (detector == null) detector = new ItemStack(Blocks.stone_pressure_plate);
            CraftingPlugin.addShapedRecipe(
                    liquidLoader.getItem(),
                    "GLG",
                    "G G",
                    "GDG",
                    'D',
                    detector,
                    'G',
                    "blockGlassColorless",
                    'L',
                    Blocks.hopper);
        }

        EnumMachineGamma liquidUnloader = EnumMachineGamma.FLUID_UNLOADER;
        if (liquidUnloader.register()) {
            ItemStack detector = EnumDetector.TANK.getItem();
            if (detector == null) detector = new ItemStack(Blocks.stone_pressure_plate);
            CraftingPlugin.addShapedRecipe(
                    liquidUnloader.getItem(),
                    "GDG",
                    "G G",
                    "GLG",
                    'D',
                    detector,
                    'G',
                    "blockGlassColorless",
                    'L',
                    Blocks.hopper);
        }

        EnumCart cart = EnumCart.TANK;

        if (cart.setup()) {
            Machine ironGauge = Tanks.getGauge(TankMaterial.IRON);
            if (ironGauge != null && ironGauge.isAvailable()) {
                CraftingPlugin
                        .addShapedRecipe(cart.getCartItem(), "T", "M", 'T', ironGauge.getItem(), 'M', Items.minecart);
                cart.setContents(getColorTank(ironGauge, EnumColor.WHITE, 1));
            } else {
                CraftingPlugin.addShapedRecipe(
                        cart.getCartItem(),
                        "GGG",
                        "GMG",
                        "GGG",
                        'G',
                        "blockGlassColorless",
                        'M',
                        Items.minecart);
                cart.setContents(new ItemStack(Blocks.glass, 8));
            }
            CraftingPlugin.addShapelessRecipe(new ItemStack(Items.minecart), cart.getCartItem());
            CraftingPlugin.addRecipe(new CartFilterRecipe());
        }

        cart = EnumCart.CARGO;

        if (cart.setup()) {
            CraftingPlugin
                    .addShapedRecipe(cart.getCartItem(), "B", "M", 'B', Blocks.trapped_chest, 'M', Items.minecart);
            CraftingPlugin.addShapelessRecipe(new ItemStack(Items.minecart), cart.getCartItem());
            CraftingPlugin.addRecipe(new CartFilterRecipe());
        }
    }

    private ItemStack getColorTank(Machine type, EnumColor color, int qty) {
        ItemStack stack = type.getItem(qty);
        return InvTools.setItemColor(stack, color);
    }
}
