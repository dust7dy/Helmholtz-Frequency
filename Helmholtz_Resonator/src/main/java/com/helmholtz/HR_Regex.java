package com.helmholtz;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HR_Regex {
    public HR_Regex(){}

    public String regex(String start, String end, String input){
        String result = "";
        Pattern pattern = Pattern.compile(start+"(.*?)"+end);
        Matcher matcher = pattern.matcher(input);
        try{
            while(matcher.find() ){
                result = matcher.group(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
