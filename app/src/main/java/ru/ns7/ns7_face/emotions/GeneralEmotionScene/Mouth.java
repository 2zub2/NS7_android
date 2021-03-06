package ru.ns7.ns7_face.emotions.GeneralEmotionScene;

import android.content.Context;
import fr.arnaudguyon.smartgl.opengl.Sprite;
import fr.arnaudguyon.smartgl.opengl.Texture;
import ru.ns7.ns7_face.R;


public class Mouth extends Sprite {

    private  Texture spriteTexture;

    public Mouth(Context context) {
        super(250, 111);

        spriteTexture = new Texture(context, R.drawable.mouth);
        setTexture(spriteTexture);
    }

    public void release() {

        if (spriteTexture != null) {
            spriteTexture.release();
        }
    }
}
