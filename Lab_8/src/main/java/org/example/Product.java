package org.example;

//Задание1
class Product {
    String name;
    double price;
    int quantity;
    String country;

    public Product(String name, double price, int quantity, String country) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return name;
    }
}

