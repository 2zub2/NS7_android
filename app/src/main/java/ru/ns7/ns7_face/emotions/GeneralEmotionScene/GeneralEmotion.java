package ru.ns7.ns7_face.emotions.GeneralEmotionScene;


import android.content.Context;
import android.opengl.GLSurfaceView;

import fr.arnaudguyon.smartgl.opengl.RenderPassSprite;
import fr.arnaudguyon.smartgl.opengl.SmartGLRenderer;
import fr.arnaudguyon.smartgl.opengl.SmartGLView;
import ru.ns7.ns7_face.emotions.MouthTalk;


public class GeneralEmotion {

    private SmartGLRenderer mRenderer;
    private RenderPassSprite mRenderPassSprite;

    protected RightEye mRightEye;
    protected LeftEye mLeftEye;
    protected Mouth mMouth;

    protected MouthTalk mMouthTalk;


    public GeneralEmotion(SmartGLView mView) {

        Context context = mView.getContext();
        mRenderPassSprite = new RenderPassSprite();
        mRenderer = mView.getSmartGLRenderer();

        mRightEye = new RightEye(context);
        mLeftEye = new LeftEye(context);
        mMouthTalk = new MouthTalk(context);
    }


    public void init() {

        mRightEye.setPos(400, 300);
        mRightEye.setScale(2f, 2f);

        mLeftEye.setPos(0, 300);
        mLeftEye.setScale(2f, 2f);

        mMouthTalk.setPos(405, 660);
        mMouthTalk.setScale(0.3f, 0.4f);

        mRenderPassSprite.addSprite(mRightEye);
        mRenderPassSprite.addSprite(mLeftEye);
        mRenderPassSprite.addSprite(mMouthTalk);

        mRenderer.addRenderPass(mRenderPassSprite);
    }


    public void update() {
        mMouthTalk.Update(mRenderer.getFrameDuration());
    }

    public void release() {
        if (mRightEye != null) {
            mRightEye.release();
        }

        if (mLeftEye != null) {
            mLeftEye.release();
        }

        if (mMouth != null) {
            mMouthTalk.release();
        }
    }

    public void AnimateMouth(boolean animate) {
        mMouthTalk.setAnimate(animate);
    }

}
