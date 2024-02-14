/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.mspace.data;

import java.io.Serializable;

public class MyData implements Serializable {

    private String firstName;
    private String lastName;
    private String gender;
    private String work;
    private String home;
    private String marital;
    private int age;

    public MyData(String firstName, String lastName, String gender, String work, String home, String marital, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.work = work;
        this.home = home;
        this.marital = marital;
        this.age = age;
    }

    // Getters and setters for firstName, lastName, age

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }
    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
