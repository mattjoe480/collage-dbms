package org.collage.inventory.controllers;

import org.collage.inventory.UIHelper;
import org.collage.inventory.model.InventoryModel;
import org.collage.inventory.model.Role;
import org.collage.inventory.model.UserModel;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private  String url = "jdbc:mysql://localhost:3306/inventory";
    private  String user = "root";
    private  String password = "Admin";
    private Role currentRole = Role.USER;
    public static DataBase instance;
    private Connection con;
    private int qaunity;

    public DataBase() throws SQLException {
        con = DriverManager.getConnection(url, user, password);
    }

    public Role getCurrentRole() {
        return this.currentRole;
    }

    private ResultSet query(String query) throws SQLException {
        Statement stmt = con.createStatement();
        return stmt.executeQuery(query);
    }
    private void execute(String query) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeUpdate(query);
    }

    public void addNewUser(String username, String password, String role) throws SQLException {
        String query = "INSERT INTO users(name, password, role)" +
                " values(\"" + username + "\", \"" + password + "\", \"" + role +"\")";
        execute(query);
    }

    public List<UserModel> getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";
        ResultSet rs = query(query);
        List<UserModel> users = new ArrayList<>();
        while (rs.next()) {
            users.add(new UserModel(rs.getInt("id"), rs.getString("name"),
                    rs.getString("password"), rs.getString("role")));
        }
        return users;
    }
    public void setRole(UserModel user, Role role) throws SQLException {
        String query = "UPDATE users SET role =\"" + role.toString() + "\"WHERE id = \"" + user.getId() + "\"";
        execute(query);
    }
    public void deleteUser(UserModel user) throws SQLException {
        String query = "DELETE FROM users WHERE id = " + user.getId()  ;
        execute(query);
    }

    public Boolean login(String userName, String password) throws SQLException, IOException {
        String query = "SELECT * FROM users WHERE name = '"
                + userName + "' AND password = '" + password + "'";
        ResultSet rs = query(query);
        System.out.println("Login query executed successfully");
        if(!rs.next())
            return false;
        if (userName.equals(rs.getString("name")) && password.equals(rs.getString("password"))){
            String role = rs.getString("role");
            DataBase.instance.currentRole = Role.valueOf(role.toUpperCase());
            if (currentRole == Role.MANAGER)
                UIHelper.setWindow("Inventory.fxml");
            if(currentRole == Role.ADMIN)
                UIHelper.setWindow("user-list.fxml");
            return true;
        }
        return false;
    }
    public void addToInventory(String itemName, int quantity) throws SQLException {
        String query = "INSERT INTO inventory(name, quantity) values(\"" + itemName + "\", \"" + quantity + "\")";
        execute(query);
    }

    public List<InventoryModel> getAllInventory() throws SQLException {
        String query = "SELECT * FROM inventory";
        ResultSet rs = query(query);
        List<InventoryModel> inventoryList = new ArrayList<>();
        while (rs.next()) {
            String itemName = rs.getString("name");
            String quantity = rs.getString("quantity");
            inventoryList.add(new InventoryModel(itemName, quantity));
        }
        return inventoryList;
    }

    public void removeFromInventory(String itemName) throws SQLException {
        String query = "DELETE FROM inventory WHERE name = \"" + itemName + "\"";
        execute(query);
    }

}
