/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entites;

import Models.Role;
import java.sql.*;
import db.*;
import java.util.ArrayList;
/**
 *
 * @author PC
 */
public class RoleEnity {
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    public ArrayList<Role> getAll() {
//      Call array list with type is Role
        ArrayList<Role> list = new ArrayList<>();
//      Query SELECT in SQL
        String query = "SELECT * FROM roles";

        try {
//          Connect to database and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();

//          Call value in databse and set for list Role
            while (rs.next()) {
                Role role = new Role(rs.getInt("id"),
                        rs.getString("name"));

                list.add(role);
            }

            return list;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//          Close databse at end
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return null;
    }

    public Role getOne(int id) {
//      Query select in database with hidden value "?"
        String query = "SELECT * FROM roles where id = ?";

        try {
//            Connect to database, set hidden value and execute query
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            
//          if id exists in database: set value in Role of Models else print in console: "This role doesn't exists!"
            if (rs.next()) {
                Role role = new Role(rs.getInt("id"),
                        rs.getString("name"));

                return role;
            } else {
                System.out.println("This role doesn't exists!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//          Close database at end
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return null;
    }

    public boolean insert(Role obj) {
        boolean flag = false;
//      Query insert in database with hidden value
        String query = "INSERT INTO roles (name) VALUES (?)";

        try {
//          Connect to database and set hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, obj.getName());

//          Execute Query, if insert successfull set flag equal true
            if (preparedStatement.executeUpdate() > 0) {
                flag = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//          Close database at end
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return flag;
    }

    public boolean update(Role obj) {
        boolean flag = false;
        RoleEnity re = new RoleEnity();
//        Query Update in Database with hidden value "?"
        String query = "UPDATE roles SET name = ? WHERE id = ?";

        try {
//          Connect to database
            connection = JDBCConnect.getJDBCConnection();

            if (re.getOne(obj.getId()) == null) {
                System.out.println("This role doesn't exists!");
            } else {
//              Set hidden value
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setInt(2, obj.getId());

//              Execute Query, if update successfull set flag equal true
                if (preparedStatement.executeUpdate() > 0) {
                    flag = true;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//          Close database at end
            JDBCConnect.closeResultSet(rs);
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return flag;
    }

    public boolean delete(int id) {
        boolean flag = false;

//      Query Delete in database with hidden value
        String query = "DELETE FROM roles WHERE id = ?";

        try {
//          Connect to Database, set value for hidden value
            connection = JDBCConnect.getJDBCConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

//              Execute Query, if delete successfull set flag equal true
            if (preparedStatement.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
//          Close database at end
            JDBCConnect.closePreparedStatement(preparedStatement);
            JDBCConnect.closeConnection(connection);
        }

        return flag;
    }

}
