package com.mygdx.game.Screens;

import com.mygdx.game.FirebaseController.FirebaseAuth;
import com.mygdx.game.MyWidgets.MyScreen;
import com.mygdx.game.JadventureMain;
import de.tomgrill.gdxfirebase.core.GDXFirebase;
import de.tomgrill.gdxfirebase.core.auth.AuthStateListener;

public class LoginScreen extends MyScreen {

    //https://github.com/TomGrill/gdx-firebase

    FirebaseAuth firebaseAuth;
    public LoginScreen(JadventureMain game) {
        super(game);
        firebaseAuth = new FirebaseAuth();
    }

    public void iniciarSesion(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email,password);
        GDXFirebase.FirebaseAuth().addAuthStateListener(new AuthStateListener() {
            @Override
            public void onAuthStateChanged(de.tomgrill.gdxfirebase.core.auth.FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null) {
                    new MenuScreen(game);
                } else {
                    System.out.println("error al iniciar sesion");
                }
            }
        });
    }
}
