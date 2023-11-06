/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.plugins.forge;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagEnd;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;

import com.google.common.collect.ForwardingList;

import cpw.mods.fml.common.ObfuscationReflectionHelper;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info/>
 */
public class NBTPlugin {

    public enum EnumNBTType {

        END(NBTTagEnd.class),
        BYTE(NBTTagByte.class),
        SHORT(NBTTagShort.class),
        INT(NBTTagInt.class),
        LONG(NBTTagLong.class),
        FLOAT(NBTTagFloat.class),
        DOUBLE(NBTTagDouble.class),
        BYTE_ARRAY(NBTTagByteArray.class),
        STRING(NBTTagString.class),
        LIST(NBTTagList.class),
        COMPOUND(NBTTagCompound.class),
        INT_ARRAY(NBTTagIntArray.class);

        public static final EnumNBTType[] VALUES = values();
        public final Class<? extends NBTBase> classObject;

        EnumNBTType(Class<? extends NBTBase> c) {
            this.classObject = c;
        }

        public static EnumNBTType fromClass(Class<? extends NBTBase> c) {
            for (EnumNBTType type : VALUES) {
                if (type.classObject == c) return type;
            }
            return null;
        }
    }

    public static <T extends NBTBase> NBTList<T> getNBTList(NBTTagCompound nbt, String tag, EnumNBTType type) {
        NBTTagList nbtList = nbt.getTagList(tag, type.ordinal());
        return new NBTList<T>(nbtList);
    }

    public static class NBTList<T extends NBTBase> extends ForwardingList<T> {

        private final ArrayList<T> backingList;

        public NBTList(NBTTagList nbtList) {
            backingList = ObfuscationReflectionHelper.getPrivateValue(NBTTagList.class, nbtList, 0);
        }

        @Override
        protected List<T> delegate() {
            return backingList;
        }
    }
}
