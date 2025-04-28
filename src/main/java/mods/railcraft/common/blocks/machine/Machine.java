package mods.railcraft.common.blocks.machine;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.client.util.textures.TextureAtlasSheet;
import mods.railcraft.common.gui.tooltips.ToolTip;
import mods.railcraft.common.modules.ModuleManager.Module;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;

public class Machine implements IMachine {

    protected Block block;
    protected Module module;
    protected Class<? extends TileMachineBase> tile;
    protected String tag;
    private int[] textureInfo;
    private IIcon[] texture;
    private ToolTip tip;

    public Machine(Module module, Block block, Class<? extends TileMachineBase> tile, String tag, int... textureInfo) {
        this.module = module;
        this.block = block;
        this.tile = tile;
        this.tag = tag;
        this.textureInfo = textureInfo;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        if (isDepreciated()) return;
        texture = new IIcon[textureInfo.length - 2];
        int columns = textureInfo[0];
        int rows = textureInfo[1];
        IIcon[] icons = TextureAtlasSheet.unstitchIcons(iconRegister, "railcraft:" + tag, columns, rows);
        for (int i = 0; i < texture.length; i++) {
            texture[i] = icons[textureInfo[i + 2]];
        }
    }

    @Override
    public IIcon getTexture(int index) {
        if (index < 0 || index >= texture.length) index = 0;
        return texture[index];
    }

    @Override
    public Module getModule() {
        return module;
    }

    @Override
    public Block getBlock() {
        return block;
    }

    @Override
    public Class<? extends TileMachineBase> getTileClass() {
        return tile;
    }

    @Override
    public String getTag() {
        return "tile.railcraft.machine." + tag;
    }

    @Override
    public ToolTip getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
        if (tip != null) return tip;
        String tipTag = getTag() + ".tip";
        if (LocalizationPlugin.hasTag(tipTag)) tip = ToolTip.buildToolTip(tipTag);
        return tip;
    }
}
