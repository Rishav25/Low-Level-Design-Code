package models;

import java.util.LinkedList;
import java.util.List;

public class Bank {
    String bankName;
    String ifscCode;
    List<User> userList;

    public Bank(String bankName, String ifscCode) {
        this.bankName = bankName;
        this.ifscCode = ifscCode;
        userList = new LinkedList<>();
    }

    public void addUser(User u) {
        userList.add(u);
    }

    public void removeUser(User u) {
        userList.remove(u);
    }
}
