package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalKeyValuePersister {
    private Map<String, String> keyValueCache;
    String filePath;
    public static final String DELETED = "DELETED";

    public LocalKeyValuePersister() {
        keyValueCache = new HashMap<>();
        filePath = "/home/arpita/Documents/KeyValueStore/KeyValueFile.txt";
        populateLocalCache();
        for (Map.Entry<String, String> record :
                keyValueCache.entrySet()) {
            System.out.println("key: " + record.getKey() + ", value: " + record.getValue());
        }
    }

    private void populateLocalCache() {
        Path path = Paths.get(filePath);
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] keyVal = line.split(":");
                if(DELETED.equals(keyVal[1])) {
                    keyValueCache.remove(keyVal[0]);
                    continue;
                }
                keyValueCache.put(keyVal[0], keyVal[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(String key, String value) {
        keyValueCache.put(key, value);
        appendToLocalFile(key, value);
    }

    private void appendToLocalFile(String key, String value) {
//        String content = key + ":" + value;

//        try {
//            Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.APPEND);
//        }catch (IOException e) {
//            //exception handling left as an exercise for the reader
//        }



        try {
            String content = key + ":" + value;
            File f1 = new File(filePath);
            if (!f1.exists()) {
                f1.createNewFile();
            }

            FileWriter fileWritter = new FileWriter(f1.getName(), true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write(content);
            bw.write("\n");
            bw.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String key) {
        keyValueCache.remove(key);
        appendToLocalFile(key, DELETED);
    }

    public String read(String key) {
        return keyValueCache.get(key);
    }
}
