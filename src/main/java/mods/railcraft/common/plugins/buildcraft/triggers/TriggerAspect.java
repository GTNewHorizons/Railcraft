package mods.railcraft.common.plugins.buildcraft.triggers;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import buildcraft.api.statements.IStatementParameter;
import mods.railcraft.api.signals.SignalAspect;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class TriggerAspect extends Trigger {

    private final SignalAspect aspect;

    public TriggerAspect(SignalAspect aspect) {
        this.aspect = aspect;
    }

    @Override
    public boolean isTriggerActive(ForgeDirection side, TileEntity tile, IStatementParameter[] parameter) {
        if (tile instanceof IAspectProvider) {
            return ((IAspectProvider) tile).getTriggerAspect() == aspect;
        }
        return false;
    }
}
