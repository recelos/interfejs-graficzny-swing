package jp.jg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * Klasa zawierająca przykładową bazę danych.
 */
public class Database {

    /**
     * Mapa przechowująca nazwy użytkowników i hasła.
     */
    private final Map<String, char[]> data = new HashMap<>();
    /**
     * Inicjalizuje przykładowe dane.
     */
    private void initializeData() {
        data.put("admin", new char[]{'a','d','m','i','n'});
        data.put("user", new char[]{'p','a','s','s','w','o','r','d'});
    }

    public Database(){
        initializeData();
    }

    /**
     * Sprawdza, czy dane podane przy logowaniu zgadzają się w bazie danych.
     * @param entryLogin login podany przez użytkownika.
     * @param entryPassword hasło podane przez użytkownika.
     * @return true jeżeli dane zgadzają się z bazą danych
     */
    public boolean authorize(String entryLogin, char[] entryPassword) {
        if (data.containsKey(entryLogin)) {
            char[] password = data.get(entryLogin);
            if(password.length == entryPassword.length){
                for (int i = 0; i < password.length; i++) {
                    if(password[i] != entryPassword[i])
                        return false;
                }
                return true;
            }
        }
        return false;
    }
}
