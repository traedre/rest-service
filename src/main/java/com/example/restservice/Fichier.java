package com.example.restservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Fichier {
    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    private long id;
    private String content;

    public Fichier(long id, String content, String clef) {
        this.id = id;
        try {
            this.content = initContent(content,clef);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String initContent(String content, String clef) throws IOException {

        HashMap<String, String> map = new HashMap<String, String>();

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(content));
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null)
        {
            sb.append(line) ;
            String[] parts = line.split(":", 2);
            if (parts.length >= 2)
            {
                String key = parts[0];
                String value = parts[1];
                map.put(key, value);
            }
        }

        reader.close();

       /* List<String> lignes = Files.readAllLines(Path.of(content), StandardCharsets.ISO_8859_1) ;
        StringBuilder sb = new StringBuilder();
        for (String ligne : lignes) {
            sb.append(ligne);
        }
        return sb.toString();*/
        if (clef!="")
            return map.get(clef);
        else return sb.toString() ;
    }
}
