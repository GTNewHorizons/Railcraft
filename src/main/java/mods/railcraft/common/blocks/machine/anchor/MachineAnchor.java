package mods.railcraft.common.blocks.machine.anchor;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import mods.railcraft.common.blocks.machine.BlockMachine;
import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.MachineProxy;
import mods.railcraft.common.carts.ItemCartAnchor;
import mods.railcraft.common.core.RailcraftConstants;
import mods.railcraft.common.gui.tooltips.ToolTip;
import mods.railcraft.common.modules.ModuleManager.Module;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;
import mods.railcraft.common.util.collections.ItemMap;

public class MachineAnchor extends Machine {

    protected String anchorTag;
    protected int miningLevel;
    private ItemMap<Float> fuelMap;

    public MachineAnchor(Class<? extends TileAnchorWorld> tile, String tag, int miningLevel, ItemMap<Float> fuelMap) {
        super(Module.CHUNK_LOADING, tile, "anchor." + tag, 3, 1, 0, 0, 1, 1, 1, 1, 2);
        this.fuelMap = fuelMap;
    }

    public ItemMap<Float> fuelMap() {
        return fuelMap;
    };

    public Block createBlock(MachineProxy proxy) {
        Block block = new BlockMachine(0, proxy, true, 255).setBlockName("railcraft." + tag);
        block.setHarvestLevel("pickaxe", miningLevel);
        return block;
    }

    @Override
    public ToolTip getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
        if (tip != null) return tip;
        if (fuelMap() != null && !fuelMap().isEmpty()) {
            addAnchorInfo(stack);
        }
        return tip;
    }

    private ToolTip addAnchorInfo(ItemStack stack) {
        ToolTip toolTip = new ToolTip();
        long fuel = ItemCartAnchor.getFuel(stack);
        double hours = (double) fuel / RailcraftConstants.TICKS_PER_HOUR;
        String format = LocalizationPlugin.translate("railcraft.gui.anchor.fuel.remaining");
        toolTip.add(String.format(format, hours));
        return toolTip;
    }
}
