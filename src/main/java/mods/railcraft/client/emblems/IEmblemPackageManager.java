/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.client.emblems;

import net.minecraft.util.ResourceLocation;

import mods.railcraft.client.util.textures.Texture;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info/>
 */
public interface IEmblemPackageManager {

    Texture getEmblemTexture(String ident);

    ResourceLocation getEmblemTextureLocation(String ident);

    Emblem getEmblem(String ident);
}
