package mods.railcraft.api.core.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Interface for Items that work like the Engineer's overall in lowering locomotive damage and providing Electric Track
 * resistance.
 * 
 * This likely doesn't need to be implemented by anything with high armor value and GT hazmat functionality, but for
 * those mods that want to replicate this specific behaviour, this is appropriate.
 * 
 * Reminder that if the GREGTECH module is enabled and Gregtech is installed, the Electric Track functionality gets
 * replaced with the Electric Hazmat system.
 */
public interface ISafetyPants {

    // Test if particular pants should block damage right now
    public boolean blocksElectricTrackDamage(ItemStack pants);

    /*
     * Electric track shock callback For damage callbacks upon getting shocked by electric tracks. This does *not* get
     * called when the GREGTECH module is active and Gregtech is installed, as the Hazmat system does not use damage
     * (yet).
     */
    public void onShock(ItemStack pants, EntityPlayer player);

    // Test if particular pants should block damage right now
    public boolean lowersLocomotiveDamage(ItemStack pants);

    /* Locomotive hit callback */
    public void onHitLocomotive(ItemStack pants, EntityPlayer player);
}
