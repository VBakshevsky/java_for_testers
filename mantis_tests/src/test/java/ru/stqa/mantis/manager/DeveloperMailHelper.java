package ru.stqa.mantis.manager;

import okhttp3.OkHttpClient;

public class DeveloperMailHelper extends  HelperBase {
    OkHttpClient client;
    public DeveloperMailHelper(ApplicationManager manager) {
        super(manager);
    }
}
