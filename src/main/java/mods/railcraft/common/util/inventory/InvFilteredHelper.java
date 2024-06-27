package mods.railcraft.common.util.inventory;

import java.util.Collection;
import java.util.Collections;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import mods.railcraft.api.core.items.IStackFilter;
import mods.railcraft.common.util.inventory.filters.ArrayStackFilter;
import mods.railcraft.common.util.inventory.filters.InvertedStackFilter;
import mods.railcraft.common.util.inventory.filters.StackFilter;

public class InvFilteredHelper {

    private static final InvFilteredHelper ACCEPTS_ALL = new InvFilteredHelper(StackFilter.ALL);

    private final IStackFilter filter;

    private InvFilteredHelper(IStackFilter filter) {
        this.filter = filter;
    }

    public int countItems(IInventory inventory) {
        return countItems(Collections.singletonList(inventory));
    }

    public int countItems(Collection<IInventory> inventories) {
        return InvTools.countItems(inventories, filter);
    }

    public ItemStack moveOneItem(Collection<IInventory> sources, IInventory destination) {
        return moveOneItem(sources, Collections.singletonList(destination));
    }

    public ItemStack moveOneItem(IInventory source, Collection<IInventory> destinations) {
        return moveOneItem(Collections.singletonList(source), destinations);
    }

    /**
     * Attempts to move one item from a collection of inventories.
     */
    public ItemStack moveOneItem(Collection<IInventory> sources, Collection<IInventory> destinations) {
        for (IInventory source : sources) {
            for (IInventory dest : destinations) {
                ItemStack moved = InvTools.moveOneItem(source, dest, filter);
                if (moved != null) return moved;
            }
        }

        return null;
    }

    public InvFilteredHelper invert() {
        return new InvFilteredHelper(new InvertedStackFilter(filter));
    }

    public static InvFilteredHelper acceptAll() {
        return ACCEPTS_ALL;
    }

    public static InvFilteredHelper filteredByStacks(ItemStack... filters) {
        return filteredByStacks(false, false, filters);
    }

    public static InvFilteredHelper filteredByStacks(boolean matchNBT, boolean matchMetadata, ItemStack... filters) {
        return new InvFilteredHelper(new ArrayStackFilter(matchNBT, matchMetadata, filters));
    }
}
