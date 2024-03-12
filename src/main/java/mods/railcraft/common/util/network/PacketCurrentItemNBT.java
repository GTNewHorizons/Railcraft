/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.util.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import mods.railcraft.common.util.misc.Game;

public class PacketCurrentItemNBT extends RailcraftPacket {

    private static final Marker SECURITY_MARKER = MarkerManager.getMarker("SuspiciousPackets");
    private final EntityPlayer player;
    private final ItemStack currentItem;

    public PacketCurrentItemNBT(EntityPlayer player, ItemStack stack) {
        this.player = player;
        this.currentItem = stack;
    }

    @Override
    public void writeData(DataOutputStream data) throws IOException {
        DataTools.writeItemStack(currentItem, data);
    }

    @Override
    public void readData(DataInputStream data) throws IOException {
        try {
            ItemStack stack = DataTools.readItemStack(data);

            if (stack == null || currentItem == null) return;

            if (stack.getItem() != currentItem.getItem()) return;

            if (!(currentItem.getItem() instanceof IEditableItem)) return;

            IEditableItem eItem = (IEditableItem) stack.getItem();

            if (!eItem.canPlayerEdit(player, currentItem)) {
                Game.LOGGER.warn(
                        SECURITY_MARKER,
                        "Player {} attempted to edit an item he is not allowed to edit {}.",
                        player.getGameProfile(),
                        currentItem.getItem().getUnlocalizedName());
                return;
            }

            if (!eItem.validateNBT(currentItem, stack.getTagCompound())) {
                Game.LOGGER.warn(SECURITY_MARKER, "Player {}: Item NBT not valid!", player.getGameProfile());
                return;
            }

            currentItem.setTagCompound(stack.getTagCompound());
        } catch (Exception exception) {
            Game.logThrowable("Error reading Item NBT packet", exception);
        }
    }

    public void sendPacket() {
        PacketDispatcher.sendToServer(this);
    }

    @Override
    public int getID() {
        return PacketType.ITEM_NBT.ordinal();
    }
}
