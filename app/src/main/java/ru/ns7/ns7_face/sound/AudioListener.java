package ru.ns7.ns7_face.sound;


import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Looper;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;


public class AudioListener {
    private static final int REQUEST_PERMISSION_RECORD_AUDIO = 1;

    private static final int RECORDER_SAMPLE_RATE = 44100;
    private static final int RECORDER_CHANNELS = 1;
    private static final int RECORDER_ENCODING_BIT = 16;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    private static final int MAX_DECIBELS = 120;

    private AudioRecord audioRecord;

    private Thread recordingThread;
    public byte[] buffer;

    private float[] amplitudes;

    public void checkPermissionsAndStart(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_PERMISSION_RECORD_AUDIO);
        } else {
            initRecorder();
            if (audioRecord.getState() == AudioRecord.STATE_INITIALIZED) {
                startRecording();
            }
        }
    }

    private void initRecorder() {
        final int bufferSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLE_RATE,
                RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, RECORDER_SAMPLE_RATE,
                RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING, bufferSize);
        //AudioUtil.initProcessor(RECORDER_SAMPLE_RATE, RECORDER_CHANNELS, RECORDER_ENCODING_BIT);

        recordingThread = new Thread("recorder") {
            @Override
            public void run() {
                super.run();
                buffer = new byte[bufferSize];
                Looper.prepare();
                audioRecord.setRecordPositionUpdateListener(recordPositionUpdateListener, new Handler(Looper.myLooper()));
                int bytePerSample = RECORDER_ENCODING_BIT / 8;
                float samplesToDraw = bufferSize / bytePerSample;
                audioRecord.setPositionNotificationPeriod((int) samplesToDraw);
                //We need to read first chunk to motivate recordPositionUpdateListener.
                //Mostly, for lower versions - https://code.google.com/p/android/issues/detail?id=53996
                audioRecord.read(buffer, 0, bufferSize);
                Looper.loop();
            }
        };
    }

    private AudioRecord.OnRecordPositionUpdateListener recordPositionUpdateListener = new AudioRecord.OnRecordPositionUpdateListener()
    {
        @Override
        public void onMarkerReached(AudioRecord recorder) {
            // empty for now
        }

        @Override
        public void onPeriodicNotification(AudioRecord recorder) {

            if (audioRecord.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING
                    && audioRecord.read(buffer, 0, buffer.length) != -1) {
                // TODO

            }
        }
    };

    private void startRecording() {
        if (audioRecord != null) {
            audioRecord.startRecording();
        }
        recordingThread.start();
    }

    public void update()
    {
        if (audioRecord.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING
                && audioRecord.read(buffer, 0, buffer.length) != -1) {
            // TODO
        }
    }

}
