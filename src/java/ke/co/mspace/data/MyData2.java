/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.mspace.data;

/**
 *
 * @author mspace
 */
public class MyData2 {
    
    
    private String biometricDate;
    private String biometricTime;
    private int badgeNumber;
    private String pager;
    private String name;

    public MyData2(String biometricDate, String biometricTime, int badgeNumber, String pager, String name) {
        this.biometricDate = biometricDate;
        this.biometricTime = biometricTime;
        this.badgeNumber = badgeNumber;
        this.pager = pager;
        this.name = name;
    }

    public void setBiometricDate(String biometricDate) {
        this.biometricDate = biometricDate;
    }

    public void setBiometricTime(String biometricTime) {
        this.biometricTime = biometricTime;
    }

    public void setBadgeNumber(int badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public void setPager(String pager) {
        this.pager = pager;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiometricDate() {
        return biometricDate;
    }

    public String getBiometricTime() {
        return biometricTime;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public String getPager() {
        return pager;
    }

    public String getName() {
        return name;
    }
    
    
    
    
    
    
    
    
}
