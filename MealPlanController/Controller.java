/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MealPlanController;

import MealPlanModel.Model;
import java.util.ArrayList;

public class Controller {

    private ArrayList<Model> users = new ArrayList<>();

    public Controller() {
        // Dummy user
        users.add(new Model("Admin", "admin@gmail.com", "1234"));
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
}
