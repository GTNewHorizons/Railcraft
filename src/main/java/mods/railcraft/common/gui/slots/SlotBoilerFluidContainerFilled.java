package mods.railcraft.common.gui.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

import mods.railcraft.api.fuel.FuelManager;
import mods.railcraft.common.fluids.FluidItemHelper;
import mods.railcraft.common.fluids.Fluids;

public class SlotBoilerFluidContainerFilled extends SlotFluidContainerFilled {

    public SlotBoilerFluidContainerFilled(IInventory iinventory, int slotIndex, int posX, int posY) {
        super(iinventory, slotIndex, posX, posY);
    }

    @Override
    public boolean isItemValid(ItemStack itemstack) {
        Fluid fluid = FluidItemHelper.getFluidInContainer(itemstack);
        return super.isItemValid(itemstack) && (FuelManager.getBoilerFuelValue(fluid) > 0 || Fluids.WATER.is(fluid));
    }
}
