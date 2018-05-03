package com.example.zh.babyplan;

/**
 * Created by 84481 on 2018/3/6.
 * 查看宝宝页面的流式布局的属性
 */

public class BB {

    private String b_name, birthday, sex;

    public BB(String b_name, String birthday, String sex){
        this.b_name = b_name;
        this.birthday = birthday;
        this.sex = sex;
    }

    public String getB_name() {
        return b_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getSex() {
        return sex;
    }
}
