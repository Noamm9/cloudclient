/*
 * Copyright (c) 2022 DupliCAT
 * GNU Lesser General Public License v3.0
 */

package dev.cloudmc.gui.hudeditor.impl.impl;

import dev.cloudmc.Cloud;
import dev.cloudmc.gui.ClientStyle;
import dev.cloudmc.gui.hudeditor.impl.HudEditor;
import dev.cloudmc.gui.hudeditor.impl.HudMod;
import dev.cloudmc.helpers.Helper2D;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ServerAddressHud extends HudMod {

    public ServerAddressHud(String name, int x, int y) {
        super(name, x, y);
        setW(100);
        setH(20);
    }

    @Override
    public void renderMod(int mouseX, int mouseY) {
        if (Cloud.INSTANCE.modManager.getMod(getName()).isToggled()) {
            if (isModern()) {
                if (isBackground()) {
                    Helper2D.drawRoundedRectangle(getX(), getY(), getW(), getH(), 2, ClientStyle.getBackgroundColor(50).getRGB(), 0);
                }
                Cloud.INSTANCE.fontHelper.size20.drawString(
                        Cloud.INSTANCE.mc.isIntegratedServerRunning() ? "Singleplayer" : Cloud.INSTANCE.mc.getCurrentServerData().serverIP,
                        getX() + getW() / 2 - (Cloud.INSTANCE.fontHelper.size20.getStringWidth(Cloud.INSTANCE.mc.isIntegratedServerRunning() ? "Singleplayer" : Cloud.INSTANCE.mc.getCurrentServerData().serverIP)) / 2,
                        getY() + 6,
                        getColor()
                );
            }
            else {
                if (isBackground()) {
                    Helper2D.drawRectangle(getX(), getY(), getW(), getH(), ClientStyle.getBackgroundColor(50).getRGB());
                }
                Cloud.INSTANCE.mc.fontRendererObj.drawString(
                        Cloud.INSTANCE.mc.isIntegratedServerRunning() ? "Singleplayer" : Cloud.INSTANCE.mc.getCurrentServerData().serverIP,
                        getX() + getW() / 2 - (Cloud.INSTANCE.mc.fontRendererObj.getStringWidth(Cloud.INSTANCE.mc.isIntegratedServerRunning() ? "Singleplayer" : Cloud.INSTANCE.mc.getCurrentServerData().serverIP)) / 2,
                        getY() + 6,
                        getColor()
                );
            }
            super.renderMod(mouseX, mouseY);
        }
    }

    @SubscribeEvent
    public void onRender2D(RenderGameOverlayEvent.Pre.Text e) {
        if (Cloud.INSTANCE.modManager.getMod(getName()).isToggled() && !(Cloud.INSTANCE.mc.currentScreen instanceof HudEditor)) {
            if (isModern()) {
                if (isBackground()) {
                    Helper2D.drawRoundedRectangle(getX(), getY(), getW(), getH(), 2, 0x50000000, 0);
                }
                Cloud.INSTANCE.fontHelper.size20.drawString(
                        Cloud.INSTANCE.mc.isIntegratedServerRunning() ? "Singleplayer" : Cloud.INSTANCE.mc.getCurrentServerData().serverIP,
                        getX() + getW() / 2 - (Cloud.INSTANCE.fontHelper.size20.getStringWidth(Cloud.INSTANCE.mc.isIntegratedServerRunning() ? "Singleplayer" : Cloud.INSTANCE.mc.getCurrentServerData().serverIP)) / 2,
                        getY() + 6,
                        getColor()
                );
            }
            else {
                if (isBackground()) {
                    Helper2D.drawRectangle(getX(), getY(), getW(), getH(), 0x50000000);
                }
                Cloud.INSTANCE.mc.fontRendererObj.drawString(
                        Cloud.INSTANCE.mc.isIntegratedServerRunning() ? "Singleplayer" : Cloud.INSTANCE.mc.getCurrentServerData().serverIP,
                        getX() + getW() / 2 - (Cloud.INSTANCE.mc.fontRendererObj.getStringWidth(Cloud.INSTANCE.mc.isIntegratedServerRunning() ? "Singleplayer" : Cloud.INSTANCE.mc.getCurrentServerData().serverIP)) / 2,
                        getY() + 6,
                        getColor()
                );
            }
        }
    }

    public int getColor() {
        return Cloud.INSTANCE.settingManager.getSettingByModAndName(getName(), "Font Color").getColor().getRGB();
    }

    private boolean isModern() {
        return Cloud.INSTANCE.settingManager.getSettingByModAndName(getName(), "Mode").getCurrentMode().equalsIgnoreCase("Modern");
    }

    private boolean isBackground() {
        return Cloud.INSTANCE.settingManager.getSettingByModAndName(getName(), "Background").isCheckToggled();
    }
}