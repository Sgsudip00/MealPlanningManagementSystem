/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MealPlanController;

import MealPlanModel.Model;
import java.util.ArrayList;
import java.io.*;

public class Controller {

    private ArrayList<Model> users = new ArrayList<>();
    private static final String USERS_FILE = "src/MealPlanView/users.txt";

    public Controller() {
        // Add admin user (cannot be registered through signup)
        users.add(new Model("Admin", "admin@eatwise.com", "Admin123"));
        loadUsersFromFile();
    }

    public Model login(String email, String password) {
        for (Model m : users) {
            if (m.getEmail().equals(email) &&
                m.getPassword().equals(password)) {
                return m; 
            }
        }
        return null;
    }
    
    public boolean registerUser(String username, String email, String password) {
        // Check if user already exists
        for (Model user : users) {
            if (user.getEmail().equals(email)) {
                return false; // User already exists
            }
        }
        
        // Create new user
        Model newUser = new Model(username, email, password);
        users.add(newUser);
        
        // Save to file
        saveUserToFile(newUser);
        return true;
    }
    
    private void saveUserToFile(Model user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(user.getUsername() + "," + user.getEmail() + "," + user.getPassword());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving user to file: " + e.getMessage());
        }
    }
    
    private void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    users.add(new Model(parts[0], parts[1], parts[2]));
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, which is fine
        } catch (IOException e) {
            System.err.println("Error loading users from file: " + e.getMessage());
        }
    }
}
