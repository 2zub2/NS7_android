package ru.ns7.ns7_face.emotions.GeneralEmotionScene;

import android.content.Context;
import fr.arnaudguyon.smartgl.opengl.Sprite;
import fr.arnaudguyon.smartgl.opengl.Texture;
import ru.ns7.ns7_face.R;


public class Mouth extends Sprite {

    private  Texture mSpriteTexture;

    public Mouth(Context mContext) {
        super(250, 111);

        mSpriteTexture = new Texture(mContext, R.drawable.mouth);
        setTexture(mSpriteTexture);
    }

    public void release() {

        if (mSpriteTexture != null) {
            mSpriteTexture.release();
        }
    }
}
