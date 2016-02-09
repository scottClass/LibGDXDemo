/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scott.model;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author johns6971
 */
public class Mario extends Entity {
    private final float X_MAX_VEL = 2.0f;
    private final float Y_MAX_VEL = 4.0f;
    private final float DAMP = 0.9f;
    
    
    //states for mario
    public enum State {
        STANDING, RUNNING, JUMPING
    }
    
    //the actual state mario is in
    private State state;
    private Vector2 velocity;
    private Vector2 acceleration;
    //facing
    private boolean isFacingLeft;
    
    private float stateTime;
    
    public Mario(float x, float y, float width, float height) {
        super(x, y, width, height);
        state = State.STANDING;
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        isFacingLeft = false;
        stateTime = 0;
    }
    
    public void update(float delta) {
        acceleration.y = -9.8f;
        velocity.mulAdd(acceleration, delta);
        velocity.x = velocity.x * DAMP;
        if(velocity.x < 0.01f && velocity.x > -0.001f) {
            velocity.x = 0;
        }
        addToPosition(velocity.x, 0);
        addToPosition(0, velocity.y);
        
        
    }
    
    public void jump() {
        if(velocity.y == 0) {
            velocity.y = Y_MAX_VEL;
        }
    }
    
    public void setVelocityX(float x) {
        velocity.x = x;
    }
    
    public void setVelocityY(float y) {
        velocity.y = y;
    }
    
    public void setState(State s) {
        state = s;
    }
    
    public float getVelocityX() {
        return velocity.x;
    }
    
    public float getVelocityY() {
        return velocity.y;
    }
    
    public State getState() {
        return state;
    }
    
    public float getStateTime() {
        return stateTime;
    }
    
    public boolean isFacingLeft() {
        return isFacingLeft;
    }
    
}
