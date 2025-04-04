package mods.railcraft.common.items.firestone;

import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;

public interface IItemFirestoneBurning {

    boolean shouldBurn(@NotNull ItemStack itemStack);
}
