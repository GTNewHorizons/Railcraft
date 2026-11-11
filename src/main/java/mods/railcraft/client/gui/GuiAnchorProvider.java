package mods.railcraft.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import mods.railcraft.common.blocks.machine.epsilon.TileAnchorProvider;
import mods.railcraft.common.core.RailcraftConstants;
import mods.railcraft.common.gui.containers.ContainerAnchorProvider;

public class GuiAnchorProvider extends TileGui {

    public GuiAnchorProvider(InventoryPlayer playerInv, TileAnchorProvider provider) {
        super(
                provider,
                new ContainerAnchorProvider(playerInv, provider),
                RailcraftConstants.GUI_TEXTURE_FOLDER + "gui_anchor_provider.png");
        xSize = 176;
        ySize = 140;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String label = getGuiTitle();
        int sWidth = fontRendererObj.getStringWidth(label);
        int sPos = xSize / 2 - sWidth / 2;
        fontRendererObj.drawString(label, sPos, 6, 0x404040);
        fontRendererObj
                .drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 2, 0x404040);
    }
}
