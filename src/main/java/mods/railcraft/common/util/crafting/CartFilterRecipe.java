/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.util.crafting;

import javax.annotation.Nullable;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import mods.railcraft.common.carts.EntityCartFiltered;
import mods.railcraft.common.carts.EnumCart;
import mods.railcraft.common.carts.ICartType;
import mods.railcraft.common.fluids.FluidItemHelper;
import mods.railcraft.common.util.inventory.wrappers.IInvSlot;
import mods.railcraft.common.util.inventory.wrappers.InventoryIterator;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class CartFilterRecipe implements IRecipe {

    public enum FilterType {

        Cargo(EnumCart.CARGO),
        Tank(EnumCart.TANK) {

            @Override
            public boolean isAllowedFilterItem(ItemStack stack) {
                return FluidItemHelper.isFilledContainer(stack);
            }
        };

        public static FilterType[] VALUES = values();
        public final ICartType cartType;

        FilterType(ICartType cartType) {
            this.cartType = cartType;
        }

        public boolean isAllowedFilterItem(ItemStack stack) {
            return true;
        }

        @Nullable
        public static FilterType fromCartType(ICartType cartType) {
            if (cartType == null) return null;
            for (FilterType t : VALUES) {
                if (t.cartType == cartType) return t;
            }
            return null;
        }
    }

    @Override
    public boolean matches(InventoryCrafting grid, World world) {
        return getCraftingResult(grid) != null;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting grid) {
        ItemStack cartItem = null;
        ItemStack filterItem = null;
        FilterType filterType = null;
        int cartSlot = -1;
        int itemCount = 0;

        for (IInvSlot slot : InventoryIterator.getIterable(grid).notNull()) {
            itemCount++;
            ItemStack stack = slot.getStackInSlot();
            FilterType type = FilterType.fromCartType(EnumCart.getCartType(stack));
            if (type != null) {
                cartSlot = slot.getIndex();
                filterType = type;
                cartItem = stack.copy();
                cartItem.stackSize = 1;
            }
        }
        if (filterType == null || itemCount > 2) {
            return null;
        }
        for (IInvSlot slot : InventoryIterator.getIterable(grid).notNull()) {
            if (slot.getIndex() == cartSlot) {
                continue;
            }
            ItemStack stack = slot.getStackInSlot();
            if (filterType.isAllowedFilterItem(stack)) {
                filterItem = stack.copy();
                break;
            }
        }
        if (cartItem == null || filterItem == null) {
            return null;
        }

        if (filterItem.getItem().hasContainerItem(filterItem)) {
            /*
             * System.out.println("Has container item.");
             * System.out.println("Does Container Item Leave Crafting Grid: "+filterItem.getItem().
             * doesContainerItemLeaveCraftingGrid(filterItem)); ItemStack cont =
             * filterItem.getItem().getContainerItem(filterItem);
             * System.out.println("Container item: "+cont.getDisplayName()+" x"+cont.stackSize);
             */
            return null;
        } else {
            filterItem.stackSize = 1;
        }
        ItemStack output = EntityCartFiltered.addFilterToCartItem(cartItem, filterItem);
        return output;
    }

    @Override
    public int getRecipeSize() {
        return 2;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }
}
