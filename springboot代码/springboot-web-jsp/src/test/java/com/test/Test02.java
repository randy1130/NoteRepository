package com.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test02 {
    public static void main(String[] args) {
        List<Object> objects = Collections.synchronizedList(new ArrayList<>());
    }
}
