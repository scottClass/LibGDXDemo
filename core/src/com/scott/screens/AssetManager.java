/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scott.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author johns6971
 */
public class AssetManager {
    
    private static TextureAtlas atlas;
    public static TextureRegion block;
    public static TextureRegion marioStand;
    
    public static void load() {
        atlas = new TextureAtlas("Mario.pack");
        block = atlas.findRegion("stoneBlock");
        marioStand = atlas.findRegion("stand");
    }
    
}
