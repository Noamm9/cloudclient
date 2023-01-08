/*
 * Copyright (c) 2022 DupliCAT
 * GNU Lesser General Public License v3.0
 */

package dev.cloudmc.helpers;

import org.lwjgl.input.Mouse;

import java.util.LinkedList;
import java.util.Queue;

public class CpsHelper {

    private final Queue<Long> clicks;
    private boolean lastClick;

    public CpsHelper(){
        clicks = new LinkedList<>();
    }

    private void updateClicks(int mouseButton, boolean shouldAdd){
        long time = System.currentTimeMillis();

        if(Mouse.isButtonDown(mouseButton) != lastClick){
            lastClick = Mouse.isButtonDown(mouseButton);
            if(Mouse.isButtonDown(mouseButton) && shouldAdd)
                clicks.add(time);
        }

        clicks.removeIf(e -> e + 1000 < time);
    }

    public int getCPS(int mouseButton, boolean shouldAdd){
        updateClicks(mouseButton, shouldAdd);
        return clicks.size();
    }
}