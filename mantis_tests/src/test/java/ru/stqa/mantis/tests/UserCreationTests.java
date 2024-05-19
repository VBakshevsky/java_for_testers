package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.manager.HelperBase;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserCreationTests extends TestBase {

    DeveloperMailUser user;

    @Test
    void canRegisterUserWithDeveloperMail() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format(String.format("%s@developermail.com", user.name()));

        app.session().registrationUser(user.name(),email);

        var message = app.developerMail().receive(user, Duration.ofSeconds(300));
        var url = CommonFunctions.extractUrl(message);
        // извлекаем ссылку из письма

        app.session().finishRegistrationUser(url, user.name(), password);
        // проходим по ссылке завершаем решистрацию пользователя (браузер)

        app.http().login(user.name(), "password");
        Assertions.assertTrue(app.http().isLoggedIn());
        // проверяем, что пользователь может залогиниться (HttpSessionHelper)
    }

    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }

}
