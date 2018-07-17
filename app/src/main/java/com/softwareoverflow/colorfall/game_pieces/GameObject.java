package com.softwareoverflow.colorfall.game_pieces;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.softwareoverflow.colorfall.game.Level;


public abstract class GameObject{

    final int screenX;
    int x = 0, y =0;
    float speed = 1;
    int panelWidth;

    private Bitmap bitmap;
    private Colour colour;

    GameObject(int screenX, int numPanels){
        this.screenX = screenX;
        this.panelWidth = screenX / numPanels;

        //placeholder to init bitmap
        setColour(Colour.BLUE);
    }

    Colour getColour(){
        return colour;
    }

    Bitmap getBitmap() {
        return bitmap;
    }

    void setColour(Colour colour) {
        this.colour = colour;
        this.bitmap = Bitmaps.getBitmap(colour);
    }

    public int getY(){
        return y;
    }


    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, x, y, null);
    }

    /**
     * @param touchX - x position of the touch event
     * @param touchY - y position of the touch event
     * @return - true if the player touched within the bounds, false otherwise
     */
    public boolean isObjectTouched(float touchX, float touchY){
        return (touchX > x && touchX < x + getBitmap().getWidth()
                && touchY > y && touchY < y + getBitmap().getHeight());
    }

    /**
     * @param frameTime The time of the frame, used to maintain a smooth ui
     */
    public abstract void update(double frameTime);
    /**
     *
     * @return true if the piece scored a point, false otherwise
     */
    public abstract boolean didPieceScore(Level level);
    /**
     * reset the piece
     */
    public abstract void resetPiece(Level level);
    /**
     * @param endX - The xValue at the end of the swipe. Used to calculate which panel the ball
     *             should be moved to.
     */
    public abstract void onSwipe(float endX);
}