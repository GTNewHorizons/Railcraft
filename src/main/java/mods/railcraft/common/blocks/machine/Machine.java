package mods.railcraft.common.blocks.machine;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.railcraft.client.util.textures.TextureAtlasSheet;
import mods.railcraft.common.gui.tooltips.ToolTip;
import mods.railcraft.common.modules.ModuleManager;
import mods.railcraft.common.modules.ModuleManager.Module;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;

public abstract class Machine {

    protected Block block;
    protected Module module;
    protected Class<? extends TileMachineBase> tile;
    protected String tag;
    private int[] textureInfo;
    protected IIcon[] texture;
    protected ToolTip tip;

    public Machine(Module module, Class<? extends TileMachineBase> tile, String tag, int... textureInfo) {
        this.module = module;
        this.tile = tile;
        this.tag = tag;
        this.textureInfo = textureInfo;
        MachineProxy proxy = new MachineProxy(this);
        this.block = createBlock(proxy);
    }

    protected abstract Block createBlock(MachineProxy proxy);

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        texture = new IIcon[textureInfo.length - 2];
        int columns = textureInfo[0];
        int rows = textureInfo[1];
        IIcon[] icons = TextureAtlasSheet.unstitchIcons(iconRegister, "railcraft:" + tag, columns, rows);
        for (int i = 0; i < texture.length; i++) {
            texture[i] = icons[textureInfo[i + 2]];
        }
    }

    public IIcon getTexture(int index) {
        if (index < 0 || index >= texture.length) index = 0;
        return texture[index];
    }

    public Module getModule() {
        return module;
    }

    public boolean isAvailable() {
        return getBlock() != null && ModuleManager.isModuleLoaded(getModule());
    }

    public Block getBlock() {
        return block;
    }

    public ItemStack getItem(int qty) {
        return new ItemStack(block, qty);
    }

    public TileEntity getTileEntity() {
        try {
            return tile.newInstance();
        } catch (Exception ex) {}
        return null;
    }

    public String getTag() {
        return "tile.railcraft.machine." + tag;
    }

    public ToolTip getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
        if (tip != null) return tip;
        String tipTag = getTag() + ".tip";
        if (LocalizationPlugin.hasTag(tipTag)) tip = ToolTip.buildToolTip(tipTag);
        return tip;
    }
}
