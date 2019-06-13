package com.example.demo.service;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.BasicProject;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.User;
import com.atlassian.jira.rest.client.domain.input.FieldInput;
import com.atlassian.jira.rest.client.domain.input.IssueInput;
import com.atlassian.jira.rest.client.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.domain.input.TransitionInput;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;

import org.apache.log4j.Logger;

/**
 * @Author yyhu3
 * @Date 2019-01-12 13:38
 */
public class TestJira {
    Logger logger = Logger.getLogger(TestJira.class);
    static JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
    static NullProgressMonitor pm = new NullProgressMonitor();
    static String uri="http://localhost:8100";
    static String user="hzhuyuanyuan";
    static String pwd="TODO";

    public static void main(String[] args) {
        // TODO Auto-generated method stub


    }

    public static void getIssue(String issueKey) throws URISyntaxException{

        URI jiraServerUri = new URI(uri);
        JiraRestClient restClient = factory.createWithBasicHttpAuthentication(
                jiraServerUri, user, pwd);
        //get issue through issueKey
        Issue issue = restClient.getIssueClient().getIssue(issueKey, pm);
        //grab user info
        User usr = restClient.getUserClient().getUser(user, pm);
        System.out.println(usr);
        System.out.println(issue);
    }



    //
 /*
 * create the issue
 * @param Issue type :	1L:bug; 2L:new requirement; 3L:task; 4L.improvement
 * @param projectName:such as "GTP",the project short name
 * @param Longid:such as 10000
 * @param Summary:topic
 * @param Description:
 */
    public static void createIssue(String projectName,String issueType,String description,String summary) throws URISyntaxException{
        //JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
        URI jiraServerUri = new URI(uri);
        JiraRestClient restClient = factory.createWithBasicHttpAuthentication(
                jiraServerUri, user, pwd);
        IssueInputBuilder issueBuilder = new IssueInputBuilder("ProjectKey",
                Long.valueOf(issueType));


        final URI projectUri = new URI(
                "http://localhost:8100/rest/api/2/project/IT");
        BasicProject bporject = new BasicProject(projectUri, projectName, "",
                (long) 10000);
        issueBuilder.setProject(bporject);
        issueBuilder.setDescription(description);
        issueBuilder.setSummary(summary);


        IssueInput issueInput = issueBuilder.build();
        BasicIssue bIssue = restClient.getIssueClient().createIssue(issueInput,
                pm);
        // print the newly created issuekey
        System.out.println(bIssue.getKey());
    }  public static void updateIssueField(String key,String field,String value) throws URISyntaxException{


        URI jiraServerUri = new URI(uri);
        JiraRestClient restClient = factory.createWithBasicHttpAuthentication(
                jiraServerUri, user, pwd);
        //get the issue through issuekey
        Issue issue = restClient.getIssueClient().getIssue(key, pm);
        //update the default field environment,if the field is defined by self,pls use the filed FieldInput fieldinput = new FieldInput("environment", value);
        FieldInput fieldinput = new FieldInput("environment", value);
        List<FieldInput> fields = new ArrayList<FieldInput>();
        fields.add(fieldinput);
        restClient.getIssueClient().update(issue, fields, pm);
    }




    public static void changeIssueStatus(String issuekey) throws URISyntaxException
    {


        URI jiraServerUri = new URI(uri);
        JiraRestClient restClient = factory
                .createWithBasicHttpAuthentication(jiraServerUri, user,
                        pwd);
        Issue issue = restClient.getIssueClient().getIssue(issuekey, pm);
//the number 3 is involed below picture,you can focus on the red rectangular
        TransitionInput tinput= new TransitionInput(3);
        restClient.getIssueClient().transition(issue,tinput, pm);
    }
}
