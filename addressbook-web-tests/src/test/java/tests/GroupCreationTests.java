package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        int groupCount = app.groups().getCountGroup();
        app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        int newGroupCount = app.groups().getCountGroup();
        Assertions.assertEquals(groupCount + 1,newGroupCount);
    }

    @Test
    public void canCreateMultipleGroups() {
        int n = 5;
        int groupCount = app.groups().getCountGroup();
        for (int i = 0; i < n; i++) {
            app.groups().createGroup(new GroupData(randomString(i), "group header", "group footer"));
        }
        int newGroupCount = app.groups().getCountGroup();
        Assertions.assertEquals(groupCount + n,newGroupCount);
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }

}
