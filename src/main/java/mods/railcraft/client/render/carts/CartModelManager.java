/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info This code is the property of CovertJaguar and may only be used
 * with explicit written permission unless otherwise specified on the license page at
 * http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.client.render.carts;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.entity.item.EntityMinecart;

import mods.railcraft.client.render.models.ModelSimpleCube;
import mods.railcraft.client.render.models.ModelTextured;
import mods.railcraft.client.render.models.carts.ModelGift;
import mods.railcraft.client.render.models.carts.ModelLowSidesMinecart;
import mods.railcraft.client.render.models.carts.ModelMaintance;
import mods.railcraft.common.carts.EntityCartCargo;
import mods.railcraft.common.carts.EntityCartGift;
import mods.railcraft.common.carts.EntityCartTank;
import mods.railcraft.common.carts.EntityCartTrackLayer;
import mods.railcraft.common.carts.EntityCartTrackRelayer;
import mods.railcraft.common.carts.EntityCartTrackRemover;
import mods.railcraft.common.carts.EntityCartUndercutter;
import mods.railcraft.common.core.RailcraftConstants;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class CartModelManager {

    public static final ModelBase modelMinecart = new ModelMinecart();
    public static final ModelTextured emptyModel = new ModelTextured("empty");
    public static final Map<Class, ModelBase> modelsCore = new HashMap<Class, ModelBase>();
    public static final Map<Class, ModelTextured> modelsContents = new HashMap<Class, ModelTextured>();

    static {
        ModelLowSidesMinecart lowSides = new ModelLowSidesMinecart();
        modelsCore.put(EntityCartTank.class, lowSides);
        modelsCore.put(EntityCartCargo.class, lowSides);

        ModelTextured tank = new ModelSimpleCube();
        tank.setTexture(RailcraftConstants.CART_TEXTURE_FOLDER + "cart_tank.png");
        tank.doBackFaceCulling(false);
        modelsContents.put(EntityCartTank.class, tank);

        modelsContents.put(EntityCartGift.class, new ModelGift());

        ModelTextured maint = new ModelMaintance();
        maint.setTexture(RailcraftConstants.CART_TEXTURE_FOLDER + "cart_undercutter.png");
        modelsContents.put(EntityCartUndercutter.class, maint);

        maint = new ModelMaintance();
        maint.setTexture(RailcraftConstants.CART_TEXTURE_FOLDER + "cart_track_relayer.png");
        modelsContents.put(EntityCartTrackRelayer.class, maint);

        maint = new ModelMaintance();
        maint.setTexture(RailcraftConstants.CART_TEXTURE_FOLDER + "cart_track_layer.png");
        modelsContents.put(EntityCartTrackLayer.class, maint);

        maint = new ModelMaintance();
        maint.setTexture(RailcraftConstants.CART_TEXTURE_FOLDER + "cart_track_remover.png");
        modelsContents.put(EntityCartTrackRemover.class, maint);
    }

    public static ModelBase getCoreModel(Class eClass) {
        ModelBase render = modelsCore.get(eClass);
        if (render == null && eClass != EntityMinecart.class) {
            render = getCoreModel(eClass.getSuperclass());
            if (render == null) {
                render = modelMinecart;
            }
            modelsCore.put(eClass, render);
        }
        return render;
    }

    public static ModelTextured getContentModel(Class eClass) {
        ModelTextured render = modelsContents.get(eClass);
        if (render == null && eClass != EntityMinecart.class) {
            render = getContentModel(eClass.getSuperclass());
            if (render == null) {
                render = emptyModel;
            }
            modelsContents.put(eClass, render);
        }
        return render;
    }
}
