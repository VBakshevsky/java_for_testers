import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        openGroupsPage();
        if (!isThereARecord()) {
            createGroup(new GroupData("group name", "group header", "group footer"));
        }
        removeGroup();
    }

}
