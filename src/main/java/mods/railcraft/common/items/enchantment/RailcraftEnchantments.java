package mods.railcraft.common.items.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.monster.EntityCreeper;

import mods.railcraft.common.core.RailcraftConfig;

public class RailcraftEnchantments {

    public static Enchantment wrecking;
    public static Enchantment implosion;
    public static Enchantment destruction;

    public static void registerEnchantment() {
        wrecking = new EnchantmentDamageRailcraft("wrecking", RailcraftConfig.wreckingID(), 2, 1, 11, 20, null, 1.5f);
        implosion = new EnchantmentDamageRailcraft(
                "implosion",
                RailcraftConfig.implosionID(),
                2,
                5,
                8,
                20,
                EntityCreeper.class,
                3f);
        ((EnchantmentDamageRailcraft) implosion).registerEventHandler();
        destruction = new EnchantmentDestruction(RailcraftConfig.destructionID(), 1);
    }
}
