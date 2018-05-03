package com.example.zh.babyplan;

/**
 * Created by zjh on 2018/4/21.
 */

public class CheckListViewItem {
    private String id;
    private String height;
    private String weight;
    private String vaccine;
    private String date;
    private String bid;
    public CheckListViewItem(String id,String height,String weight,String vaccine,String date,String bid){
        this.id=id;
        this.height=height;
        this.weight=weight;
        this.vaccine=vaccine;
        this.date=date;
        this.bid=bid;
    }
    public CheckListViewItem(){}
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getHeight(){
        return height;
    }
    public void setHeight(String height){
        this.height=height;
    }
    public String getWeight(){
        return weight;
    }
    public void setWeight(String weight){
        this.weight=weight;
    }
    public String getVaccine(){
        return vaccine;
    }
    public void setVaccine(String vaccine){
        this.vaccine=vaccine;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }
    public String getBid(){
        return bid;
    }
    public void setBid(String bid){
        this.bid=bid;
    }
}
