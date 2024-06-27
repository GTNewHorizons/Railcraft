/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.client.gui;

import net.minecraft.entity.player.InventoryPlayer;

import mods.railcraft.client.gui.buttons.GuiMultiButton;
import mods.railcraft.client.gui.buttons.GuiOverlayMultiButton;
import mods.railcraft.common.blocks.machine.gamma.TileLoaderItemBase;
import mods.railcraft.common.blocks.machine.gamma.TileLoaderItemBase.EnumRedstoneMode;
import mods.railcraft.common.blocks.machine.gamma.TileLoaderItemBase.EnumTransferMode;
import mods.railcraft.common.blocks.machine.gamma.TileLoaderItemBase.MatchMetadataMode;
import mods.railcraft.common.blocks.machine.gamma.TileLoaderItemBase.MatchNBTMode;
import mods.railcraft.common.core.RailcraftConstants;
import mods.railcraft.common.gui.containers.ContainerItemLoader;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;
import mods.railcraft.common.util.misc.Game;
import mods.railcraft.common.util.network.PacketBuilder;

public class GuiLoaderItem extends TileGui {

    private final String FILTER_LABEL = LocalizationPlugin.translate("railcraft.gui.filters");
    private final String CART_FILTER_LABEL = LocalizationPlugin.translate("railcraft.gui.filters.carts");
    private final String BUFFER_LABEL = LocalizationPlugin.translate("railcraft.gui.item.loader.buffer");
    private GuiMultiButton<EnumTransferMode> transferMode;
    private GuiMultiButton<EnumRedstoneMode> redstoneMode;
    private GuiMultiButton<MatchMetadataMode> matchMetadataMode;
    private GuiMultiButton<MatchNBTMode> matchNBTMode;
    private final TileLoaderItemBase tile;

    public GuiLoaderItem(InventoryPlayer inv, TileLoaderItemBase tile) {
        super(
                tile,
                new ContainerItemLoader(inv, tile),
                RailcraftConstants.GUI_TEXTURE_FOLDER + "new/gui_item_loader.png");
        this.tile = tile;

        ySize = 182;
    }

    @Override
    public void initGui() {
        super.initGui();
        if (tile == null) return;
        buttonList.clear();
        int w = (width - xSize) / 2;
        int h = (height - ySize) / 2;
        buttonList.add(
                transferMode = new GuiMultiButton<>(0, w + 62, h + 45, 52, tile.getTransferModeController().copy()));
        buttonList.add(
                redstoneMode = new GuiMultiButton<>(0, w + 62, h + 62, 52, tile.getRedstoneModeController().copy()));
        buttonList.add(
                matchMetadataMode = new GuiOverlayMultiButton<>(
                        0,
                        w + 8,
                        h + 81,
                        tile.getMatchMetadataController().copy()));
        buttonList.add(
                matchNBTMode = new GuiOverlayMultiButton<>(0, w + 8 + 16, h + 81, tile.getMatchNbtController().copy()));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        GuiTools.drawCenteredString(fontRendererObj, tile.getName(), 6);
        fontRendererObj.drawString(FILTER_LABEL, 18, 16, 0x404040);
        fontRendererObj.drawString(CART_FILTER_LABEL, 75, 16, 0x404040);
        fontRendererObj.drawString(BUFFER_LABEL, 126, 16, 0x404040);
    }

    @Override
    public void onGuiClosed() {
        if (Game.isNotHost(tile.getWorld())) {
            tile.getTransferModeController().setCurrentState(transferMode.getController().getCurrentState());
            tile.getRedstoneModeController().setCurrentState(redstoneMode.getController().getCurrentState());
            tile.getMatchMetadataController().setCurrentState(matchMetadataMode.getController().getCurrentState());
            tile.getMatchNbtController().setCurrentState(matchNBTMode.getController().getCurrentState());
            PacketBuilder.instance().sendGuiReturnPacket(tile);
        }
    }
}
