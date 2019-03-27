/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package comman;

import java.io.Serializable;

/**
 *
 * @author m.j
 */
public class LoginInfo implements Serializable{
    private String user,pass;
 
    public LoginInfo(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }
    
}
