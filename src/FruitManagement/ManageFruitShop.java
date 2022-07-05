/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FruitManagement;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Xuan Thanh
 */
public class ManageFruitShop {

    Scanner sc = new Scanner(System.in);
    GettingData getData = new GettingData();

    //display function menu
    public void displayMenu() {
        System.out.println("FRUIT SHOP SYSTEM\n"
                + "1. Create Fruit\n"
                + "2. View orders\n"
                + "3. Shopping (for buyer)\n"
                + "4. Exit");
    }

    //allow users to create new fruit
    public void createFruit(ArrayList<Fruit> fruitList) {
        boolean choice = false;
        String id, name, origin = "";
        float price;
        int quantity;
        //users select item(1) to create fruit
        do {
            //let user input fruit id
            id = getData.getFruitID(fruitList);
            //let user input fruit name
            name = getData.getFruitName();
            //let user input fruit price
            price = getData.getFruitPrice();
            //let user input fruit quantity
            quantity = getData.getFruitQuantity();
            //let user input fruit origin
            origin = getData.getFruitOrigin();
            //add fruit into the list
            Fruit a = new Fruit(id.trim(), name.trim(), price, quantity, origin.trim());
            fruitList.add(a);
            //after each fruit is created, the program show message
            choice = getContinueDecision("creating");
        } while (choice==true);
        //display all fruit that are created
        displayAllCreatedFruit(fruitList);
    }

    //show all the order users have made
    public void viewOrders(Hashtable<String, List<Order>> orderList) {
        //check if order list is empty or not
        if (orderList.isEmpty()) {
            System.out.println("There aren't any order in the list for now");
        } else {
            float total = 0;
            Set<String> keySet = orderList.keySet();
            //loop to access all order data
            for (String key : keySet) {
                //loop to access all order in the order list
                for (int i = 0; i < orderList.get(key).size(); i++) {
                    System.out.println("Customer: " + key);
                    System.out.println("Product       |  Quantity  |    Price   |   Amount ");
                    //loop to access every fruit user have bought
                    for (int j = 0; j < orderList.get(key).get(i).getFruitList().size(); j++) {
                        System.out.println(getData.getLeftAlignString(14, j + 1 + "." + orderList.get(key).get(i).getFruitList().get(j).getFruitName()) + " "
                                + getData.getCenterAlignString(12, String.valueOf(orderList.get(key).get(i).getFruitList().get(j).getQuantity())) + " "
                                + getData.getCenterAlignString(13, String.valueOf(orderList.get(key).get(i).getFruitList().get(j).getPrice() + "$")) + " "
                                + getData.getCenterAlignString(12, String.valueOf(orderList.get(key).get(i).getFruitList().get(j).getPrice()
                                        * orderList.get(key).get(i).getFruitList().get(j).getQuantity() + "$")));
                        total += orderList.get(key).get(i).getFruitList().get(j).getPrice()
                                * orderList.get(key).get(i).getFruitList().get(j).getQuantity();
                    }
                    System.out.println("Total: " + total + "$");
                    System.out.println(" ");
                    total = 0;
                }
            }
        }
    }

    //shopping(for buyer)
    public void shopping(ArrayList<Fruit> fruitList, Hashtable<String, List<Order>> orderList) {
        //check if order list is empty or not
        int fruitNumber, quantity;
        ArrayList<Fruit> buyList = new ArrayList<>();
        if (fruitList.isEmpty()) {
            System.out.println("There aren't any fruit in the shop. Please come back later.");
        } else {
            boolean choice = false;
            //let user buying continueously
            do {
                //Customer selects item 3, the program displays all fruits
                displayAvailableFruit(fruitList);
                //to order, users select item
                fruitNumber = getData.getBuyingItem(fruitList);
                //users input quantity of fruit
                quantity = getData.getBuyingQuantity(fruitList, fruitNumber);
                //add the fruit user choose to the order
                addFruitToOrder(fruitList, buyList, fruitNumber, quantity);
                //ask if users want to continue creating
                choice = getContinueDecision("buying");
                //If customer selects N, the program returns to List of Fruit to continue ordering
            } while (choice==false);
            //If select Y, the program displays order detail
            showOrderedFruits(buyList);
            //users input name to finish ordering
            String name = getData.getCustomerName();
            //finish ordering
            executeOrdering(buyList, name, orderList);
        }
    }

    //function to display all fruit that are created
    public void displayAllCreatedFruit(ArrayList<Fruit> fruitList) {
        //check if the fruit list is empty or not
        if (fruitList.isEmpty()) {
            System.out.println("Fruits available: None");
            //if only one fruit in the list then print out without separate comma
        } else if (fruitList.size() == 1) {
            System.out.println("Fruits available: " + fruitList.get(0).getFruitName());
        } else {
            System.out.print("Fruits available: ");
            //loop through all the list's element
            for (int i = 0; i < fruitList.size() - 1; i++) {
                System.out.print(fruitList.get(i).getFruitName() + ", ");
            }
            System.out.println(fruitList.get(fruitList.size() - 1).getFruitName() + ".");
        }
    }

    //display all the fruit in the shop
    public void displayAvailableFruit(ArrayList<Fruit> fruitList) {
        //check if fruit list is empty or not
        if (fruitList.isEmpty()) {
            System.out.println("There aren't any fruit in the shop. Please come back later.");
        } else {
            System.out.println("List of Fruit:\n"
                    + "| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ |");
            //loop to access all fruit and it's infor in the list
            for (int i = 0; i < fruitList.size(); i++) {
                System.out.println(" " + getData.getCenterAlignString(12, String.valueOf(i + 1)) + " "
                        + getData.getCenterAlignString(18, fruitList.get(i).getFruitName()) + " "
                        + getData.getCenterAlignString(14, fruitList.get(i).getOrigin()) + " "
                        + getData.getCenterAlignString(14, String.valueOf(fruitList.get(i).getPrice() + "$")));
            }
        }
    }

    //handle the order user have made
    public void addFruitToOrder(ArrayList<Fruit> fruitList, ArrayList<Fruit> buyList, int fruitNumber, int quantity) {
        //check if the buy list is empty or not
        if (buyList.isEmpty()) {
            Fruit buyFruit = new Fruit(fruitList.get(fruitNumber - 1).getFruitID(), fruitList.get(fruitNumber - 1).getFruitName(),
                    fruitList.get(fruitNumber - 1).getPrice(), quantity, fruitList.get(fruitNumber - 1).getOrigin());
            buyList.add(buyFruit);
            //loop to access all the fruit in fruit list
            for (int i = 0; i < fruitList.size(); i++) {
                //check if there is any fruit id equal with the id of the fruit user buy
                if (fruitList.get(i).getFruitID().equals(buyFruit.getFruitID())) {
                    fruitList.get(i).setQuantity(fruitList.get(i).getQuantity() - buyFruit.getQuantity());
                    //check if the quantity remain in the shop down to zero or not
                    if (fruitList.get(i).getQuantity() == 0) {
                        fruitList.remove(fruitList.get(i));
                    }
                }
            }
        } else {
            boolean existed = false;
            Fruit buyFruit = new Fruit(fruitList.get(fruitNumber - 1).getFruitID(), fruitList.get(fruitNumber - 1).getFruitName(),
                    fruitList.get(fruitNumber - 1).getPrice(), quantity, fruitList.get(fruitNumber - 1).getOrigin());
            //loop to access all the fruit that were bought
            for (int i = 0; i < buyList.size(); i++) {
                //check if the fruit user choose existed in the buy list or not
                if (buyFruit.getFruitID().equals(buyList.get(i).getFruitID())) {
                    existed = true;
                    buyList.get(i).setQuantity(buyList.get(i).getQuantity() + buyFruit.getQuantity());
                }
            }
            //check if the fruit user choose existed in the buy list or not
            if (!existed) {
                buyList.add(buyFruit);
            }
            //loop to access every fruit in the fruit list
            for (int i = 0; i < fruitList.size(); i++) {
                //check if there is any fruit id equal with the id of the fruit user buy
                if (fruitList.get(i).getFruitID().equals(buyFruit.getFruitID())) {
                    fruitList.get(i).setQuantity(fruitList.get(i).getQuantity() - buyFruit.getQuantity());
                    //check if the quantity remain in the shop down to zero or not
                    if (fruitList.get(i).getQuantity() == 0) {
                        fruitList.remove(fruitList.get(i));
                    }
                }
            }
        }
    }

    //show the detail of the order
    public void showOrderedFruits(ArrayList<Fruit> buyList) {
        float total = 0;
        System.out.println("  Product   |  Quantity  |   Price    |   Amount  ");
        //loop to access all the fruit in the buy list
        for (int i = 0; i < buyList.size(); i++) {
            System.out.println(getData.getCenterAlignString(12, buyList.get(i).getFruitName()) + " "
                    + getData.getCenterAlignString(12, String.valueOf(buyList.get(i).getQuantity())) + " "
                    + getData.getCenterAlignString(11, String.valueOf(buyList.get(i).getPrice() + "$")) + " "
                    + getData.getCenterAlignString(14, String.valueOf(buyList.get(i).getPrice() * buyList.get(i).getQuantity() + "$")));
            total += buyList.get(i).getPrice() * buyList.get(i).getQuantity();
        }
        System.out.println("Total: " + total + "$");
    }

    //finish ordering
    public void executeOrdering(ArrayList<Fruit> buyList, String name, Hashtable<String, List<Order>> orderList) {
        ArrayList<Fruit> newBuyList = new ArrayList<>();
        newBuyList = (ArrayList<Fruit>) buyList.clone();
        buyList.clear();
        Order order = new Order(name, newBuyList);
        //check if order list contain the customer's name or not
        if (!orderList.containsKey(order.getCusName())) {
            List<Order> list = new ArrayList<>();
            list.add(order);
            orderList.put(order.getCusName(), list);
        } else if (orderList.containsKey(order.getCusName())) {
            orderList.get(order.getCusName()).add(order);
        }
    }

    //ask if user want to continue creating fruit
    public boolean getContinueDecision(String function) {
        boolean choice = false;
        if (function.equals("creating")) {
            System.out.println("Do you want to continue (Y/N)?");
            choice = getData.getLoopChoice();
        } else if (function.equals("buying")) {
            System.out.println("Do you want to order now?(Y/N)");
            choice = getData.getLoopChoice();
        }
        return choice;
    }
}
