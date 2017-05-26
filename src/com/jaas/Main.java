package com.jaas;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) {
        System.setProperty("java.security.auth.login.config", "JaasConfig.config");
        LoginContext lc = null;
        try {
             lc = new LoginContext("JaasSecurity", new MyCallbackHandler());
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(0);
        }

        while(true){
            try {
                lc.login();
                System.exit(0);
            } catch (LoginException e) {
                e.printStackTrace();
            }
        }
    }
}
