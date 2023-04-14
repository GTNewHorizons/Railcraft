/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.util.network;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface IEditableItem {

    boolean validateNBT(ItemStack oldStack, NBTTagCompound nbt);

    boolean canPlayerEdit(EntityPlayer player, ItemStack stack);

    /**
     * @return true if edit only exists if editableKeys, false otherwise
     */
    static boolean checkValidModification(NBTTagCompound oldTag, NBTTagCompound newTag, Set<String> editableKeys) {
        Set<String> oldKeys = new HashSet<>();
        // if there is any existing key, the edited tag should preserve it as is.
        if (oldTag != null) {
            for (Object o : oldTag.func_150296_c()) {
                String key = (String) o;
                if (editableKeys.contains(key)) continue;
                if (!oldTag.getTag(key).equals(newTag.getTag(key))) return false;
                oldKeys.add(key);
            }
        }
        // it should not add any keys not present in the oldTag or is otherwise open to edit
        for (Object o : newTag.func_150296_c()) {
            String key = (String) o;
            if (oldKeys.contains(key) || editableKeys.contains(key)) continue;
            return false;
        }
        return true;
    }
}
