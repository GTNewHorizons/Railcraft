package mods.railcraft.common.plugins.buildcraft.actions;

import java.util.Collection;
import java.util.LinkedList;

import net.minecraftforge.common.util.ForgeDirection;

import buildcraft.api.statements.IActionExternal;
import buildcraft.api.statements.IActionInternal;
import buildcraft.api.statements.IActionProvider;
import buildcraft.api.statements.IStatementContainer;
import cpw.mods.fml.common.Optional;
import mods.railcraft.common.plugins.buildcraft.triggers.*;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
@Optional.InterfaceList(
        value = {
                @Optional.Interface(iface = "buildcraft.api.statements.IActionProvider", modid = "BuildCraft|Core"), })
public class ActionProvider implements IActionProvider {

    public ActionProvider() {
        Actions.init();
    }

    @Override
    @Optional.Method(modid = "BuildCraft|Core")
    public Collection<IActionInternal> getInternalActions(IStatementContainer isc) {
        return null;
    }

    @Override
    @Optional.Method(modid = "BuildCraft|Core")
    public Collection<IActionExternal> getExternalActions(ForgeDirection side,
            net.minecraft.tileentity.TileEntity tile) {
        Collection<IActionExternal> actions = new LinkedList<IActionExternal>();
        if (tile instanceof IHasWork) actions.add(Actions.PAUSE);
        if (tile instanceof IHasCart) actions.add(Actions.SEND_CART);
        return actions;
    }
}
