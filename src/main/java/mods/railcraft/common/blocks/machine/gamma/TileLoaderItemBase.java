/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.blocks.machine.gamma;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import mods.railcraft.common.carts.CartUtils;
import mods.railcraft.common.gui.buttons.ButtonTextureSet;
import mods.railcraft.common.gui.buttons.IButtonTextureSet;
import mods.railcraft.common.gui.buttons.IMultiButtonState;
import mods.railcraft.common.gui.buttons.IOverlayMultiButtonState;
import mods.railcraft.common.gui.buttons.MultiButtonController;
import mods.railcraft.common.gui.buttons.StandardButtonTextureSets;
import mods.railcraft.common.gui.tooltips.ToolTip;
import mods.railcraft.common.plugins.forge.LocalizationPlugin;
import mods.railcraft.common.util.inventory.InvTools;
import mods.railcraft.common.util.inventory.PhantomInventory;
import mods.railcraft.common.util.network.IGuiReturnHandler;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public abstract class TileLoaderItemBase extends TileLoaderBase implements IGuiReturnHandler, ISidedInventory {

    protected static final int[] SLOTS = InvTools.buildSlotArray(0, 9);
    private final PhantomInventory invFilters = new PhantomInventory(9, this);
    private final MultiButtonController<EnumTransferMode> transferModeController = new MultiButtonController<>(
            EnumTransferMode.ALL.ordinal(),
            EnumTransferMode.values());
    private final MultiButtonController<EnumRedstoneMode> redstoneModeController = new MultiButtonController<>(
            0,
            getValidRedstoneModes());
    private final MultiButtonController<MatchNBTMode> matchNbtController = new MultiButtonController<>(
            0,
            MatchNBTMode.values());
    private final MultiButtonController<MatchMetadataMode> matchMetadataController = new MultiButtonController<>(
            0,
            MatchMetadataMode.values());
    protected boolean movedItemCart = false;

    public MultiButtonController<EnumTransferMode> getTransferModeController() {
        return transferModeController;
    }

    public EnumRedstoneMode[] getValidRedstoneModes() {
        return EnumRedstoneMode.values();
    }

    public MultiButtonController<EnumRedstoneMode> getRedstoneModeController() {
        return redstoneModeController;
    }

    public MultiButtonController<MatchNBTMode> getMatchNbtController() {
        return matchNbtController;
    }

    public MultiButtonController<MatchMetadataMode> getMatchMetadataController() {
        return matchMetadataController;
    }

    public final PhantomInventory getItemFilters() {
        return invFilters;
    }

    public abstract Slot getBufferSlot(int id, int x, int y);

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return SLOTS;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return isItemValidForSlot(slot, stack);
    }

    @Override
    protected void setPowered(boolean p) {
        if (!isSendCartGateAction() && redstoneModeController.getButtonState() == EnumRedstoneMode.MANUAL) {
            super.setPowered(false);
            return;
        }
        super.setPowered(p);
    }

    @Override
    public boolean canHandleCart(EntityMinecart cart) {
        if (isSendCartGateAction()) return false;
        if (!(cart instanceof IInventory)) return false;
        IInventory cartInv = (IInventory) cart;
        if (cartInv.getSizeInventory() <= 0) return false;
        ItemStack minecartSlot1 = getCartFilters().getStackInSlot(0);
        ItemStack minecartSlot2 = getCartFilters().getStackInSlot(1);
        if (minecartSlot1 != null || minecartSlot2 != null) if (!CartUtils.doesCartMatchFilter(minecartSlot1, cart)
                && !CartUtils.doesCartMatchFilter(minecartSlot2, cart))
            return false;
        return true;
    }

    @Override
    public boolean isProcessing() {
        return movedItemCart;
    }

    @Override
    public boolean isManualMode() {
        return redstoneModeController.getButtonState() == EnumRedstoneMode.MANUAL;
    }

    public final EnumTransferMode getMode() {
        return transferModeController.getButtonState();
    }

    public boolean isMatchByNBT() {
        return matchNbtController.getButtonState() == MatchNBTMode.MATCH_NBT;
    }

    public void setMatchByNBT(boolean matchByNBT) {
        this.matchNbtController.setCurrentState(matchByNBT ? MatchNBTMode.MATCH_NBT : MatchNBTMode.IGNORE_NBT);
    }

    public boolean isMatchByMetadata() {
        return matchMetadataController.getButtonState() == MatchMetadataMode.MATCH_METADATA;
    }

    public void setMatchByMetadata(boolean matchByMetadata) {
        this.matchMetadataController.setCurrentState(
                matchByMetadata ? MatchMetadataMode.MATCH_METADATA : MatchMetadataMode.IGNORE_METADATA);
    }

    @Override
    public void writePacketData(DataOutputStream data) throws IOException {
        super.writePacketData(data);
        data.writeByte(transferModeController.getCurrentState());
        data.writeByte(redstoneModeController.getCurrentState());
        data.writeBoolean(isMatchByNBT());
        data.writeBoolean(isMatchByMetadata());
    }

    @Override
    public void readPacketData(DataInputStream data) throws IOException {
        super.readPacketData(data);
        transferModeController.setCurrentState(data.readByte());
        redstoneModeController.setCurrentState(data.readByte());
        setMatchByNBT(data.readBoolean());
        setMatchByMetadata(data.readBoolean());
    }

    @Override
    public void writeGuiData(DataOutputStream data) throws IOException {
        data.writeByte(transferModeController.getCurrentState());
        data.writeByte(redstoneModeController.getCurrentState());
        data.writeBoolean(isMatchByNBT());
        data.writeBoolean(isMatchByMetadata());
    }

    @Override
    public void readGuiData(DataInputStream data, EntityPlayer sender) throws IOException {
        transferModeController.setCurrentState(data.readByte());
        redstoneModeController.setCurrentState(data.readByte());
        setMatchByNBT(data.readBoolean());
        setMatchByMetadata(data.readBoolean());
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        transferModeController.writeToNBT(data, "mode");
        redstoneModeController.writeToNBT(data, "redstone");
        getItemFilters().writeToNBT("invFilters", data);
        data.setBoolean("matchByNBT", isMatchByNBT());
        data.setBoolean("matchByMetadata", isMatchByMetadata());
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        transferModeController.readFromNBT(data, "mode");
        redstoneModeController.readFromNBT(data, "redstone");
        if (data.getBoolean("waitTillComplete")) {
            redstoneModeController.setCurrentState(EnumRedstoneMode.COMPLETE.ordinal());
        }

        if (data.hasKey("filters")) {
            NBTTagCompound filters = data.getCompoundTag("filters");
            getItemFilters().readFromNBT("Items", filters);
        } else {
            getItemFilters().readFromNBT("invFilters", data);
        }

        setMatchByNBT(data.getBoolean("matchByNBT"));
        setMatchByMetadata(data.getBoolean("matchByMetadata"));
    }

    public enum EnumTransferMode implements IMultiButtonState {

        TRANSFER("railcraft.gui.item.loader.transfer"),
        STOCK("railcraft.gui.item.loader.stock"),
        EXCESS("railcraft.gui.item.loader.excess"),
        ALL("railcraft.gui.item.loader.all");

        private final String label;
        private final ToolTip tip;

        EnumTransferMode(String label) {
            this.label = label;
            this.tip = ToolTip.buildToolTip(label + ".tip");
        }

        @Override
        public String getLabel() {
            return LocalizationPlugin.translate(label);
        }

        @Override
        public IButtonTextureSet getTextureSet() {
            return StandardButtonTextureSets.SMALL_BUTTON;
        }

        @Override
        public ToolTip getToolTip() {
            return tip;
        }
    }

    public enum EnumRedstoneMode implements IMultiButtonState {

        IMMEDIATE("railcraft.gui.item.loader.immediate"),
        COMPLETE("railcraft.gui.item.loader.complete"),
        MANUAL("railcraft.gui.item.loader.manual"),
        PARTIAL("railcraft.gui.item.loader.partial");

        private final String label;
        private final ToolTip tip;

        EnumRedstoneMode(String label) {
            this.label = label;
            this.tip = ToolTip.buildToolTip(label + ".tip");
        }

        @Override
        public String getLabel() {
            return LocalizationPlugin.translate(label);
        }

        @Override
        public StandardButtonTextureSets getTextureSet() {
            return StandardButtonTextureSets.SMALL_BUTTON;
        }

        @Override
        public ToolTip getToolTip() {
            return tip;
        }
    }

    public enum MatchNBTMode implements IOverlayMultiButtonState {

        IGNORE_NBT("railcraft.gui.item.loader.ignore_nbt", new ButtonTextureSet(64, 0, 32, 32)),
        MATCH_NBT("railcraft.gui.item.loader.match_nbt", new ButtonTextureSet(32, 0, 32, 32));

        private final ToolTip tip;
        private final IButtonTextureSet overlay;

        MatchNBTMode(String label, IButtonTextureSet overlay) {
            this.tip = ToolTip.buildToolTip(label + ".tip");
            this.overlay = overlay;
        }

        @Override
        public String getLabel() {
            return "";
        }

        @Override
        public IButtonTextureSet getOverlayTexture() {
            return overlay;
        }

        @Override
        public ToolTip getToolTip() {
            return tip;
        }
    }

    public enum MatchMetadataMode implements IOverlayMultiButtonState {

        IGNORE_METADATA("railcraft.gui.item.loader.ignore_metadata", new ButtonTextureSet(64, 32, 32, 32)),
        MATCH_METADATA("railcraft.gui.item.loader.match_metadata", new ButtonTextureSet(32, 32, 32, 32));

        private final ToolTip tip;
        private final IButtonTextureSet overlay;

        MatchMetadataMode(String label, IButtonTextureSet overlay) {
            this.tip = ToolTip.buildToolTip(label + ".tip");
            this.overlay = overlay;
        }

        @Override
        public String getLabel() {
            return "";
        }

        @Override
        public IButtonTextureSet getOverlayTexture() {
            return overlay;
        }

        @Override
        public ToolTip getToolTip() {
            return tip;
        }
    }
}
