/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FruitManagement;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author Xuan Thanh
 */
public class Main {

    public static void main(String[] args) {
        ManageFruitShop function = new ManageFruitShop();
        GettingData getData = new GettingData();
        ArrayList<Fruit> fruitList = new ArrayList<>();
        Hashtable<String, List<Order>> orderList = new Hashtable<>();
        while (true) {
            //step 1: display menu
            function.displayMenu();
            //step 2: let users to choose function
            int choice = getData.getChoice(); 
            //step 3: perform options base on user's choice
            switch (choice) {
                case 1: {
                    //create product(Fruit) for fruit shop owner
                    function.createFruit(fruitList);
                    break; 
                }
                case 2: {
                    //To view orders list, who buy and how many product
                    function.viewOrders(orderList);
                    break;
                }
                case 3: {
                    //shopping fucntion for users
                    function.shopping(fruitList, orderList);
                    break;
                }
                case 4:
                    //exit the program
                    System.exit(0);
            }
        }
    }
}
