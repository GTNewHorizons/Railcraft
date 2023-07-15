package mods.railcraft.common.plugins.buildcraft.triggers;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import buildcraft.api.statements.IStatementParameter;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public abstract class Trigger {

    @cpw.mods.fml.common.Optional.Method(modid = "BuildCraft|Core")
    public abstract boolean isTriggerActive(ForgeDirection side, TileEntity tile, IStatementParameter[] parameter);
}
