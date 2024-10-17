package org.collage.inventory.model;

import java.util.Locale;

public class UserModel {
    private int id;

    public UserModel(int id, String name, String password, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = Role.valueOf(role.toUpperCase());
    }

    private String name;
    private String password;
    private Role  role;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public int getId() {
        return id;
    }
}
