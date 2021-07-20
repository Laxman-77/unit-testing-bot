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


    @RequestMapping(value = "/getTestCount",
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


    @RequestMapping("/")
    public SlackResponse home(){
        return new SlackResponse("This is home page. ");
    }
}
