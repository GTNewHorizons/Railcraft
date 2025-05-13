package mods.railcraft.common.blocks.machine.anchor;

import java.util.Optional;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import mods.railcraft.common.blocks.machine.Machine;
import mods.railcraft.common.blocks.machine.TileMachineBase;
import mods.railcraft.common.carts.ItemCartAnchor;
import mods.railcraft.common.core.RailcraftConstants;
import mods.railcraft.common.gui.tooltips.ToolTip;
import mods.railcraft.common.modules.ModuleManager.Module;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;
import mods.railcraft.common.util.collections.ItemMap;

public class MachineAnchor extends Machine {

    private final ItemMap<Float> fuelMap;

    public MachineAnchor(Block block, Class<? extends TileMachineBase> tile, String tag,
            Optional<ItemMap<Float>> itemMap, int... textureInfo) {
        super(Module.CHUNK_LOADING, block, tile, tag, textureInfo);
        this.fuelMap = itemMap != null ? itemMap.get() : new ItemMap<Float>();
    }

    @Override
    public ToolTip getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
        if (tip != null) return tip;
        if (fuelMap != null && !fuelMap.isEmpty()) {
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
