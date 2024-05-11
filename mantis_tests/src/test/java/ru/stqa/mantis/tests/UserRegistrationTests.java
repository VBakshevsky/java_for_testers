package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends  TestBase{

    @Test
    void canRegisterUser() {

        String username = CommonFunctions.randomString(5);
        var email = String.format(String.format("%s@localhost", CommonFunctions.randomString(8)));
        app.jamesCli().addUser(email, "password");
        // создать пользователя (адрес) на почтовом сервере (JamesHelper)

        app.session().registrationUser(username,email);
         //заполняем форму созадния и отправляем (браузер)

        var messages = app.mail().receive(email,"password", Duration.ofSeconds(60));
        // ждем почту (MailHelper)

        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        var url = "";
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
        // извлекаем ссылку из письма

        app.session().finishRegistrationUser(url, username, "password");
        // проходим по ссылке завершаем решистрацию пользователя (браузер)

        app.http().login(username, "password");
        Assertions.assertTrue(app.http().isLoggedIn());
        // проверяем, что пользователь может залогиниться (HttpSessionHelper)
    }
}
