package ru.ns7.ns7_face.mouth;


import android.content.Context;

import fr.arnaudguyon.smartgl.opengl.RenderPassSprite;
import fr.arnaudguyon.smartgl.opengl.SmartGLRenderer;
import fr.arnaudguyon.smartgl.opengl.SmartGLView;
import ru.ns7.ns7_face.sound.AudioVisualisation;

public class WaveBar extends AudioVisualisation {
    private final int NUM_RECT = 44;
    private final int LEFT_POSITION = 300;
    private final int TOP_POSITION = 650;
    private final int BUFFER_LENGHT = 2048;


    private SmartGLRenderer glRenderer;
    private RenderPassSprite renderPassSprite;

    private BarRect[] barRects;

    private Wave wave;
    private float[] scaleRects;

    private byte[] buffer;


    public WaveBar(SmartGLView glView)
    {
        Context context = glView.getContext();

        glRenderer = glView.getSmartGLRenderer();
        renderPassSprite = new RenderPassSprite();

        barRects = new BarRect[NUM_RECT];

        for (int i = 0; i < NUM_RECT; i++) {
            barRects[i] = new BarRect(context);
        }

        scaleRects = new float[NUM_RECT];
        buffer = new byte[BUFFER_LENGHT];
    }

    public void init()
    {
        for (int i = 0; i < NUM_RECT; i++) {
            barRects[i].setPivot(0.5f, 0.5f);
            barRects[i].setPos(LEFT_POSITION + BarRect.WIDTH*i, TOP_POSITION);
            renderPassSprite.addSprite(barRects[i]);
        }

        glRenderer.addRenderPass(renderPassSprite);

        wave = new Wave(NUM_RECT);
    }


    public void update()
    {
        if (audioVisualizationStream.getSample(buffer)) {
            wave.updateRms(buffer);
        }

        wave.updateWave(scaleRects);

        for (int i = 0; i < NUM_RECT; i++) {
            barRects[i].setScale(1, scaleRects[i]);
        }
    }

    public void release()
    {
        if (barRects != null) {
            for (int i = 0; i < NUM_RECT; i++) {
                barRects[i].release();
                renderPassSprite.releaseResources();
            }
        }
    }
}
