package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserInfoTests extends TestBase {
    
    @Test
    void testAllElementsOnTheForm() {
        createRandomUser();
        var users = app.hbm().getUsersList();
        var expectedAddress = users.stream().collect(Collectors.toMap(user -> user.id(), user ->
                Stream.of(user.address())
                        .filter(s -> s != null && !"".equals(s))
                        .map(s -> s.replace("\r\n", "\n"))
                        .collect(Collectors.joining("\n"))
        ));
        var address = app.users().getAddress();
        var expectedEmails = users.stream().collect(Collectors.toMap(user -> user.id(), user ->
                Stream.of(user.email(), user.email2(), user.email3())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var emails = app.users().getEmails();
        var expectedPhones = users.stream().collect(Collectors.toMap(user -> user.id(), user ->
                Stream.of(user.home(), user.mobile(), user.work())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.users().getPhones();
        Assertions.assertEquals(expectedAddress, address);
        Assertions.assertEquals(expectedEmails, emails);
        Assertions.assertEquals(expectedPhones, phones);
    }

}
