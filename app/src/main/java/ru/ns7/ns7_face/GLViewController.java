package ru.ns7.ns7_face;

import android.app.Activity;

import fr.arnaudguyon.smartgl.opengl.SmartGLRenderer;
import fr.arnaudguyon.smartgl.opengl.SmartGLView;
import fr.arnaudguyon.smartgl.opengl.SmartGLViewController;
import fr.arnaudguyon.smartgl.touch.TouchHelperEvent;
import ru.ns7.ns7_face.emotions.GeneralEmotionScene.GeneralEmotion;
import ru.ns7.ns7_face.mouth.WaveBar;
import ru.ns7.ns7_face.sound.AudioListener;

public class GLViewController implements SmartGLViewController {
    private WaveBar waveBar;
    private GeneralEmotion generalEmotion;

    private boolean tmpAnimateMouth = false;

    private AudioListener audioListener;


    @Override
    public void onPrepareView(SmartGLView smartGLView) {
        SmartGLRenderer renderer = smartGLView.getSmartGLRenderer();
        renderer.setDoubleSided(false);
        renderer.setClearColor(0, 0, 0, 1);

        generalEmotion = new GeneralEmotion(smartGLView);
        generalEmotion.init();

        waveBar = new WaveBar(smartGLView);
        waveBar.linkToAudioByteStream(audioListener);
        waveBar.init();
    }

    @Override
    public void onReleaseView(SmartGLView smartGLView) {
        generalEmotion.release();
        waveBar.release();
    }

    @Override
    public void onResizeView(SmartGLView smartGLView) {

    }

    @Override
    public void onTick(SmartGLView smartGLView) {
        generalEmotion.update();
        waveBar.update();
    }

    @Override
    public void onTouchEvent(SmartGLView smartGLView, TouchHelperEvent touchHelperEvent) {
        generalEmotion.AnimateMouth(tmpAnimateMouth);

        tmpAnimateMouth = !tmpAnimateMouth;
    }

    public void onStart(Activity activity)
    {
        audioListener = new AudioListener();
        audioListener.checkPermissionsAndStart(activity);
    }

    public void onPause()
    {
        return;
    }

    public void onResume()
    {
        return;
    }
}
