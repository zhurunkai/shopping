package com.servlet.order;

import java.util.ArrayList;
import java.util.Arrays;

public class haha {
    public static void main(String[] args) {
        String a = "1,2,3&4,5,6&7,8,9";
        String[] b = a.split("&");
        ArrayList<String[]> j = new ArrayList<>();
        for (String s : b) {
            String[] v = s.split(",");
            j.add(v);
        }
    }
}
