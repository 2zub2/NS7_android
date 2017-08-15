package ru.ns7.ns7_face.mouth;


public class Wave {
    private final float WAVE_SCALE = 200f;
    private final float COS_OFFSET = 0.2f;
    private final float INTERPOLATION = 0.2f;

    private final boolean useAvaregeRms = true;
    private float prevRmsValue;

    private float[] waveValues;
    private float rmsValue;

    private int numRect;


    public Wave(int numRects)
    {
        this.numRect = numRects;
        waveValues = new float[this.numRect];
    }



    public void updateRms(byte[] buffer)
    {
        float sample;
        float sum = 0;
        int length;
        int begin = 0;

        length = buffer.length;

        for (int i = begin; i < length; i += 2) {
            sample = (float) ((buffer[i]) | buffer[i + 1] << 8) / 32768;
            sum += sample * sample;
        }

        rmsValue = (float)Math.sqrt(sum / (buffer.length / 2));
    }

    public void updateWave(float[] scales)
    {
        float scale;

        for (int i = numRect - 1; i > 1; i--)
        {
            waveValues[i] = waveValues[i - 2];
        }

        if (useAvaregeRms) {
            rmsValue = (rmsValue + prevRmsValue) / 2.0f;
            prevRmsValue = rmsValue;
        }

        waveValues[0] = 1f + rmsValue  * WAVE_SCALE;

        float val = (waveValues[0] + waveValues[2]) * INTERPOLATION;
        if (val < 1f) val = 1f;
        waveValues[1] = val;

        for (int i = 0; i < numRect; i++)
        {
            scale = (float)Math.sin((((float)i / numRect * (1f - 2 * COS_OFFSET)) + COS_OFFSET) * Math.PI) * waveValues[i];
            if (scale < 1f) scale = 1f;
            scales[i] = scale;
        }
    }
}
