package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.AfterEach;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.UserData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestBase {

    protected static ApplicationManager app;


    @BeforeEach
    public void setUp() throws IOException {
        if (app == null) {
            var properties = new Properties();
            properties.load(new FileReader(System.getProperty("target", "local.properties")));
            app = new ApplicationManager();
            app.init(System.getProperty("browser", "firefox"), properties);
        }
    }

    @AfterEach
    void checkDatabaseConsistency() {
        app.jdbc().checkConsistency();
    }

    public static void readingAFileFromJsonLineByLineUsers(ArrayList<UserData> result) throws IOException {
        var json = "";
        try (var reader = new FileReader("users.json");
             var breader = new BufferedReader(reader)
        ) {
            var line = breader.readLine();
            while (line != null) {
                json = json + line;
                line = breader.readLine();
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<UserData>>() {
        });
        result.addAll(value);
    }

    public static void readingAFileFromJsonUsers(ArrayList<UserData> result) throws IOException {
        var json = Files.readString(Paths.get("users.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<UserData>>() {
        });
        result.addAll(value);
    }

    public static void readingAFileFromXmlUsers(ArrayList<UserData> result) throws IOException {
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("users.xml"), new TypeReference<List<UserData>>() {
        });
        result.addAll(value);
    }

    public static void readingAFileFromJsonLineByLineGroups(ArrayList<GroupData> result) throws IOException {
        var json = "";
        try (var reader = new FileReader("groups.json");
             var breader = new BufferedReader(reader)
        ) {
            var line = breader.readLine();
            while (line != null) {
                json = json + line;
                line = breader.readLine();
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<GroupData>>() {
        });
        result.addAll(value);
    }

    public static void readingAFileFromJsonGroups(ArrayList<GroupData> result) throws IOException {
        var json = Files.readString(Paths.get("groups.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<GroupData>>() {
        });
        result.addAll(value);
    }

    public static void readingAFileFromXmlGroups(ArrayList<GroupData> result) throws IOException {
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("groups.xml"), new TypeReference<List<GroupData>>() {
        });
        result.addAll(value);
    }

    protected static void CreateAUserIfThereIsNone() {
        if (app.hbm().getUserCount() == 0) {
            app.hbm().createUser(new UserData().withMainInformation(CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomMail(), CommonFunctions.randomMail(), "+791711122233", "+791733322211", "+791722233311", CommonFunctions.randomFile("src/test/resources/images"), CommonFunctions.randomMail()));
        }
    }

    protected static void CreateAGroupIfThereIsNone() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", CommonFunctions.randomString(10), CommonFunctions.randomString(10), CommonFunctions.randomString(10)));
        }
    }
}
