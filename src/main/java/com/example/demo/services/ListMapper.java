package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;

public class ListMapper {

    public static HashMap<String,String> getAuthorMapForFailedTests(HashMap<String,String> authorMap, ArrayList<String> list){
        HashMap<String,String> failureAuthorMap = new HashMap<>();

        for(String testName: list){
            failureAuthorMap.put(testName,authorMap.get(testName));
        }

        //for(Map.Entry entry: failureAuthorMap.entrySet()) System.out.println("### "+entry);
        return failureAuthorMap;
    }
}
