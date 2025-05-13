package mods.railcraft.client.render;

import net.minecraft.block.Block;

import cpw.mods.fml.common.FMLLog;
import mods.railcraft.common.blocks.machine.chest.TileChestRailcraft;
import mods.railcraft.common.core.RailcraftConstants;

public class RenderBlockMachineChest extends BlockRenderer {

    public RenderBlockMachineChest(Block block, String texture, Class<? extends TileChestRailcraft> tileClass) {
        super(block);
        FMLLog.info("Making chest renderer for block " + block);
        addBlockRenderer(0, new DoNothingRenderer());
        try {
            TileChestRailcraft tileChest = tileClass.newInstance();
            addItemRenderer(0, new RenderChest(RailcraftConstants.TESR_TEXTURE_FOLDER + texture, tileChest));
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't initialize renderer");
        } ;
    }
}
