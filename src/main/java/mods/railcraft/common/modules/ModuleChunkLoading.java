/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.modules;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;

import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.Machines;
import mods.railcraft.common.carts.EnumCart;
import mods.railcraft.common.core.Railcraft;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.plugins.forge.CraftingPlugin;
import mods.railcraft.common.util.misc.ChunkManager;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class ModuleChunkLoading extends RailcraftModule {

    @Override
    public void initFirst() {
        ForgeChunkManager.setForcedChunkLoadingCallback(Railcraft.getMod(), ChunkManager.getInstance());
        MinecraftForge.EVENT_BUS.register(ChunkManager.getInstance());

        Machine worldAnchor = Machines.WORLD_ANCHOR;
        if (worldAnchor != null) {
            if (worldAnchor.getBlock() != null && RailcraftConfig.canCraftAnchors()) {
                CraftingPlugin.addShapedRecipe(
                        worldAnchor.getItem(),
                        "gog",
                        "dpd",
                        "gog",
                        'd',
                        "gemDiamond",
                        'g',
                        "ingotGold",
                        'p',
                        Items.ender_pearl,
                        'o',
                        new ItemStack(Blocks.obsidian));
            }
        }

        Machine personalAnchor = Machines.PERSONAL_ANCHOR;
        if (personalAnchor != null) {
            if (personalAnchor.getBlock() != null && RailcraftConfig.canCraftPersonalAnchors()) {
                CraftingPlugin.addShapedRecipe(
                        personalAnchor.getItem(),
                        "gog",
                        "dpd",
                        "gog",
                        'd',
                        "gemEmerald",
                        'g',
                        "ingotGold",
                        'p',
                        Items.ender_pearl,
                        'o',
                        new ItemStack(Blocks.obsidian));
            }
        }

        Machine passiveAnchor = Machines.PASSIVE_ANCHOR;
        if (passiveAnchor != null) {
            if (passiveAnchor.getBlock() != null && RailcraftConfig.canCraftPassiveAnchors()) {
                CraftingPlugin.addShapedRecipe(
                        passiveAnchor.getItem(),
                        "gog",
                        "dpd",
                        "gog",
                        'd',
                        "dyeCyan",
                        'g',
                        "ingotGold",
                        'p',
                        Items.ender_pearl,
                        'o',
                        new ItemStack(Blocks.obsidian));
            }
        }
        Machine sentinel = Machines.SENTINEL;
        if (sentinel.isAvailable()) {
            Block block = sentinel.getBlock();
            if (block != null) {
                ItemStack stack = sentinel.getItem();
                if (RailcraftConfig.canCraftAnchors()) {
                    CraftingPlugin.addShapedRecipe(
                            stack,
                            " p ",
                            " o ",
                            "ogo",
                            'g',
                            "ingotGold",
                            'p',
                            Items.ender_pearl,
                            'o',
                            new ItemStack(Blocks.obsidian));
                }
            }
        }

        // Define Anchor Cart
        EnumCart cart = EnumCart.ANCHOR;
        if (Machines.WORLD_ANCHOR.isAvailable() && cart.setup()) {
            ItemStack anchor = Machines.WORLD_ANCHOR.getItem();
            if (RailcraftConfig.canCraftAnchors()) {
                CraftingPlugin.addShapedRecipe(cart.getCartItem(), "A", "M", 'A', anchor, 'M', Items.minecart);
                CraftingPlugin.addShapelessRecipe(new ItemStack(Items.minecart), cart.getCartItem());
            }
            cart.setContents(anchor);
        }

        // Define Personal Anchor Cart
        cart = EnumCart.ANCHOR_PERSONAL;
        if (Machines.PERSONAL_ANCHOR.isAvailable() && cart.setup()) {
            ItemStack anchor = Machines.PERSONAL_ANCHOR.getItem();
            if (RailcraftConfig.canCraftPersonalAnchors()) {
                CraftingPlugin.addShapedRecipe(cart.getCartItem(), "A", "M", 'A', anchor, 'M', Items.minecart);
                CraftingPlugin.addShapelessRecipe(new ItemStack(Items.minecart), cart.getCartItem());
            }
            cart.setContents(anchor);
        }

        // Define Admin Anchor Cart
        cart = EnumCart.ANCHOR_ADMIN;
        if (Machines.ADMIN_ANCHOR.isAvailable() && cart.setup()) {
            ItemStack anchor = Machines.ADMIN_ANCHOR.getItem();
            cart.setContents(anchor);
        }
    }
}
