package com.treepal.utils;

import java.util.regex.Pattern;

public class StringUtil {
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

}
