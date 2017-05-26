package com.jaas;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

/**
 * Created by a171535 on 26-05-2017.
 */
public class MyLoginModule implements LoginModule {
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    private CallbackHandler callbackhandler = null;
    private boolean authenticationflag = false;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackhandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] ca = new Callback[2];
        ca[0] = new NameCallback("Username:");
        ca[1] = new PasswordCallback("Password:",false);

        try {
            callbackhandler.handle(ca);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }

        String name = ((NameCallback)ca[0]).getName();
        String password = new String(((PasswordCallback)ca[1]).getPassword());

        if(USER.equals(name) && PASSWORD.equals(password)){
            System.out.println("Authentication successful............");
            authenticationflag = true;
        }
        return authenticationflag;
    }

    @Override
    public boolean commit() throws LoginException {
        return authenticationflag;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
