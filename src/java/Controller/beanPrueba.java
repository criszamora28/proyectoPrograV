/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Correo;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Ernesto PC
 */
@Named(value = "beanPrueba")
@Dependent
public class beanPrueba {

    /**
     * Creates a new instance of beanPrueba
     */
    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
    private String country;
    private String city;
    private Map<String, String> countries;
    private Map<String, String> cities;

//    public void init() {
//        countries  = new HashMap<String, String>();
//        countries.put("USA", "USA");
//        countries.put("Germany", "Germany");
//        countries.put("Brazil", "Brazil");
//         
//        Map<String,String> map = new HashMap<String, String>();
//        map.put("New York", "New York");
//        map.put("San Francisco", "San Francisco");
//        map.put("Denver", "Denver");
//        data.put("USA", map);
//         
//        map = new HashMap<String, String>();
//        map.put("Berlin", "Berlin");
//        map.put("Munich", "Munich");
//        map.put("Frankfurt", "Frankfurt");
//        data.put("Germany", map);
//         
//        map = new HashMap<String, String>();
//        map.put("Sao Paulo", "Sao Paulo");
//        map.put("Rio de Janerio", "Rio de Janerio");
//        map.put("Salvador", "Salvador");
//        data.put("Brazil", map);
//    }
    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Map<String, String> getCountries() {
        return countries;
    }

    public Map<String, String> getCities() {
        return cities;
    }

    public void onCountryChange() {
        if (country != null && !country.equals("")) {
            cities = data.get(country);
        } else {
            cities = new HashMap<String, String>();
        }
    }

    public void displayLocation() {
        FacesMessage msg = null;
        if (city != null && country != null) {
            msg = new FacesMessage("Selected", city + " of " + country);
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected.");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public beanPrueba() {
    }
// mensaje
    private String message;
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
     
    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
         String sd="holis";
        context.addMessage(null, new FacesMessage("Successful",  "Your message: " + message) );
        context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
        context.addMessage(null, new FacesMessage("Hola", "micaFunciona" +sd));
    }
   
    
    //SEGUNDO MENSAJE 
     private String username;
    private String email;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
     
    public void save() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "User Saved"));
        username = null;
        email = null;
    }
    
    ///Generar random
    public String generateRandom() {
        SecureRandom random = new SecureRandom();
        String text = new BigInteger(130, random).toString(32);
        return text;
    }
    ///mensajes 2.0
//    private MenuModel model;
//     public void init() {
//        model = new DefaultMenuModel();
// 
//        //First submenu
//        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Dynamic Submenu");
// 
//        DefaultMenuItem item = new DefaultMenuItem("External");
//        item.setUrl("http://www.primefaces.org");
//        item.setIcon("pi pi-home");
//        firstSubmenu.addElement(item);
// 
//        model.addElement(firstSubmenu);
// 
//        //Second submenu
//        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");
// 
//        item = new DefaultMenuItem("Save");
//        item.setIcon("pi pi-save");
//        item.setCommand("#{buttonView.save}");
//        item.setUpdate("messages");
//        secondSubmenu.addElement(item);
// 
//        item = new DefaultMenuItem("Delete");
//        item.setIcon("pi pi-times");
//        item.setCommand("#{buttonView.delete}");
//        item.setAjax(false);
//        secondSubmenu.addElement(item);
// 
//        model.addElement(secondSubmenu);
//    }
// 
//    public MenuModel getModel() {
//        return model;
//    }
// 
//    public void save() {
//        addMessage("Data saved");
//    }
// 
//    public void update() {
//        addMessage("Data updated");
//    }
// 
//    public void delete() {
//        addMessage("Data deleted");
//    }
// 
//    public void buttonAction() {
//        addMessage("Welcome to Primefaces!!");
//    }
// 
//    public void addMessage(String summary) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
//        FacesContext.getCurrentInstance().addMessage(null, message);
//    }
}
