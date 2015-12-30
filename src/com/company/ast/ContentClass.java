package com.company.ast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ContentClass {

    public String getContent(String path) {
        File f = new File(path);
        return getContent(f);
    }

    public String getContent(File f) {

        String content = "";

        try {
            content = new Scanner(f).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return content;
    }

    public String getContent(String src, Class c) {
        String path = src + c.getPackage().getName().replaceAll("\\.", "\\/") + "/" + c.getSimpleName() + ".java";
        return getContent(path);
    }

}
