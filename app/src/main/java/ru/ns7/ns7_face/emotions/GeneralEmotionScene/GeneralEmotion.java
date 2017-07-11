package ru.ns7.ns7_face.emotions.GeneralEmotionScene;


import android.content.Context;

import fr.arnaudguyon.smartgl.opengl.SmartGLView;
import ru.ns7.ns7_face.emotions.Emotion;


public class GeneralEmotion extends Emotion {

    protected RightEye mRightEye;
    protected LeftEye mLeftEye;
    protected Mouth mMouth;

    protected boolean isAnimateMouth = false;

    public GeneralEmotion(SmartGLView mView) {
        super(mView);

        Context context = mView.getContext();

        mRightEye = new RightEye(context);
        mLeftEye = new LeftEye(context);
        mMouth = new Mouth(context);
    }


    public void init() {
        mRightEye.setPos(400, 300);
        mRightEye.setScale(2f, 2f);

        mLeftEye.setPos(0, 300);
        mLeftEye.setScale(2f, 2f);

        mMouth.setPos(370, 580);
        mMouth.setScale(0.3f, 0.4f);

        mRenderPassSprite.addSprite(mRightEye);
        mRenderPassSprite.addSprite(mLeftEye);
        mRenderPassSprite.addSprite(mMouth);

    }


    public void MouthAnimate() {
        if (isAnimateMouth) {
            //mMouth.setVisible(false);
            mMouth.setScale(mMouth.getScaleX(), mMouth.getScaleY() / 0.5f);
        }
        else {
            //mMouth.setVisible(true);
            mMouth.setScale(mMouth.getScaleX(), mMouth.getScaleY() * 0.5f);
        }

        isAnimateMouth =! isAnimateMouth;
    }

    public void release() {
        if (mRightEye != null) {
            mRightEye.release();
        }

        if (mLeftEye != null) {
            mLeftEye.release();
        }

        if (mMouth != null) {
            mMouth.release();
        }
    }

}
