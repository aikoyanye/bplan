package com.example.zh.babyplan;

/**
 * Created by 84481 on 2018/3/6.
 */

public class Comment {
    private String name, content, created;

    public Comment(String name, String content, String created){
        this.name = name;
        this.content = content;
        this.created = created;
    }

    public String getCreated() {
        return created;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}
