package mods.railcraft.common.plugins.buildcraft.triggers;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import buildcraft.api.statements.IStatementParameter;
import cpw.mods.fml.common.Optional;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class TriggerMaintenance extends Trigger {

    @Override
    @Optional.Method(modid = "BuildCraft|Core")
    public boolean isTriggerActive(ForgeDirection side, TileEntity tile, IStatementParameter[] parameter) {
        if (tile instanceof INeedsMaintenance) {
            return ((INeedsMaintenance) tile).needsMaintenance();
        }
        return false;
    }
}
