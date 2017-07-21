package ru.ns7.ns7_face;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import fr.arnaudguyon.smartgl.opengl.SmartGLView;
import ru.ns7.ns7_face.sound.AudioListener;

public class MainActivity extends Activity {

    private SmartGLView mActivityGLView;

    private AudioListener audioListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        setContentView(R.layout.activity_main);

        mActivityGLView = (SmartGLView) findViewById(R.id.faceView);
        mActivityGLView.setDefaultRenderer(this);
        // mActivityGLView.getSmartGLRenderer().setMflip2DProj(true);

        audioListener = new AudioListener();

        mActivityGLView.setController(new GLViewController(audioListener));


    }

    @Override
    protected void onStart() {
        super.onStart();
        audioListener.checkPermissionsAndStart(this);
    }

    @Override
    protected void onPause() {
        if (mActivityGLView != null) {
            mActivityGLView.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mActivityGLView != null) {
            mActivityGLView.onResume();
        }
    }
}
