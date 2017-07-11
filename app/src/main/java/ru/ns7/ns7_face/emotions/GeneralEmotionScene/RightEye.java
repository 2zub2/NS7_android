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

        for (int i=0; i<9; i++) {
            for (int j=0; j<6; j++) {
                if (i == 0 && j == 0) {
                    addFrame(10, j/6f, i/9f, (j+1)/6f, (i+1)/9f);
                    continue;
                }
                addFrame(0.001f, j/6f, i/9f, (j+1)/6f, (i+1)/9f);
            }
        }

    }

    public void release() {
        if (mSpriteTexture != null) {
            mSpriteTexture.release();
        }
    }
}
