package ru.ns7.ns7_face.mouth;


import android.content.Context;
import android.media.AudioRecord;

import fr.arnaudguyon.smartgl.opengl.RenderPassSprite;
import fr.arnaudguyon.smartgl.opengl.SmartGLRenderer;
import fr.arnaudguyon.smartgl.opengl.SmartGLView;
import ru.ns7.ns7_face.sound.AudioListener;

public class WaveBar {
    private final int LEFT_POSITION = 300;
    private final int TOP_POSITION = 650;

    private SmartGLRenderer glRenderer;
    private RenderPassSprite renderPassSprite;
    public AudioListener audioListener;

    private BarRect[] barRects;

    private Wave wave;
    private int numRect;
    private float[] scaleRects;


    public WaveBar(SmartGLView glView, AudioListener listener)
    {
        audioListener = listener;

        Context context = glView.getContext();

        glRenderer = glView.getSmartGLRenderer();
        renderPassSprite = new RenderPassSprite();

        numRect = Wave.NUM_RECT;

        barRects = new BarRect[numRect];

        for (int i = 0; i < numRect; i++) {
            barRects[i] = new BarRect(context);
        }

        scaleRects = new float[numRect];
    }

    public void init()
    {
        for (int i = 0; i < Wave.NUM_RECT; i++) {
            barRects[i].setPivot(0.5f, 0.5f);
            barRects[i].setPos(LEFT_POSITION + BarRect.WIDTH*i, TOP_POSITION);
            renderPassSprite.addSprite(barRects[i]);
        }

        glRenderer.addRenderPass(renderPassSprite);

        wave = new Wave();
    }

    public void UpdateRms()
    {
        //audioListener.update();

        if (audioListener.buffer != null)
            wave.UpdateRms(audioListener.buffer);
    }

    public void update()
    {
        UpdateRms();
        wave.UpdateWave(scaleRects);

        for (int i = 0; i < numRect; i++) {
            barRects[i].setScale(1, scaleRects[i]);
        }
    }

    public void release()
    {
        if (barRects != null) {
            for (int i = 0; i < numRect; i++) {
                barRects[i].release();
                renderPassSprite.releaseResources();
            }
        }
    }
}
