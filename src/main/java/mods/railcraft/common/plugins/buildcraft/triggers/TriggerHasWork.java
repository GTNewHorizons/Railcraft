package mods.railcraft.common.plugins.buildcraft.triggers;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import buildcraft.api.statements.IStatementParameter;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class TriggerHasWork extends Trigger {

    @Override
    @cpw.mods.fml.common.Optional.Method(modid = "BuildCraft|Core")
    public boolean isTriggerActive(ForgeDirection side, TileEntity tile, IStatementParameter[] parameter) {
        if (tile instanceof IHasWork) {
            return ((IHasWork) tile).hasWork();
        }
        return false;
    }
}
