package com.example.logintest.data.sensors;

public class TestData implements java.io.Serializable {

    private final int accuracy;
    private final long timestamp;
    private final float[] values;

    public TestData(int accuracy, long timestamp, float[] values) {
        this.accuracy = accuracy;
        this.timestamp = timestamp;
        this.values = values;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public float[] getValues() {
        return values;
    }
}
