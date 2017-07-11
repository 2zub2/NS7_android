package ru.ns7.ns7_face.emotions.GeneralEmotionScene;


import android.content.Context;
import fr.arnaudguyon.smartgl.opengl.Texture;
import ru.ns7.ns7_face.R;



public class LeftEye extends RightEye {


    public LeftEye(Context mContext) {
        super(mContext);

        mSpriteTexture = new Texture(mContext, R.drawable.left_eye);
        setTexture(mSpriteTexture);
    }

}
