/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.user.form;

import com.openhr.common.OpenHRForm;

/**
 *
 * @author Mekbib
 */
public class LoginForm extends OpenHRForm {

    private String username;
    private String password;
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
