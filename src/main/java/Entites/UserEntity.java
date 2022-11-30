/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entites;

import java.util.HashSet;
import java.util.Set;
import java.util.prefs.Preferences;

/**
 *
 * @author PC
 */
public class UserEntity {

    private Preferences prefs;
//    private static UserEntity instance;
//
//    private String userName;
//    private Set<String> privileges;
//
//    private UserEntity(String userName, Set<String> privileges) {
//        this.userName = userName;
//        this.privileges = privileges;
//    }
//
//    public static UserEntity getInstace(String userName, Set<String> privileges) {
//        if (instance == null) {
//            instance = new UserEntity(userName, privileges);
//        }
//
//        return instance;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public Set<String> getPrivileges() {
//        return privileges;
//    }
//
//    public void cleanUserSession() {
//        userName = "";// or null
//        privileges = new HashSet<>();// or null
//    }
//
//    @Override
//    public String toString() {
//        return "UserSession{"
//                + "userName='" + userName + '\''
//                + ", privileges=" + privileges
//                + '}';
//    }

    public void setPreference() {
        // This will define a node in which the preferences can be stored
        prefs = Preferences.userRoot().node(this.getClass().getName());
        String ID1 = "Test1";
        String ID2 = "Test2";
        String ID3 = "Test3";

        // First we will get the values
        // Define a boolean value
        System.out.println(prefs.getBoolean(ID1, true));
        // Define a string with default "Hello World
        System.out.println(prefs.get(ID2, "Hello World"));
        // Define a integer with default 50
        System.out.println(prefs.getInt(ID3, 50));

        
        // now set the values
        prefs.putBoolean(ID1,
                false);
        prefs.put(ID2,
                "Hello Europa");
        prefs.putInt(ID3,
                45);
        
        System.out.println(prefs.get(ID3, ID3));

        // Delete the preference settings for the first value
        prefs.remove(ID1);

    }

    public static void main(String[] args) {
        UserEntity test = new UserEntity();
        test.setPreference();
    }
}
