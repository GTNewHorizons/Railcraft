/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.wire;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.client.util.textures.TextureAtlasSheet;
import mods.railcraft.common.blocks.RailcraftBlocks;
import mods.railcraft.common.blocks.machine.BoundingBoxManager;
import mods.railcraft.common.blocks.machine.IMachine;
import mods.railcraft.common.blocks.machine.TileMachineBase;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.gui.tooltips.ToolTip;
import mods.railcraft.common.modules.ModuleManager;
import mods.railcraft.common.modules.ModuleManager.Module;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;

/**
 *
 * @author CovertJaguar
 */
public class MachineWire implements IMachine {

    public static MachineWire INSTANCE = new MachineWire();
    private static final String tag = "wire";
    private IIcon[] texture = new IIcon[12];
    private final int[] textureInfo = { 0, 0, 0, 0, 0, 0 };
    private ToolTip tip;

    static {
        BoundingBoxManager.registerBoundingBox(INSTANCE, new TileWire.WireBoundingBox());
    }

    public boolean register() {
        if (RailcraftConfig.isSubBlockEnabled(getTag())) {
            RailcraftBlocks.registerBlockMachineDelta();
            return getBlock() != null;
        }
        return false;
    }

    @Override
    public boolean isDepreciated() {
        return this.getModule() == null;
    }

    @Override
    public IIcon getTexture(int index) {
        if (index < 0 || index >= texture.length) index = 0;
        return texture[index];
    }

    @SideOnly(Side.CLIENT)
    public static void registerIcons(IIconRegister iconRegister) {
        if (INSTANCE.isDepreciated()) return;
        INSTANCE.texture = new IIcon[6];
        IIcon[] icons = TextureAtlasSheet.unstitchIcons(iconRegister, "railcraft:" + tag, 1, 1);
        for (int i = 0; i < INSTANCE.texture.length; i++) {
            INSTANCE.texture[i] = icons[INSTANCE.textureInfo[i]];
        }
    }

    @Override
    public String getTag() {
        return "tile.railcraft.machine.delta." + tag;
    }

    @Override
    public Class<? extends TileMachineBase> getTileClass() {
        return TileWire.class;
    }

    public TileMachineBase getTileEntity() {
        try {
            return getTileClass().newInstance();
        } catch (Exception ex) {}
        return null;
    }

    @Override
    public ItemStack getItem() {
        return getItem(1);
    }

    @Override
    public ItemStack getItem(int qty) {
        Block block = getBlock();
        if (block == null) return null;
        return new ItemStack(block, qty);
    }

    public Module getModule() {
        return Module.ELECTRICITY;
    }

    @Override
    public Block getBlock() {
        return RailcraftBlocks.getBlockMachineDelta();
    }

    public boolean isEnabled() {
        return ModuleManager.isModuleLoaded(getModule()) && RailcraftConfig.isSubBlockEnabled(getTag());
    }

    @Override
    public boolean isAvaliable() {
        return getBlock() != null && isEnabled();
    }

    public ToolTip getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
        if (tip != null) return tip;
        String tipTag = getTag() + ".tip";
        if (LocalizationPlugin.hasTag(tipTag)) tip = ToolTip.buildToolTip(tipTag);
        return tip;
    }
}
