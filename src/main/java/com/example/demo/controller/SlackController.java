package com.example.demo.controller;


import com.example.demo.components.SlackResponse;
import com.example.demo.services.RequestHandler;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Set;

@RestController
public class SlackController {
    private static final Set<String> ALLOWED_CHANNELS = Set.of("unit-test-bot","paid-backend","random");
    private static final Set<String> ALLOWED_DOMAINS = Set.of("unit-test-bot","sprinklr");

    @RequestMapping(value = "/getAllFailures",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public SlackResponse onReceiveGetAllFailures(@RequestParam("team_id") String teamId,
                                                 @RequestParam("team_domain") String teamDomain,
                                                 @RequestParam("channel_id") String channelId,
                                                 @RequestParam("channel_name") String channelName,
                                                 @RequestParam("user_id") String userId,
                                                 @RequestParam("user_name") String userName,
                                                 @RequestParam("command") String command,
                                                 @RequestParam("text") String text,
                                                 @RequestParam("response_url") String responseUrl) throws IOException, ClassNotFoundException
    {

        if(!ALLOWED_DOMAINS.contains(teamDomain)) return new SlackResponse("Your teamDomain is not authorized to use this bot.");
        if(!ALLOWED_CHANNELS.contains(channelName)) return new SlackResponse("This channel is not authorized to use this bot.");
        try {
            SlackResponse response = RequestHandler.getAllFailures(responseUrl,text);
            return response;
        }
        catch(Exception e){
            e.printStackTrace();

            return new SlackResponse().setText("Sorry to say but some exception occurred.");
        }
    }


    @RequestMapping(value = "/getTestCountAll",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public SlackResponse onReceiveGetTestCount(@RequestParam("team_id") String teamId,
                                                 @RequestParam("team_domain") String teamDomain,
                                                 @RequestParam("channel_id") String channelId,
                                                 @RequestParam("channel_name") String channelName,
                                                 @RequestParam("user_id") String userId,
                                                 @RequestParam("user_name") String userName,
                                                 @RequestParam("command") String command,
                                                 @RequestParam("text") String text,
                                                 @RequestParam("response_url") String responseUrl) throws IOException, ClassNotFoundException
    {

        if(!ALLOWED_DOMAINS.contains(teamDomain)) return new SlackResponse("Your teamDomain is not authorized to use this bot.");
        if(!ALLOWED_CHANNELS.contains(channelName)) return new SlackResponse("This channel is not authorized to use this bot.");
        try {
            SlackResponse response = RequestHandler.getTestCount(responseUrl);
            return response;
        }
        catch(Exception e){
            e.printStackTrace();

            return new SlackResponse().setText("Sorry to say but some exception occurred.");
        }
    }

    @RequestMapping(value = "/getTestCount",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public SlackResponse onReceiveGetTestCountInTimeFrame(@RequestParam("team_id") String teamId,
                                               @RequestParam("team_domain") String teamDomain,
                                               @RequestParam("channel_id") String channelId,
                                               @RequestParam("channel_name") String channelName,
                                               @RequestParam("user_id") String userId,
                                               @RequestParam("user_name") String userName,
                                               @RequestParam("command") String command,
                                               @RequestParam("text") String text,
                                               @RequestParam("response_url") String responseUrl) throws IOException, ClassNotFoundException
    {

        if(!ALLOWED_DOMAINS.contains(teamDomain)) return new SlackResponse("Your teamDomain is not authorized to use this bot.");
        if(!ALLOWED_CHANNELS.contains(channelName)) return new SlackResponse("This channel is not authorized to use this bot.");
        try {
            SlackResponse response = RequestHandler.getTestCountInTimeFrame(responseUrl,text);
            return response;
        }
        catch(Exception e){
            e.printStackTrace();

            return new SlackResponse().setText("Sorry to say but some exception occurred.");
        }
    }

    @RequestMapping(value = "/getTestsAddedByAuthor",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public SlackResponse onReceiveGetTestsAddedByAuthor(@RequestParam("team_id") String teamId,
                                                      @RequestParam("team_domain") String teamDomain,
                                                      @RequestParam("channel_id") String channelId,
                                                      @RequestParam("channel_name") String channelName,
                                                      @RequestParam("user_id") String userId,
                                                      @RequestParam("user_name") String userName,
                                                      @RequestParam("command") String command,
                                                      @RequestParam("text") String text,
                                                      @RequestParam("response_url") String responseUrl) throws IOException, ClassNotFoundException
    {

        System.out.println(command + " " + text);
        System.out.println(responseUrl);
        //System.out.println(token);
        System.out.println(teamDomain);
        System.out.println(channelName);
        System.out.println(userName);
        if(!ALLOWED_DOMAINS.contains(teamDomain)) return new SlackResponse("Your teamDomain is not authorized to use this bot.");
        if(!ALLOWED_CHANNELS.contains(channelName)) return new SlackResponse("This channel is not authorized to use this bot.");
        try {
            SlackResponse response = RequestHandler.getTestAddedByAuthorInTimeframe(responseUrl,text);

            return response;
        }
        catch(Exception e){
            e.printStackTrace();
            return new SlackResponse().setText("Sorry to say but some exception occurred.");
        }

    }


    @RequestMapping(value = "/getTestsFailedByAuthor",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public SlackResponse onReceiveGetTestsFailedByAuthor(@RequestParam("team_id") String teamId,
                                                        @RequestParam("team_domain") String teamDomain,
                                                        @RequestParam("channel_id") String channelId,
                                                        @RequestParam("channel_name") String channelName,
                                                        @RequestParam("user_id") String userId,
                                                        @RequestParam("user_name") String userName,
                                                        @RequestParam("command") String command,
                                                        @RequestParam("text") String text,
                                                        @RequestParam("response_url") String responseUrl) throws IOException, ClassNotFoundException
    {

        System.out.println(command + " " + text);
        System.out.println(responseUrl);
        //System.out.println(token);
        System.out.println(teamDomain);
        System.out.println(channelName);
        System.out.println(userName);
        if(!ALLOWED_DOMAINS.contains(teamDomain)) return new SlackResponse("Your teamDomain is not authorized to use this bot.");
        if(!ALLOWED_CHANNELS.contains(channelName)) return new SlackResponse("This channel is not authorized to use this bot.");
        try {
            SlackResponse response = RequestHandler.getTestFailedByAuthorInTimeframe(responseUrl,text);

            return response;
        }
        catch(Exception e){
            e.printStackTrace();
            return new SlackResponse().setText("Sorry to say but some exception occurred.");
        }

    }

    @RequestMapping(value = "/help",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public SlackResponse onReceiveHelp(@RequestParam("team_id") String teamId,
                                                         @RequestParam("team_domain") String teamDomain,
                                                         @RequestParam("channel_id") String channelId,
                                                         @RequestParam("channel_name") String channelName,
                                                         @RequestParam("user_id") String userId,
                                                         @RequestParam("user_name") String userName,
                                                         @RequestParam("command") String command,
                                                         @RequestParam("text") String text,
                                                         @RequestParam("response_url") String responseUrl) throws IOException, ClassNotFoundException
    {

        System.out.println(command + " " + text);
        System.out.println(responseUrl);
        //System.out.println(token);
        System.out.println(teamDomain);
        System.out.println(channelName);
        System.out.println(userName);
        if(!ALLOWED_DOMAINS.contains(teamDomain)) return new SlackResponse("Your teamDomain is not authorized to use this bot.");
        if(!ALLOWED_CHANNELS.contains(channelName)) return new SlackResponse("This channel is not authorized to use this bot.");
        try {
            SlackResponse response = new SlackResponse(
                    " ```      All commands:\n" +
                            "      1. /get_all_failures <build-no> : Get all failures in the given build With their authors\n" +
                            "      2. /get_test_count <time-frame> : Get map of author-test_counts added in given time-frame\n" +
                            "      3. /get_tests_added_by_author <author> <time-frame> : Get count of new tests added by author\n" +
                            "      4. /get_tests_failed_by_author <author> <time-frame> <build-no> : Get failed tests written by author in given timeframe\n" +
                            "      5. /help : Get list of commands\n" +
                            "     \n" +
                            "        Supported Time Frames : ThisWeek , LastWeek , LastSevenDays , ThisMonth , LifeTime ```");
            System.out.println(response.getText());
            return response;
        }
        catch(Exception e){
            e.printStackTrace();
            return new SlackResponse().setText("Sorry to say but some exception occurred.");
        }

    }

    /**
     * All commands:
     * 1. /get_all_failures <build-no> : Get all failures in the given build With their authors
     * 2. /get_test_count <time-frame> : Get map of author-test_counts added in given time-frame
     * 3. /get_tests_added_by_author <author> <time-frame> : Get count of new tests added by author
     * 4. /get_tests_failed_by_author <author> <time-frame> <build-no> : Get failed tests written by author in given timeframe
     * 5. /help : Get list of commands
     *
     *   Supported Time Frames : ThisWeek , LastWeek , LastSevenDays , ThisMonth , LifeTime
     */

    @RequestMapping("/")
    public SlackResponse home(){
        return new SlackResponse("This is home page. ");
    }
}
