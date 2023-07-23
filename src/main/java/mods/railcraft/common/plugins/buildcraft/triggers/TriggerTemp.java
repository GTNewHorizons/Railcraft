package mods.railcraft.common.plugins.buildcraft.triggers;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import buildcraft.api.statements.IStatementParameter;
import cpw.mods.fml.common.Optional;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class TriggerTemp extends Trigger {

    private final int min;
    private final int max;

    public TriggerTemp(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    @Optional.Method(modid = "BuildCraft|Core")
    public boolean isTriggerActive(ForgeDirection side, TileEntity tile, IStatementParameter[] parameter) {
        if (tile instanceof ITemperature) {
            float temp = ((ITemperature) tile).getTemperature();
            return temp >= min && temp < max;
        }
        return false;
    }
}
