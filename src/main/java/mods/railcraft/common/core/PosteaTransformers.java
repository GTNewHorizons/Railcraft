package mods.railcraft.common.core;

import com.gtnewhorizons.postea.api.BlockReplacementManager;
import com.gtnewhorizons.postea.api.ItemStackReplacementManager;
import com.gtnewhorizons.postea.utility.BlockConversionInfo;

import cpw.mods.fml.common.FMLLog;
import mods.railcraft.common.blocks.machine.Machines;
import net.minecraft.block.Block;

public class PosteaTransformers {
    public static void registerTransformers() {
        FMLLog.info("Registering block transformers");
        BlockReplacementManager.addBlockReplacement("Railcraft:machine.delta", (tag, world) -> {
            FMLLog.info("Replacing wire block");
            BlockConversionInfo conversion = new BlockConversionInfo();
            conversion.blockName = "railcraft.wire";
            conversion.blockID = Block.getIdFromBlock(Machines.WIRE.getBlock());
            conversion.metadata = 0;
            return conversion;
        });
        ItemStackReplacementManager.addItemReplacement("Railcraft:machine.delta", (nbt) -> {
        //    IDExtenderCompat.setItemStackID(nbt, Item.getIdFromItem(Machines.WIRE.getItem(1).getItem()));
            return nbt;
        });
    }
}
