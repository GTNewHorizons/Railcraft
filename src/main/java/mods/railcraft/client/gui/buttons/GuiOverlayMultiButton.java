package mods.railcraft.client.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import mods.railcraft.common.core.RailcraftConstants;
import mods.railcraft.common.gui.buttons.IButtonTextureSet;
import mods.railcraft.common.gui.buttons.IOverlayMultiButtonState;
import mods.railcraft.common.gui.buttons.MultiButtonController;

public class GuiOverlayMultiButton<T extends IOverlayMultiButtonState> extends GuiMultiButton<T> {

    private static final ResourceLocation WIDGETS = new ResourceLocation(
            RailcraftConstants.GUI_TEXTURE_FOLDER + "new/widgets.png");

    public GuiOverlayMultiButton(int id, int x, int y, MultiButtonController<? extends T> control) {
        super(id, x, y, 16, control);
    }

    @Override
    protected void bindButtonTextures(Minecraft minecraft) {
        minecraft.renderEngine.bindTexture(WIDGETS);
    }

    @Override
    public void drawButton(Minecraft minecraft, int x, int y) {
        super.drawButton(minecraft, x, y);

        if (!visible) {
            return;
        }

        bindButtonTextures(minecraft);

        GL11.glPushMatrix();

        float scaleFactor = 0.5F;
        GL11.glTranslatef(xPosition, yPosition, 0);
        GL11.glScalef(scaleFactor, scaleFactor, 1);

        T state = getController().getButtonState();
        IButtonTextureSet overlay = state.getOverlayTexture();

        drawTexturedModalRect(0, 0, overlay.getX(), overlay.getY(), overlay.getWidth(), overlay.getHeight());

        GL11.glPopMatrix();
    }
}
