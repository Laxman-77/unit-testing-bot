package com.example.demo.services;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class JenkinsParser {

    public static ArrayList<String> getFailuresList( int buildNr) throws IOException {
        System.out.println("Parsing of Jenkins Report Started.");
        ArrayList<String> failedTests = new ArrayList<>();

        try{

            String JENKINS_URL = "https://qa4-automation-jenkins-reports.sprinklr.com/CI_Test/builds/"+ buildNr +"/htmlreports/Reports/index.html";
            //String _oauth2_proxy = System.getenv("_oauth2_proxy");

            String _oauth2_proxy = "bGF4bWFuLmdvbGl5YUBzcHJpbmtsci5jb20=|1625800993|4DcF2ldy1WYCEJgHcgGuabXWUvs=";
            /**
             * To get your _oauth2_proxy,
             * 1. go to the jenkins report url
             * 2. open the inspect page and go to network
             * 3. reload the tab, You will see 4 html-css files in your network
             * 4. click on the index.html
             * 5. go to the cookies
             * 6. find the value of _oauth2_proxy and copy it.
             * 7. You can paste it here but if your code is shared then you should set it as system environment variable and use it from there.
             * 8. For setting it as env variable, you can type export _oauth2_proxy = xxxxxxxxx(your _auth2_proxy) in .bash_profile or .zprofile file.
            */

            Document doc = Jsoup.connect(JENKINS_URL)
                            .userAgent("Chrome")
                            .cookie("_oauth2_proxy",_oauth2_proxy)
                            .get();

            ArrayList<String> list = new ArrayList<>();
            doc.select(".linkList li a").forEach(el -> list.add(el.text()));

            for(int i=0; i<list.size(); i+=2){
                failedTests.add(list.get(i)+". "+list.get(i+1)); // first line class name and second line test name
            }

        }catch(IOException ioe){
            System.out.println("Exception: Exception in parsing of Jenkins Report. \nPlease check your internet connection and VPN.\nIt is also possible that the Jenkins File with this build number doesn't exist." + ioe);
        }
        finally {
            return failedTests;
        }
    }
}
