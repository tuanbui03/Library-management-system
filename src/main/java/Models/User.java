/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author PC
 */
public class User {

    private static User instance;

    private String userName;
    private Set<String> privileges;

    private User(String userName, Set<String> privileges) {
        this.userName = userName;
        this.privileges = privileges;
    }

    public static User getInstace(String userName, Set<String> privileges) {
        if (instance == null) {
            instance = new User(userName, privileges);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public Set<String> getPrivileges() {
        return privileges;
    }

    public void cleanUserSession() {
        userName = "";// or null
        privileges = new HashSet<>();// or null
    }

    @Override
    public String toString() {
        return "UserSession{"
                + "userName='" + userName + '\''
                + ", privileges=" + privileges
                + '}';
    }
}
