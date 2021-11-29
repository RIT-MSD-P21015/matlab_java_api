import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import com.example.logintest.data.sensors.TestData;
import com.example.logintest.data.sensors.TestDataManager;

public class UserData {
    public int id;
    public String firstname;
    public String lastname;
    public String email;
    public String creation_timestamp;
    public String modification_timestamp;
    public String survey_timestamp;
    public String tests_timestamp;
    public String result;
    public String result_timestamp;
    public String survey;
    public String tests;

    private HashMap<Integer, TestDataManager> TestDataManagers;

    public enum Sensor { 
        ACCEL,
        GYRO,
        GRAV,
        LINACCEL,
        ROTDATA,
        STEPCNT
    }

    // returns true on success
    public boolean deserializeTestData() {

        byte[] data = Base64.getDecoder().decode(tests);
        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            TestDataManagers = (HashMap) ois.readObject();
            ois.close();
        }
        catch (IOException obj1) {
            System.out.println("IOException");
            return false;
        }
        catch (ClassNotFoundException obj2) {
            System.out.println("Class not found");
            return false;
        }

        return true;
    }

    public TestData[] getSensorData(int testnum, Sensor sensor) {

        TestDataManager tdm = TestDataManagers.get(testnum);
        TestData[] data;
        String currentSensor = "no";

        try {
            switch(sensor) {
                case ACCEL:
                    currentSensor = "accel";
                    data = new TestData[tdm.getAccelData().size()];
                    data = tdm.getAccelData().toArray(data);
                    break;
                case GYRO:
                    currentSensor = "gyro";
                    data = new TestData[tdm.getGyroData().size()];
                    data = tdm.getGyroData().toArray(data);
                    break;
                case GRAV:
                    currentSensor = "grav";
                    data = new TestData[tdm.getGravData().size()];
                    data = tdm.getGravData().toArray(data);
                    break;
                case LINACCEL:
                    currentSensor = "lin accel";
                    data = new TestData[tdm.getLinAccelData().size()];
                    data = tdm.getLinAccelData().toArray(data);
                    break;
                case ROTDATA:
                    currentSensor = "rotational ";
                    data = new TestData[tdm.getRotData().size()];
                    data = tdm.getRotData().toArray(data);
                    break;
                case STEPCNT:
                    currentSensor = "step count";
                    data = new TestData[tdm.getStepData().size()];
                    data = tdm.getStepData().toArray(data);
                    break;
                default:
                    currentSensor = "no";
                    data = null;
                    break;
            }
        }
        catch(NullPointerException e) {
            System.out.println(currentSensor + " sensor data for test #" + testnum + " not found.");
            data = null;
        }

        return data;
    }
}
