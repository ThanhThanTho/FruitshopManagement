/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FruitManagement;

import java.util.ArrayList;

/**
 *
 * @author Xuan Thanh
 */
public class Order {

    private String cusName;
    private ArrayList<Fruit> fruitList;

    public Order() {
    }

    public Order(String cusName, ArrayList<Fruit> fruitList) {
        this.cusName = cusName;
        this.fruitList = fruitList;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public ArrayList<Fruit> getFruitList() {
        return fruitList;
    }

    public void setFrutList(ArrayList<Fruit> frutList) {
        this.fruitList = frutList;
    }

    @Override
    public String toString() {
        return "Order{" + "cusName=" + cusName + ", frutList=" + fruitList + '}';
    }

}
