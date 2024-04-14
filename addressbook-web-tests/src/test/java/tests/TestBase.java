package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {

    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
            app.init(System.getProperty("browser", "firefox"));
        }
    }

    public String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char) ('a' + rnd.nextInt(26));
        }
        return result;
    }

    public String randomDay() {
        var rnd = new Random();
        var n = rnd.nextInt(31) + 1;
        return "" + n;
    }

    public String randomMonth() {
        var rnd = new Random();
        var n = rnd.nextInt(12);
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December",};
        return months[n];
    }
}
