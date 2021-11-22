package com.example.cashregisterapp;

import java.util.ArrayList;

public class StoreManager {

    ArrayList<Product> listOfItems = new ArrayList<>();


    Double total = 0.0;
    Product pants = new Product("pants", 30, 20.45);
    Product shoes = new Product("shoes", 20, 10.44);
    Product hats = new Product("hats", 25, 5.9);

    public StoreManager() {
        listOfItems.add(pants);
        listOfItems.add(shoes);
        listOfItems.add(hats);
    }

    boolean checkQuantity(Product inventory, int userQuantity) {
        if (userQuantity > inventory.getQuantity()) {
            return false;
        } else {
            return true;
        }
    }


    }



