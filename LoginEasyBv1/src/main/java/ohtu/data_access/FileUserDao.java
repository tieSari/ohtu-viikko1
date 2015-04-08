/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;
import org.springframework.stereotype.Component;

/**
 *
 * @author sariraut
 */
@Component
public class FileUserDao implements UserDao {

    private String fileName;
    private Scanner lukija;
    private List<User> users;

    public FileUserDao(String fileName) {
        this.fileName = fileName;
        users = new ArrayList<User>();
    }

    @Override
    public List<User> listAll() {

        try {
            lukija = new Scanner(new File(fileName));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        while (lukija.hasNextLine()) {
            if (lukija.hasNext()) {
                String name = lukija.next();
                String password = lukija.next();
                User user = new User(name, password);
                users.add(user);
            }
        }

        lukija.close();
        return users;
    }

    @Override
    public User findByName(String name) {
        try {
            lukija = new Scanner(new File(fileName));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        while (lukija.hasNextLine()) {
            if (lukija.hasNext()) {
                String userName = lukija.next();
                String password = lukija.next();
                if (name.equals(userName)) {
                    lukija.close();
                    return new User(name, password);
                }
            } else {
                lukija.close();
                return null;
            }
        }

        lukija.close();
        return null;
    }

    @Override
    public void add(User user
    ) {
        FileWriter kirjoittaja;
        try {
            kirjoittaja = new FileWriter(fileName);
            kirjoittaja.append(user.getUsername() + " " + user.getPassword() + "\n");
            kirjoittaja.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
