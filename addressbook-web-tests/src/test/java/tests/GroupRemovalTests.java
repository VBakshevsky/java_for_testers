package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        app.openGroupsPage();
        if (!app.isThereARecord()) {
            app.createGroup(new GroupData("group name", "group header", "group footer"));
        }
        app.removeGroup();
    }

}
