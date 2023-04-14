/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.util.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import mods.railcraft.common.util.inventory.InvTools;
import mods.railcraft.common.util.misc.Game;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class PacketCurrentItemNBT extends RailcraftPacket {

    private static final Marker SECURITY_MARKER = MarkerManager.getMarker("SuspiciousPackets");
    private static final Set<String> ALLOWED_TAGS = new HashSet<>(Arrays.asList("title", "author", "pages", "dest"));

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

            NBTTagCompound nbt = InvTools.getItemData(stack);
            for (String tag : (Set<String>) nbt.func_150296_c()) {
                if (!ALLOWED_TAGS.contains(tag)) {
                    return;
                }
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
