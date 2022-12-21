package models;

import java.util.ArrayList;

public class User {

    private String userName;
    private ArrayList<Permission> passList;

    public User(String userName) {
        this.userName = userName;
        passList = new ArrayList<>();
    }

    public ArrayList<Permission> getPassList() {
        return passList;
    }

    public void setPassList(ArrayList<Permission> passList) {
        this.passList = passList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addPermission(String nodeName, OptionsCoferences option){ 
        passList.add(new Permission(nodeName, option));
    }
    
}
