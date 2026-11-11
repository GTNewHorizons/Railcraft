package mods.railcraft.common.gui.containers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import mods.railcraft.api.core.items.IStackFilter;
import mods.railcraft.common.blocks.machine.epsilon.TileAnchorProvider;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.gui.slots.SlotStackFilter;

public class ContainerAnchorProvider extends RailcraftContainer {

    public ContainerAnchorProvider(InventoryPlayer inventoryPlayer, TileAnchorProvider provider) {
        super(provider);
        addSlot(new SlotStackFilter(new IStackFilter() {

            @Override
            public boolean matches(ItemStack stack) {
                return RailcraftConfig.anchorFuelWorld.containsKey(stack);
            }
        }, provider, 0, 80, 24));

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 9; k++) {
                addSlot(new Slot(inventoryPlayer, k + i * 9 + 9, 8 + k * 18, 58 + i * 18));
            }
        }

        for (int j = 0; j < 9; j++) {
            addSlot(new Slot(inventoryPlayer, j, 8 + j * 18, 116));
        }
    }
}
