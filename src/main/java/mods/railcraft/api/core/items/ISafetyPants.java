package mods.railcraft.api.core.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ISafetyPants {

    // Test if particular pants should block damage right now
    public boolean blocksElectricTrackDamage(ItemStack pants);

    // Electric track shock callback
    public void onShock(ItemStack pants, EntityPlayer player);

    // Test if particular pants should block damage right now
    public boolean lowersLocomotiveDamage(ItemStack pants);

    // Locomotive hit callback
    public void onHitLocomotive(ItemStack pants, EntityPlayer player);
}
