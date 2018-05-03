package com.example.zh.babyplan;

/**
 * Created by ZH on 2018/1/16.
 */

public class Fruit {
    private String name, problem, problemContent, p_name, created, support_num, head;

    //    private int imageId;
    public Fruit(String name, String problem, String problemContent, String p_name, String created, String support_num, String head) {
        this.name = name;
        this.problem = problem;
        this.problemContent = problemContent;
        this.p_name = p_name;
        this.created = created;
        this.support_num = support_num;
        this.head = head;
//        this.imageId=imageId;
    }

    public String getName() {
        return "#" + name + "#       ";
    }

    public String getProblem() {
        return problem;
    }

    public String getProblemContent() {
        if(problemContent.length() > 50){
            return problemContent.substring(1, 49) + "...";
        }else{
            return problemContent;
        }
    }

    public String getCreated() {
        return created;
    }

    public String getP_name() {
        return p_name;
    }

    public String getSupport_num() {
        return support_num;
    }

    public String getHead() {
        return head;
    }

    //    public int getImageId() {return imageId;}
}
