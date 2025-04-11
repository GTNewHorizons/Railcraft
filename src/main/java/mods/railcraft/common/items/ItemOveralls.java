/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Optional;
import gregtech.api.hazards.Hazard;
import gregtech.api.hazards.IHazardProtector;
import mods.railcraft.api.core.items.ISafetyPants;
import mods.railcraft.common.core.RailcraftConstants;
import mods.railcraft.common.gui.tooltips.ToolTip;
import mods.railcraft.common.plugins.forge.CreativePlugin;
import mods.railcraft.common.util.inventory.InvTools;
import mods.railcraft.common.util.misc.MiscTools;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
@Optional.InterfaceList(
        value = { @Optional.Interface(iface = "gregtech.api.hazards.IHazardProtector", modid = "gregtech"), })
public class ItemOveralls extends ItemArmor implements ISafetyPants, IHazardProtector {

    private static final ItemStack BLUE_CLOTH = new ItemStack(Blocks.wool, 1, 3);
    private static final String TEXTURE = RailcraftConstants.ARMOR_TEXTURE_FOLDER + "overalls.png";

    public static boolean isPlayerWearing(EntityPlayer player) {
        ItemStack pants = player.getCurrentArmor(MiscTools.ArmorSlots.LEGS.ordinal());
        return pants != null && pants.getItem() instanceof ItemOveralls;
    }

    public ItemOveralls() {
        super(ItemMaterials.OVERALLS, 0, 2);
        setCreativeTab(CreativePlugin.RAILCRAFT_TAB);
    }

    @Override
    public boolean blocksElectricTrackDamage(ItemStack pants) {
        return true;
    }

    @Override
    public void onShock(ItemStack pants, EntityPlayer player) {
        player.setCurrentItemOrArmor(MiscTools.ArmorSlots.LEGS.ordinal() + 1, InvTools.damageItem(pants, 1));
    }

    @Override
    public boolean lowersLocomotiveDamage(ItemStack pants) {
        return true;
    }

    @Override
    public void onHitLocomotive(ItemStack pants, EntityPlayer player) {
        player.setCurrentItemOrArmor(MiscTools.ArmorSlots.LEGS.ordinal() + 1, InvTools.damageItem(pants, 5));
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("railcraft:" + MiscTools.cleanTag(getUnlocalizedName()));
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return TEXTURE;
    }

    @Override
    public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack) {
        return InvTools.isItemEqual(stack, BLUE_CLOTH);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean adv) {
        super.addInformation(stack, player, info, adv);
        ToolTip tip = ToolTip.buildToolTip(stack.getUnlocalizedName() + ".tip");
        if (tip != null) info.addAll(tip.convertToStrings());
    }

    @Override
    @Optional.Method(modid = "gregtech")
    public boolean protectsAgainst(ItemStack itemStack, Hazard hazard) {
        return hazard == Hazard.ELECTRICAL;
    }
}
