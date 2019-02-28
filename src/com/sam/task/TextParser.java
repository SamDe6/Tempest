package com.sam.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByValue;

public class TextParser {
    //    private final ClassLoader classLoader = getClass().getClassLoader();
    private Map<String, Integer> wordMap;

    public TextParser() {
        wordMap = new HashMap<>();
    }

    public void parseText(String path) throws IOException {
//        File file = new File(classLoader.getResource("tempest.txt").getFile());
        File file = new File(path);
        //counters
        int wordsCount = 0;
        int linesCount = 0;

        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(new BufferedReader(fileReader));

        try (scanner) {
            while (scanner.hasNextLine()) {
                String singleLine = scanner.nextLine();
                if (!singleLine.equalsIgnoreCase("")) {
//                    wordsCount += tmpStr.split("\\s+").length;
                    for (String word : singleLine.split(" ")) {
                        String lowerCaseWord = word.toLowerCase();
                        if (wordMap.containsKey(lowerCaseWord)) {
                            int wordCount = wordMap.get(lowerCaseWord);
                            wordMap.put(lowerCaseWord, ++wordCount);
                        } else {
                            wordMap.put(lowerCaseWord, 1);
                            System.out.println("Word found at line for first time: " + lowerCaseWord);
                        }
                    }
                }
                ++linesCount;
            }

//            Map<String, Integer> sortedMap = wordMap.entrySet().stream().sorted(comparingByValue(reverseOrder())).collect(Collectors.toMap(Entry::getKey, Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

            System.out.println("# of words: " + wordsCount);
            System.out.println("# of lines: " + linesCount);
//            System.out.println("# of occurences of and: " + sortedMap.get("and"));
//            System.out.println("# of occurences of the: " + sortedMap.get("the"));
//            System.out.println("# of occurences of i: " + sortedMap.get("i"));

            wordMap.entrySet().stream().sorted(comparingByValue(reverseOrder())).limit(10).forEach(entry -> {
                System.out.println(entry.getKey() + " (" + entry.getValue() + ")");
            });

        }
    }

}
