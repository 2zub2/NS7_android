package ru.ns7.ns7_face.sound;


public abstract class AudioVisualisation {
    protected AudioVisualizationStream audioVisualizationStream;

    public void linkToAudioByteStream(AudioVisualizationStream audioVisualizationStream)
    {
        this.audioVisualizationStream = audioVisualizationStream;
    }
}
