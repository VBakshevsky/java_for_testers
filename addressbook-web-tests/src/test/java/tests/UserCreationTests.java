package tests;

import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class UserCreationTests extends TestBase {
    public static List<UserData> userProvider() {
        var result = new ArrayList<UserData>();
        for (var firstname : List.of("", "first name")) {
            for (var middlename : List.of("", "middle name")) {
                for (var lastname : List.of("", "last name")) {
                    for (var nickname : List.of("", "nickname")) {
                        for (var title : List.of("", "title")) {
                            for (var company : List.of("", "company")) {
                                for (var address : List.of("", "address")) {
                                    for (var home : List.of("", "home")) {
                                        for (var mobile : List.of("", "+712332112355")) {
                                            for (var work : List.of("", "work")) {
                                                for (var fax : List.of("", "fax")) {
                                                    for (var email : List.of("", "test@mail.ru")) {
                                                        for (var email2 : List.of("", "test1@mail.ru")) {
                                                            for (var email3 : List.of("", "test2@mail.ru")) {
                                                                for (var homepage : List.of("", "homepage")) {
                                                                    for (var bday : List.of("-", "5")) {
                                                                        for (var bmonth : List.of("-", "March")) {
                                                                            for (var byear : List.of("", "2001")) {
                                                                                for (var aday : List.of("-", "10")) {
                                                                                    for (var amonth : List.of("-", "May")) {
                                                                                        for (var ayear : List.of("", "2005")) {
                                                                                            result.add(new UserData(firstname, middlename, lastname, nickname,title,company,address,home,mobile,work,fax,email,email2,email3,homepage,bday,bmonth,byear,aday,amonth,ayear));
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new UserData(randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomMobileNumber(), randomString(i * 10), randomString(i * 10), randomMail(), randomMail(), randomMail(), randomString(i * 10), randomDay(), randomMonth(), randomYear(), randomDay(), randomMonth(), randomYear()));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    public void canCreateMultipleUsers(UserData user) {
        int userCount = app.users().getCountUsers();
        app.users().createUser(user);
        int NewUserCount = app.users().getCountUsers();
        Assertions.assertEquals(userCount + 1, NewUserCount);
    }

    public static List<UserData> negativeUerProvider() {
        var result = new ArrayList<UserData>(List.of(
                new UserData("first name'", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "-", "-", "", "-", "-", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeUerProvider")
    public void canNotCreateUser(UserData user) {
        int userCount = app.users().getCountUsers();
        app.users().createUser(user);
        int NewUserCount = app.users().getCountUsers();
        Assertions.assertEquals(userCount, NewUserCount);
    }

}
