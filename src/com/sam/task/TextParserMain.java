package com.sam.task;

public class TextParserMain {

    public static void main(String[] args) throws Exception{
        String path = args[0];
        TextParser textParser = new TextParser();
        textParser.parseText(path);
    }
}
