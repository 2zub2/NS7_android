package ru.ns7.ns7_face.mouth;


import android.content.Context;

import fr.arnaudguyon.smartgl.opengl.Sprite;
import fr.arnaudguyon.smartgl.opengl.Texture;
import ru.ns7.ns7_face.R;

public class BarRect extends Sprite {
    public static final int WIDTH = 5;
    public static final int HEIGHT = 1;

    Texture spriteTexture;

    public BarRect(Context context) {
        super(WIDTH, HEIGHT);

        spriteTexture = new Texture(context, R.drawable.bar_rect);
        setTexture(spriteTexture);
    }

    public void release() {
        if (spriteTexture != null) {
            spriteTexture.release();
        }
    }
}
