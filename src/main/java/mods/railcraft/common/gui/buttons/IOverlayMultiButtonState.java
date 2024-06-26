package mods.railcraft.common.gui.buttons;

public interface IOverlayMultiButtonState extends IMultiButtonState {

    IButtonTextureSet MINI_BUTTON = new ButtonTextureSet(0, 0, 16, 16);

    @Override
    default IButtonTextureSet getTextureSet() {
        return MINI_BUTTON;
    }

    IButtonTextureSet getOverlayTexture();
}
