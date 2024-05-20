package ru.stqa.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.Identifier;
import io.swagger.client.model.Issue;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.UserData;
import io.swagger.client.model.User;
import io.swagger.client.model.UserAddResponse;
import io.swagger.client.api.UserApi;

public class RestApiHelper extends  HelperBase{
    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));
    }


    public void createIssue(IssueData issueData) {
        Issue issue = new Issue();
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());
        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);
        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setCategory(categoryId);

        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void registrationUser(UserData userData) {
        User user = new User(); // User | The user to add.
        user.setUsername(userData.username());
        user.setEmail(userData.email());

        UserApi apiInstance = new UserApi();
        try {
            UserAddResponse result = apiInstance.userAdd(user);
            System.out.println(result);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

}
