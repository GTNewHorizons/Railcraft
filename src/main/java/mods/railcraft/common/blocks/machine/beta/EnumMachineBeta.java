/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.beta;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.client.util.textures.TextureAtlasSheet;
import mods.railcraft.common.blocks.RailcraftBlocks;
import mods.railcraft.common.blocks.machine.IEnumMachine;
import mods.railcraft.common.blocks.machine.TileMachineBase;
import mods.railcraft.common.core.RailcraftConfig;
import mods.railcraft.common.gui.tooltips.ToolTip;
import mods.railcraft.common.modules.ModuleManager.Module;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;

/**
 *
 * @author CovertJaguar
 */
public enum EnumMachineBeta implements IEnumMachine {

    TANK_IRON_WALL(Module.TRANSPORT, "tank.iron.wall", TileTankIronWall.class, 2, 1, 0, 0, 1, 1, 1, 1),
    TANK_IRON_GAUGE(Module.TRANSPORT, "tank.iron.gauge", TileTankIronGauge.class, 1, 5, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4),
    TANK_IRON_VALVE(Module.TRANSPORT, "tank.iron.valve", TileTankIronValve.class, 4, 1, 0, 0, 1, 1, 1, 1, 2, 3),
    TANK_STEEL_WALL(Module.TRANSPORT, "tank.steel.wall", TileTankSteelWall.class, 2, 1, 0, 0, 1, 1, 1, 1),
    TANK_STEEL_GAUGE(Module.TRANSPORT, "tank.steel.gauge", TileTankSteelGauge.class, 1, 5, 0, 0, 0, 0, 0, 0, 1, 2, 3,
            4),
    TANK_STEEL_VALVE(Module.TRANSPORT, "tank.steel.valve", TileTankSteelValve.class, 4, 1, 0, 0, 1, 1, 1, 1, 2, 3);

    private final Module module;
    private final String tag;
    private final Class<? extends TileMachineBase> tile;
    private IIcon[] texture = new IIcon[12];
    private final int[] textureInfo;
    private static final List<EnumMachineBeta> creativeList = new ArrayList<EnumMachineBeta>();
    private static final EnumMachineBeta[] VALUES = values();
    private ToolTip tip;

    static {
        creativeList.add(TANK_IRON_WALL);
        creativeList.add(TANK_IRON_GAUGE);
        creativeList.add(TANK_IRON_VALVE);
        creativeList.add(TANK_STEEL_WALL);
        creativeList.add(TANK_STEEL_GAUGE);
        creativeList.add(TANK_STEEL_VALVE);
    }

    private EnumMachineBeta(Module module, String tag, Class<? extends TileMachineBase> tile, int... textureInfo) {
        this.module = module;
        this.tile = tile;
        this.tag = tag;
        this.textureInfo = textureInfo;
    }

    public boolean register() {
        if (RailcraftConfig.isSubBlockEnabled(getTag())) {
            RailcraftBlocks.registerBlockMachineBeta();
            return getBlock() != null;
        }
        return false;
    }

    public void setTexture(IIcon[] tex) {
        this.texture = tex;
    }

    public IIcon[] getTexture() {
        return texture;
    }

    @Override
    public IIcon getTexture(int index) {
        if (index < 0 || index >= texture.length) index = 0;
        return texture[index];
    }

    @SideOnly(Side.CLIENT)
    public static void registerIcons(IIconRegister iconRegister) {
        for (EnumMachineBeta machine : VALUES) {
            if (machine.isDepreciated()) continue;
            machine.texture = new IIcon[machine.textureInfo.length - 2];
            int columns = machine.textureInfo[0];
            int rows = machine.textureInfo[1];
            IIcon[] icons = TextureAtlasSheet.unstitchIcons(iconRegister, "railcraft:" + machine.tag, columns, rows);
            for (int i = 0; i < machine.texture.length; i++) {
                machine.texture[i] = icons[machine.textureInfo[i + 2]];
            }
        }
    }

    public static EnumMachineBeta fromId(int id) {
        if (id < 0 || id >= VALUES.length) id = 0;
        return VALUES[id];
    }

    public static List<EnumMachineBeta> getCreativeList() {
        return creativeList;
    }

    @Override
    public String getTag() {
        return "tile.railcraft.machine.beta." + tag;
    }

    @Override
    public Class getTileClass() {
        return tile;
    }

    @Override
    public Module getModule() {
        return module;
    }

    @Override
    public Block getBlock() {
        return RailcraftBlocks.getBlockMachineBeta();
    }

    public ToolTip getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
        if (tip != null) return tip;
        String tipTag = getTag() + ".tip";
        if (LocalizationPlugin.hasTag(tipTag)) tip = ToolTip.buildToolTip(tipTag);
        return tip;
    }
}
