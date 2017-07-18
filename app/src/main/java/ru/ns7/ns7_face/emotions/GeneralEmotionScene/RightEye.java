package ru.ns7.ns7_face.emotions.GeneralEmotionScene;


import android.content.Context;

import fr.arnaudguyon.smartgl.opengl.AnimatedSprite;
import fr.arnaudguyon.smartgl.opengl.Texture;
import ru.ns7.ns7_face.R;

public class RightEye extends AnimatedSprite {

    protected Texture mSpriteTexture;


    public RightEye(Context mContext) {
        super(192, 128);

        mSpriteTexture = new Texture(mContext, R.drawable.right_eye);
        setTexture(mSpriteTexture);

        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (i == 0 && j == 0) {
                    addFrame(5, j/2f, i/9f, (j+1)/4f, (i+1)/4f);
                    continue;
                }
                addFrame(0.005f, j/4f, i/4f, (j+1)/4f, (i+1)/4f);
            }
        }

    }

    public void release() {
        if (mSpriteTexture != null) {
            mSpriteTexture.release();
        }
    }
}
