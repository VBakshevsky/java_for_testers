package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.stqa.addressbook.manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.UserData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestBase {

    protected static ApplicationManager app;
    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
            app.init(System.getProperty("browser", "firefox"));
        }
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




}
