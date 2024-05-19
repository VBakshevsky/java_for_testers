package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {

        String user = CommonFunctions.randomString(5);
        var email = String.format("%s@localhost", CommonFunctions.randomString(8));
        app.jamesCli().addUser(email, "password");
        // создать пользователя (адрес) на почтовом сервере (JamesHelper)

        app.session().registrationUser(user, email);
        //заполняем форму созадния и отправляем (браузер)

        var messages = app.mail().receive(email, "password", Duration.ofSeconds(60));
        // ждем почту (MailHelper)

        var text = messages.get(0).content();
        var url = CommonFunctions.extractUrl(text);
        // извлекаем ссылку из письма

        app.session().finishRegistrationUser(url, user, "password");
        // проходим по ссылке завершаем решистрацию пользователя (браузер)

        app.http().login(user, "password");
        Assertions.assertTrue(app.http().isLoggedIn());
        // проверяем, что пользователь может залогиниться (HttpSessionHelper)
    }

    @Test
    void canRegisterUserWithRestApi() {

        String user = CommonFunctions.randomString(5);
        var email = String.format(String.format("%s@localhost", CommonFunctions.randomString(8)));
        app.jamesApi().addUser(email, "password");
        // создать пользователя (адрес) на почтовом сервере (JamesApi)

        app.session().registrationUser(user, email);
        //заполняем форму созадния и отправляем (браузер)

        var messages = app.mail().receive(email, "password", Duration.ofSeconds(60));
        // ждем почту (MailHelper)

        var text = messages.get(0).content();
        var url = CommonFunctions.extractUrl(text);
        // извлекаем ссылку из письма

        app.session().finishRegistrationUser(url, user, "password");
        // проходим по ссылке завершаем решистрацию пользователя (браузер)

        app.http().login(user, "password");
        Assertions.assertTrue(app.http().isLoggedIn());
        // проверяем, что пользователь может залогиниться (HttpSessionHelper)
    }

}
