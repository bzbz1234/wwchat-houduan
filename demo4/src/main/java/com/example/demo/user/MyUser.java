package com.example.demo.user;

public class MyUser {
    private Integer id;
    private String company;
    private String post;
    private String introduction;

    public MyUser(String company, String post,String introduction) {
        this.company = company;
        this.post = post;
        this.introduction = introduction;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return post;
    }

    public void setName(String name) {
        this.post = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String password) {
        this.introduction = introduction;
    }

    public static MyUser parseString(String data) {
        String[] parts = data.split("，");
        String company = parts[0];
        String post = parts[1];
        String introduction = parts[2];
        return new MyUser(company,post,introduction);
    }

    public static MyUser parseString1(String data) {
        String[] parts = data.split("，");
        String company = parts[3];
        String post = parts[4];
        String introduction = parts[5];
        return new MyUser(company,post,introduction);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + company + '\'' +
                ", post=" + post + '\'' +
                ", introduction=" +introduction +
                '\n';
    }


}
