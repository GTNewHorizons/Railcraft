package mods.railcraft.common.core;

import net.minecraft.item.Item;

import org.apache.logging.log4j.Level;

import com.gtnewhorizons.postea.api.ItemStackReplacementManager;
import com.gtnewhorizons.postea.api.TileEntityReplacementManager;
import com.gtnewhorizons.postea.utility.BlockInfo;

import cpw.mods.fml.common.FMLLog;
import mods.railcraft.common.blocks.machine.Machines;

public class PosteaTransformers {

    public static void registerTransformers() {
        FMLLog.log(Railcraft.MOD_ID, Level.DEBUG, "Registering block transformers");
        TileEntityReplacementManager.tileEntityTransformer(
                "RCWireTile",
                (tag, world) -> { return new BlockInfo(Machines.WIRE.getBlock(), 0); });
        ItemStackReplacementManager.addItemReplacement("Railcraft:machine.delta", (nbt) -> {
            nbt.setShort("id", (short) Item.getIdFromItem(Machines.WIRE.getItem(1).getItem()));
            return nbt;
        });
    }
}
