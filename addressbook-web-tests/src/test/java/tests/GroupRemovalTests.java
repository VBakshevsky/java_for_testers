package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.groups().getCountGroup() == 0) {
            app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
        }
        int groupCount = app.groups().getCountGroup();
        app.groups().removeGroup();
        int newGroupCount = app.groups().getCountGroup();
        Assertions.assertEquals(groupCount - 1,newGroupCount);
    }
}
