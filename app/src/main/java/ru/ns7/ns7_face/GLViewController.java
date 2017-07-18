package ru.ns7.ns7_face;

import fr.arnaudguyon.smartgl.opengl.SmartGLRenderer;
import fr.arnaudguyon.smartgl.opengl.SmartGLView;
import fr.arnaudguyon.smartgl.opengl.SmartGLViewController;
import fr.arnaudguyon.smartgl.touch.TouchHelperEvent;
import ru.ns7.ns7_face.emotions.GeneralEmotionScene.GeneralEmotion;

public class GLViewController implements SmartGLViewController {
    private GeneralEmotion mGeneralEmotion;

    private boolean tmpAnimateMouth = false;


    public GLViewController() {

    }

    @Override
    public void onPrepareView(SmartGLView smartGLView) {
        SmartGLRenderer renderer = smartGLView.getSmartGLRenderer();
        renderer.setDoubleSided(false);
        renderer.setClearColor(0, 0, 0, 1);

        mGeneralEmotion = new GeneralEmotion(smartGLView);
        mGeneralEmotion.init();
    }

    @Override
    public void onReleaseView(SmartGLView smartGLView) {
        mGeneralEmotion.release();
    }

    @Override
    public void onResizeView(SmartGLView smartGLView) {

    }

    @Override
    public void onTick(SmartGLView smartGLView) {
        mGeneralEmotion.update();
    }

    @Override
    public void onTouchEvent(SmartGLView smartGLView, TouchHelperEvent touchHelperEvent) {
        mGeneralEmotion.AnimateMouth(tmpAnimateMouth);

        tmpAnimateMouth = !tmpAnimateMouth;
    }
}
