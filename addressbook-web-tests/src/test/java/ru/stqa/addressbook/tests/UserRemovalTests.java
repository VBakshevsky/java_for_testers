package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class UserRemovalTests extends TestBase {

    @Test
    public void canDeleteUser() {
        createRandomUser();
        var oldUsers = app.hbm().getUsersList();
        var rnd = new Random();
        var index = rnd.nextInt(oldUsers.size());
        app.users().removeUser(oldUsers.get(index));
        var newUsers = app.hbm().getUsersList();
        var expectedList = new ArrayList<>(oldUsers);
        expectedList.remove(index);
        Assertions.assertEquals(newUsers, expectedList);
    }

    @Test
    void canRemoveAllUsersAtOnce() {
        createRandomUser();
        app.users().removeAllUsers();
        Assertions.assertEquals(0, app.hbm().getUserCount());
    }

    @Test
    public void canRemoveUserFromGroup() {
        createRandomUser();
        createRandomGroup();
        var groups = app.hbm().getGroupList();
        var oldUsers = app.hbm().getUsersList();
        var rnd = new Random();
        var index = rnd.nextInt(oldUsers.size());
        var indexGroup = rnd.nextInt(groups.size());
        var user = oldUsers.get(index);
        var group = groups.get(indexGroup);
        var oldRelated = app.hbm().getUsersInGroup(group);
        var expectedUserListInGroup = new ArrayList<>(oldRelated);
        expectedUserListInGroup.remove(user);
        if (!app.hbm().isUsersInGroup(group, user)) {
            app.users().addUserToGroup(user, group);
        }
        app.users().removeUserFromGroup(user, group);
        var newRelated = app.hbm().getUsersInGroup(group);
//        Comparator<UserData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
        var expectedList = new ArrayList<>(newRelated);
//        expectedUserListInGroup.sort(compareById);
//        expectedList.sort(compareById);
        Assertions.assertEquals(Set.copyOf(expectedUserListInGroup), Set.copyOf(expectedList));
    }
}
