package ru.ns7.ns7_face;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import fr.arnaudguyon.smartgl.opengl.SmartGLView;
import ru.ns7.ns7_face.sound.AudioListener;

public class MainActivity extends Activity {

    private SmartGLView glView;
    private GLViewController glViewController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        setContentView(R.layout.activity_main);

        glView = (SmartGLView) findViewById(R.id.faceView);
        glView.setDefaultRenderer(this);
        // glView.getSmartGLRenderer().setMflip2DProj(true);

        glViewController = new GLViewController();
        glView.setController(glViewController);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (glViewController != null) {
            glViewController.onStart(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (glView != null) {
            glView.onPause();
        }

        if (glViewController != null) {
            glViewController.onPause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (glView != null) {
            glView.onResume();
        }

        if (glViewController != null) {
            glViewController.onResume();
        }
    }
}
