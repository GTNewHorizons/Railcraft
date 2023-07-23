package mods.railcraft.common.plugins.buildcraft.triggers;

import java.util.EnumSet;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import buildcraft.api.statements.IStatementParameter;
import cpw.mods.fml.common.Optional;
import mods.railcraft.common.blocks.machine.beta.TileEngine;
import mods.railcraft.common.blocks.machine.beta.TileEngine.EnergyStage;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class TriggerEngine extends Trigger {

    private final EnumSet<EnergyStage> stages;

    public TriggerEngine(EnumSet<EnergyStage> stages) {
        this.stages = stages;
    }

    @Override
    @Optional.Method(modid = "BuildCraft|Core")
    public boolean isTriggerActive(ForgeDirection side, TileEntity tile, IStatementParameter[] parameter) {
        if (tile instanceof TileEngine) {
            EnergyStage engineStage = ((TileEngine) tile).getEnergyStage();
            return stages.contains(engineStage);
        }
        return false;
    }
}
