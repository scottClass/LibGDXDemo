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
import com.scott.model.Goomba;
import com.scott.model.Mario;
import com.scott.model.Mario.State;
import static com.scott.model.Mario.State.JUMPING;
import static com.scott.model.Mario.State.RUNNING;
import static com.scott.model.Mario.State.STANDING;
import com.scott.model.World;

/**
 *
 * @author johns6971
 */
public class WorldRenderer {
    private World world;
    private Mario player;
    private Goomba goomba;
    
    public final int V_WIDTH = 800;
    public final int V_HEIGHT = 600;
    
    private Viewport viewport; 
    private OrthographicCamera camera;
    private SpriteBatch batch;
    
    public boolean left;
    
    private float runtime;
    
    public WorldRenderer(World w) {
        world = w;
        player = world.getPlayer();
        goomba = world.getGoomba();
        
        camera = new OrthographicCamera();
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);
        batch = new SpriteBatch();
        
        camera.position.x = V_WIDTH/2f;
        //move the y position of the camera
        camera.position.y = V_HEIGHT/2f;
        left = false;
        runtime = 0;
        camera.update();
    }
    
    public void render(float deltaTime) {
        //clear the screen with black
        Gdx.gl20.glClearColor(0, 0, 0, 0);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if(player.getX() > V_WIDTH / 2) {
            camera.position.x = player.getX();
        }
        
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
        batch.draw(AssetManager.goombaWalk, goomba.getX(), goomba.getY(), 25, 25);
        //draw animation frame
        State s = player.getState();
        
        if (s == RUNNING) {
            if(!left) {
                batch.draw(AssetManager.marioRun.getKeyFrame(runtime), player.getX(), player.getY());
            } else {
                batch.draw(AssetManager.marioRunL.getKeyFrame(runtime), player.getX(), player.getY());
            }
            runtime += deltaTime;
            if(AssetManager.marioRun.isAnimationFinished(runtime) || AssetManager.marioRunL.isAnimationFinished(runtime)) {
                runtime = 0;
            }
        } else if(s == STANDING) {
            if(!left) {
                batch.draw(AssetManager.marioStand, player.getX(), player.getY());
            } else {
                batch.draw(AssetManager.marioStandL, player.getX(), player.getY());
            }
            runtime = 0;
        } else if (s == JUMPING) {
            if(left) {
                batch.draw(AssetManager.marioJumpL, player.getX(), player.getY());
            } else {
                batch.draw(AssetManager.marioJump, player.getX(), player.getY());
            }
            runtime = 0;
        }
        
        batch.end();
        
        
        
    }
    
    public void resize (int width, int height) {
        viewport.update(width, height);
    }
    
    public void setLeft(boolean thing) {
        left = thing;
    }
}
