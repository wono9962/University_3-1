package org.techtown.doitmission_14;

public class Product {
    String Name;
    String Price;
    String Information;
    int Pic;

    public Product(String name, String price, String information, int pic) {
        Name = name;
        Price = price;
        Information = information;
        Pic = pic;
    }

    public int getPic(){
        return Pic;
    }
    public void setPic(int pic){
        Pic = pic;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }
}