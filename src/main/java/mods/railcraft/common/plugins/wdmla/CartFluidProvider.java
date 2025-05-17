package mods.railcraft.common.plugins.wdmla;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import com.gtnewhorizons.wdmla.api.accessor.Accessor;
import com.gtnewhorizons.wdmla.api.provider.IClientExtensionProvider;
import com.gtnewhorizons.wdmla.api.provider.IServerExtensionProvider;
import com.gtnewhorizons.wdmla.api.view.ClientViewGroup;
import com.gtnewhorizons.wdmla.api.view.FluidView;
import com.gtnewhorizons.wdmla.api.view.ViewGroup;

import mods.railcraft.common.core.Railcraft;

public enum CartFluidProvider
        implements IServerExtensionProvider<FluidView.Data>, IClientExtensionProvider<FluidView.Data, FluidView> {

    INSTANCE;

    @Override
    public List<ClientViewGroup<FluidView>> getClientGroups(Accessor accessor, List<ViewGroup<FluidView.Data>> groups) {
        return ClientViewGroup.map(groups, FluidView::readDefault, (group, clientGroup) -> {});
    }

    @Override
    public List<ViewGroup<FluidView.Data>> getGroups(Accessor accessor) {
        if (!(accessor.getTarget() instanceof IFluidHandler fluidCart)) {
            return null;
        }
        List<ViewGroup<FluidView.Data>> tanks = new ArrayList<ViewGroup<FluidView.Data>>();
        for (FluidTankInfo info : fluidCart.getTankInfo(ForgeDirection.UNKNOWN)) {
            tanks.add(new ViewGroup<>(Arrays.asList(new FluidView.Data(info.fluid, info.capacity))));
        }
        return tanks;
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(Railcraft.MOD_ID.toLowerCase(), "cart_fluid");
    }
}
