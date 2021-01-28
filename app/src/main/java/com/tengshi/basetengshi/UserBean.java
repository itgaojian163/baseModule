package com.tengshi.basetengshi;

/**
 * 作者: Adam
 * 日期: 2019-11-18 - 14:10
 * 邮箱: itgaojian@163.com
 * 描述:
 */
public class UserBean {
    private String name;
    private String pwd;
    private int rule;
    private boolean isDebug;
    private float age;
    private double classes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public double getClasses() {
        return classes;
    }

    public void setClasses(double classes) {
        this.classes = classes;
    }
}
