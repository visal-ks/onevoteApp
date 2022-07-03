package com.example.my;

public class Model {
    String Name,Position,Dept,Sem,url;
    int count;
  Model(){}


    public Model(String name, String position, String dept, String sem, String url) {
        Name = name;
        Position = position;
        Dept = dept;
        Sem = sem;
        this.url = url;

    }


    public int getCount(){return count;}
    public String getName() {
        return Name;
    }

    public String getPosition() {
        return Position;
    }

    public String getSem() {
        return Sem;
    }

    public String getDept() {
        return Dept;
    }

    public String getUrl() {
        return url;
    }
}
