package com.example.logintest.data.sensors;

import java.util.ArrayList;

public class TestDataManager implements java.io.Serializable {

    private final ArrayList<TestData> accelData;
    private final ArrayList<TestData> gyroData;
    private final ArrayList<TestData> gravData;
    private final ArrayList<TestData> linAccelData;
    private final ArrayList<TestData> rotData;
    private final ArrayList<TestData> stepData;

    public TestDataManager() {
        accelData = new ArrayList<>();
        gyroData = new ArrayList<>();
        gravData = new ArrayList<>();
        linAccelData = new ArrayList<>();
        rotData = new ArrayList<>();
        stepData = new ArrayList<>();
    }

    // App side
    public void addAccelData(TestData d) {
        accelData.add(d);
    }

    public void addGyroData(TestData d) {
        gyroData.add(d);
    }

    public void addGravData(TestData d) {
        gravData.add(d);
    }

    public void addLinAccelData(TestData d) {
        linAccelData.add(d);
    }

    public void addRotData(TestData d) {
        rotData.add(d);
    }

    public void addStepData(TestData d) {
        stepData.add(d);
    }

    // MATLAB side
    public ArrayList<TestData> getAccelData() {
        return accelData;
    }

    public ArrayList<TestData> getGyroData() {
        return gyroData;
    }

    public ArrayList<TestData> getGravData() {
        return gravData;
    }

    public ArrayList<TestData> getLinAccelData() {
        return linAccelData;
    }

    public ArrayList<TestData> getRotData() {
        return rotData;
    }

    public ArrayList<TestData> getStepData() {
        return stepData;
    }
}
