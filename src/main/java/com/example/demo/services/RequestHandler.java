package com.example.demo.services;


import com.example.demo.components.SlackResponse;
import com.example.demo.test.TestRunner;
import com.example.demo.test.TestRunnerWithTimeFrame;
import net.minidev.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class RequestHandler {
    public static SlackResponse getAllFailures(String responseUrl,String text) throws IOException, ClassNotFoundException {
        String[] temp = text.split(" ");
        if(temp.length!=1){
            return new SlackResponse()
                    .setResponseType("ephemeral")
                    .setText("Invalid Command. \n there should be a Build Number after the command.");
        }

        int buildNr = Integer.parseInt(text);

        Thread thread = new Thread(()-> {
            try {
                HashMap<String, String> authorMap = TestRunner.getAuthorMap();
                String mapTable = MapUtils.getMapAsTableString(authorMap);
                List<String> allFailedTests = JenkinsParser.getFailuresList(buildNr);
                HashMap<String, String> fullClassName = TestRunner.getFullClassName();

                LinkedHashMap<String, String> failuresByAuthor = new LinkedHashMap<>();

                for (String test : allFailedTests) {
                    failuresByAuthor.put(test, authorMap.get(test));
                }

                String failureTestAuthorMapTable = MapUtils.getMapAsTableString(failuresByAuthor);

                String payload = "```Here are all the failured tests in build "+ buildNr+":\n" +failureTestAuthorMapTable+ " ```";

                System.out.println(failureTestAuthorMapTable);

                
                // Sending HTTP Post request with the processed payload to Slack channel
                URL url = new URL(responseUrl);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                http.setRequestMethod("POST");
                http.setDoOutput(true);
                http.setRequestProperty("Accept", "application/json");
                http.setRequestProperty("Content-Type", "application/json");

                String data = "{\n  \"status\":\"200\",\"mrkdwn\":\"true\",\"response_type\":\"in_channel\",\n  \"text\":\""+payload+" \"\n}";
                System.out.println(data);

                byte[] out = data.getBytes(StandardCharsets.UTF_8);

                OutputStream stream = http.getOutputStream();
                stream.write(out);

                System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
                http.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        return new SlackResponse().setText("Thanks for your request, we'll process it and get back to you.");

    }

    public static SlackResponse getTestAddedByAuthorInTimeframe(String responseUrl,String text) throws IOException, ClassNotFoundException {
        Set<String> allowedTimeFrames = Set.of("LastWeek","LastSevenDays","ThisWeek","ThisMonth");

        String[] temp = text.split(" ");
        if(temp.length!=3){
            return new SlackResponse()
                    .setResponseType("ephemeral")
                    .setText("Invalid Command. \n there should be Author name, TimeFrame and Build Number after the command.");
        }
        String author = temp[0];
        String timeFrame = temp[1];
        System.out.println(temp[0] + " : " + temp[1] + " : " + temp[2]);
        int buildNr = Integer.parseInt(temp[2]);

        if(!allowedTimeFrames.contains(timeFrame)){
            return new SlackResponse()
                    .setResponseType("ephemeral")
                    .setText("Invalid TimeFrame.\nThese are allowed timeFrames: LastWeek, LastSevenDays,ThisWeek,ThisMonth.\n");
        }

        Thread thread = new Thread(()-> {
            try{
                TestRunnerWithTimeFrame.timeFrameSetup(timeFrame);
                HashMap<String,String> authorMap = TestRunnerWithTimeFrame.getAuthorMap();
                HashMap<String,String> fullClassName = TestRunnerWithTimeFrame.getFullClassName();

                List<String> testsByAuthor = MapUtils.getAllTestsOfAuthor(authorMap,author);

                List<String> allFailedTests = JenkinsParser.getFailuresList(buildNr);
                List<String> failedTestsByAuthor = MapUtils.getAllFailedTestsByAuthor(testsByAuthor,allFailedTests,author);

                String embeddedListTable = MapUtils.getListAsTableString(failedTestsByAuthor,fullClassName,buildNr);

                System.out.println(embeddedListTable);

                String payload = null;
                if(allFailedTests.size()>0) {
                    payload = "Tests added by " + author + " in " + timeFrame + " : " + testsByAuthor.size();
                    payload += "\nHere are the failed tests: \n";
                    payload += embeddedListTable;
                    payload = "```" + payload + "```";
                }
                else payload="Jenkins File with build number "+buildNr+" is not accessible. ";


                // Sending HTTP Post request with the processed payload to Slack channel
                URL url = new URL(responseUrl);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                http.setRequestMethod("POST");
                http.setDoOutput(true);
                http.setRequestProperty("Accept", "application/json");
                http.setRequestProperty("Content-Type", "application/json");

                String data = "{\n  \"status\":\"200\",\"response_type\":\"in_channel\",\n  \"text\":\""+payload+"\"\n}";
                System.out.println(data);

                byte[] out = data.getBytes(StandardCharsets.UTF_8);

                OutputStream stream = http.getOutputStream();
                stream.write(out);

                System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
                http.disconnect();

            } catch (ProtocolException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        });
        System.out.println("Thread Starting");
        thread.start();

        return new SlackResponse()
                .setResponseType("ephemeral")
                .setText("Thanks for your request, we'll process it and get back to you.");

    }
}
