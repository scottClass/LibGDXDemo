/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scott.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.scott.model.Block;
import com.scott.model.Mario;
import com.scott.model.World;

/**
 *
 * @author johns6971
 */
public class WorldRenderer {
    private World world;
    private Mario player;
    
    public final int V_WIDTH = 800;
    public final int V_HEIGHT = 600;
    
    private Viewport viewport; 
    private OrthographicCamera camera;
    private SpriteBatch batch;
    
    public WorldRenderer(World w) {
        world = w;
        player = world.getPlayer();
        
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        batch = new SpriteBatch();
        
        camera.position.x = V_WIDTH/2f;
        //move the y position of the camera
        camera.position.y = V_HEIGHT/2f;
        camera.update();
    }
    
    public void render(float deltaTime) {
        //clear the screen with black
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.update();
        //links renderer to camera
        batch.setProjectionMatrix(camera.combined);
        //loads in the images
        AssetManager.load();
        //links the renderer to the camera
        batch.begin();
        //go through and draw the blocks
        for(Block b: world.getBlocks()) {
            batch.draw(AssetManager.block, b.getX(), b.getY());
        }
        //draw mario
        batch.draw(AssetManager.marioStand, player.getX(), player.getY());
        
        batch.end();
        
    }
    
    public void resize (int width, int height) {
        viewport.update(width, height);
    }
}
