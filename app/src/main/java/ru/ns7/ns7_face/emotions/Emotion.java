package ru.ns7.ns7_face.emotions;


import fr.arnaudguyon.smartgl.opengl.RenderPassSprite;
import fr.arnaudguyon.smartgl.opengl.SmartGLRenderer;
import fr.arnaudguyon.smartgl.opengl.SmartGLView;



public abstract class Emotion {
    protected RenderPassSprite mRenderPassSprite;
    protected SmartGLRenderer mRenderer;

    public Emotion(SmartGLView mView) {

        mRenderer = mView.getSmartGLRenderer();
        mRenderPassSprite = new RenderPassSprite();
        mRenderer.addRenderPass(mRenderPassSprite);
    }

    public void release() {
        if (mRenderPassSprite != null) {
            mRenderPassSprite.releaseResources();
        }
    }

}
