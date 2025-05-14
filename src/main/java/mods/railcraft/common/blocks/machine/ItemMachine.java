package mods.railcraft.common.blocks.machine;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import mods.railcraft.common.blocks.ItemBlockRailcraft;
import mods.railcraft.common.gui.tooltips.ToolTip;

public class ItemMachine extends ItemBlockRailcraft {

    private final BlockMachine machineBlock;

    public ItemMachine(Block block) {
        super(block);
        this.machineBlock = (BlockMachine) block;
        setUnlocalizedName("railcraft.machine");
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return machineBlock.getMachineProxy().getMachine().getTag();
    }

    @Override
    public ToolTip getToolTip(ItemStack stack, EntityPlayer player, boolean adv) {
        return machineBlock.getMachineProxy().getMachine().getToolTip(stack, player, adv);
    }
}
