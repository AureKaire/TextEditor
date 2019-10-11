package com.texteditor.lib.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 200)
    private String inputText;

    private String changedText;

    public Category(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getChangedText() {
        return changeText(this.inputText);
    }

    public void setChangedText(String name) {
        this.changedText = changeText(name);
    }

    private String changeText(String name) {
        String[] arr= name.trim().split("\\s+");
        List<String> words = new ArrayList<>(Arrays.asList(arr));

        Map<String, List<String>> map = new HashMap();
        for (String word : words) {
            String lastChar = String.valueOf(word.charAt(word.length()-1));
            if (map.get(lastChar) == null) {
                map.put(lastChar, new ArrayList<>(Arrays.asList(word)));
            } else {
                map.get(lastChar).add(word);
            }
        }

        StringBuilder mapAsString = new StringBuilder(" ");
        for (String key : map.keySet()) {
            mapAsString.append(key + " "+map.get(key).size() + map.get(key) + " " + "\n");
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append(" ");
        String result=  mapAsString.toString();

        return result;
    }

}
