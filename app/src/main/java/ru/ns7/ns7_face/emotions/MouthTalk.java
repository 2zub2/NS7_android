package ru.ns7.ns7_face.emotions;


import android.content.Context;

import ru.ns7.ns7_face.emotions.GeneralEmotionScene.Mouth;



public class MouthTalk extends Mouth {

    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public boolean isAnimate() {
        return animate;
    }

    public void setAnimate(boolean animate) {
        this.animate = animate;
    }

    private boolean animate = false;
    private int direction = -1;


    public MouthTalk(Context mContext) {
        super(mContext);

        frameCount = 5;
        maxFrameTime = 0.04f;
        frame = 0;
        frameCount = 8;

        setPivot(0.5f, 0.5f);
    }

    public void Update(float dt) {
        if (!animate && frame==0) return;

        currentFrameTime += dt;

        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
            UpdateFrame();
        }

        if (frame >= frameCount/2 ) {
            direction = 1;
        }

        if (frame >= frameCount) {
            frame = 0;
            direction = -1;
        }
    }

    private void UpdateFrame()
    {
        resize((int)getWidth() - direction*6, (int)getHeight() + direction*12);
    }
}
