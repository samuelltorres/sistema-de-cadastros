package entities;

import java.io.Serializable;

public class Person implements Serializable {

    private String name;
    private String email;
    private Integer age;
    private Double height;

    public Person() {
    }

    public Person(String name, String email, Integer age, Double height) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getNameFormated(){
        return name.replaceAll(" ", "").toUpperCase();
    }

    @Override
    public String toString() {
        return
               name + "," + email + "," + age + "," + height;
    }
}
