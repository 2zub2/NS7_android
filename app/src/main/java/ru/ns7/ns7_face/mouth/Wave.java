package ru.ns7.ns7_face.mouth;


public class Wave {
    public static final int NUM_RECT = 44;

    private final float WAVE_SCALE = 200f;
    private final float COS_OFFSET = 0.2f;
    private final float INTERPOLATION = 0.2f;

    private float fps = 0;

    private int bytesPerSample = 2;

    private float[] sampleBuffer;
    private float[] waveValues;

    private float rmsValue;
    private float prevRmsValue;
    private float prevRmsValue1;

    public Wave()
    {
        waveValues = new float[NUM_RECT];
    }



    public void UpdateRms(byte[] buffer)
    {
        /*float sum = 0;
        sampleBuffer = byteToFloat(buffer);

        for (int i = 0; i < sampleBuffer.length; i++)
        {
            sampleBuffer[i] /= 32768;
            sum += sampleBuffer[i] * sampleBuffer[i];
        }*/
        float sample = 0;
        float sum = 0;
        int length = 0;
        int begin = 0;

        if (fps == 0) {
            length = 512;
            begin = 0;
        }

        if (fps == 1) {
            length = 1024;
            begin = 512;
        }

        if (fps == 2) {
            length = 1536;
            begin = 1024;
        }

        if (fps == 3) {
            length = 2048;
            begin = 1536;
            fps = 0;
        }

        fps++;


        for (int i = begin; i < length; i += 2) {
            sample = (float) ((buffer[i]) | buffer[i + 1] << 8) / 32768;
            sum += sample * sample;
        }

        //prevRmsValue1 = prevRmsValue;
        prevRmsValue = rmsValue;
        rmsValue = (float)Math.sqrt(sum / (buffer.length / 8));
    }

    public void UpdateWave(float[] scales)
    {
        float scale;

        for (int i = NUM_RECT - 1; i > 1; i--)
        {
            waveValues[i] = waveValues[i - 2];
        }
        waveValues[0] = 1f + rmsValue  * WAVE_SCALE;

        float val = (waveValues[0] + waveValues[2]) * INTERPOLATION;
        if (val < 1f) val = 1f;
        waveValues[1] = val;

        for (int i = 0; i < NUM_RECT; i++)
        {
            scale = (float)Math.sin((((float)i / NUM_RECT * (1f - 2 * COS_OFFSET)) + COS_OFFSET) * Math.PI) * waveValues[i];
            if (scale < 1f) scale = 1f;
            scales[i] = scale;
        }
    }
}
