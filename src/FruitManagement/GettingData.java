/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FruitManagement;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Xuan Thanh
 */
public class GettingData {

    Scanner sc = new Scanner(System.in);

    //allow user to input selection
    public int getChoice() {
        int choice;
        System.out.println("(Please choose 1 to create product, 2 to view order, 3 for shopping, 4 to Exit program)");
        //loops to let user enter again if input is out of range
        do {
            choice = getNumber();
            //check if the choice is out of range
            if (choice <= 0 | choice > 4) {
                System.out.println("Only from 1 to 4. Please enter again!");
            }
        } while (choice <= 0 | choice > 4);
        return choice;
    }

    //let user input fruit ID
    public String getFruitID(ArrayList<Fruit> fruitList) {
        String id;
        do {
            System.out.println("Input fruit id: ");
            id = getString();
            //warn user if id already exist
            if (checkDublicateID(fruitList, id) == true) {
                System.out.println("Id already existed. Please try again!");
            }
        } while (checkDublicateID(fruitList, id) == true);
        return id;
    }

    //let user input fruit name
    public String getFruitName() {
        String name;
        System.out.println("Input fruit name: ");
        name = getString();
        return name;
    }

    //let user in put fruit origin
    public String getFruitOrigin() {
        String origin, choiceOrigin = "";
        System.out.println("Input origin: ");
        origin = getString();
        return origin;
    }

    //let user select buying item
    public int getBuyingItem(ArrayList<Fruit> fruitList) {
        System.out.println("Select the number of item you want to buy!");
        int fruitNumber;
        //loop untill user enter valid value
        do {
            fruitNumber = getNumber();
            //check if the number item is out of range
            if (fruitList.size() == 1) {
                System.out.println("You can only choose 1. Please enter again!");
            } else if (fruitNumber < 1 | fruitNumber > fruitList.size()) {
                System.out.println("You can only choose from 1 to " + fruitList.size()
                        + ". Please enter again!");
            }
        } while (fruitNumber > fruitList.size() | fruitNumber < 1);
        System.out.println("You selected: " + fruitList.get(fruitNumber - 1).getFruitName());
        return fruitNumber;
    }

    //let user input quantity of the buying fruit
    public int getBuyingQuantity(ArrayList<Fruit> fruitList, int fruitNumber) {
        System.out.println("Please input quantity: ");
        int quantity = 0;
        //loop until user input valid quantity
        do {
            quantity = getFruitQuantity();
            //check if the user's quantity exceed the quantity have left or not
            if (quantity > fruitList.get(fruitNumber - 1).getQuantity()) {
                System.out.println("We dont have enought quantity you required. Only "
                        + fruitList.get(fruitNumber - 1).getQuantity()
                        + " left. Please enter again!");
            }
        } while (quantity > fruitList.get(fruitNumber - 1).getQuantity());
        return quantity;
    }

    //let user input name
    public String getCustomerName() {
        System.out.println("Input your name: ");
        String name = getString();
        return name;
    }

    //check valid string
    public String getString() {
        //users have to enter again if input is empty
        while (true) {
            String string = sc.nextLine().trim();
            //string must not empty
            if (string.isEmpty()) {
                System.out.println("Input not empty. Please enter again!");
            } else {
                return string;
            }
        }
    }

    //check valid price
    public float getFruitPrice() {
        System.out.println("Input price($): ");
        //loop to let enter again if input wrong format
        while (true) {
            String price = sc.nextLine().trim();
            try {
                float floatPrice = Float.parseFloat(price);
                //check if the prize is smaller than 0 or not
                if (floatPrice <= 0) {
                    throw new InputMismatchException();
                }
                return floatPrice;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter number!");
            } catch (InputMismatchException e) {
                System.out.println("Price must greater than 0. Please enter again!");
            }
        }
    }

    //check valid quantity
    public int getFruitQuantity() {
        System.out.println("Input quantity: ");
        int quantity = getNumber();
        return quantity;
    }

    //check yes/no decision
    public boolean getLoopChoice() {
        //users have to enter again if input is invalid
        while (true) {
            String choice = sc.nextLine().trim();
            //must not empty
            if (choice.length() <= 0) {
                System.out.println("Please enter Y or N!");
                //check if user enter Y or y
            } else if ("Y".equals(choice) | "y".equals(choice)) {
                return true;
                //check if user enter N or n
            } else if ("N".equals(choice) | "n".equals(choice)) {
                return false;
            } else {
                System.out.println("Only Y or N. Please enter again!");
            }
        }
    }

    //allign the string to the left of a given space
    public String getLeftAlignString(int space, String string) {
        String spaceHaveLeft = "";
        StringBuffer tempString = new StringBuffer("");
        //check if space have left fit with the string 
        if (space <= string.length()) {
            //loop to access to string's character
            for (int i = 0; i < space; i++) {
                tempString.append(string.charAt(i));
            }
            return String.valueOf(tempString);
        } else {
            //set up for the right space
            for (int i = 0; i < space - string.length(); i++) {
                spaceHaveLeft += " ";
            }
        }
        return string + spaceHaveLeft;
    }
    
    //center align string in a given space
    public String getCenterAlignString(int space, String string) {
        String leftSpace = "";
        String rightSpace = "";
        StringBuffer tempString = new StringBuffer("");
        //check if the given space smaller than string's capacity
        if (space < string.length()) {
            //loop to access to string's character
            for (int i = 0; i < space; i++) {
                tempString.append(string.charAt(i));
            }
            return String.valueOf(tempString);
            //check if the string length fit with the given space
        } else if (space == string.length()) {
            return string;
        } else {
            int numSpace = space - string.length();
            //set up the length for left and right space base on the space remaining
            if (numSpace % 2 == 0) {
                //set up left and right space for the case remaining space is even
                for (int i = 0; i < numSpace / 2; i++) {
                    leftSpace += " ";
                    rightSpace += " ";
                }
                return leftSpace + string + rightSpace;
            } else {
                //set up left and right space for the case remaining space is odd
                for (int i = 0; i < numSpace / 2; i++) {
                    leftSpace += " ";
                    rightSpace += " ";
                }
                //append the space on two side of the string then we have a center-align string
                return leftSpace + string + rightSpace + " ";
            }
        }
    }
    
    //check if there is a dublicate id in the list
    public boolean checkDublicateID(List<Fruit> list, String id) {
        //check all the condition of the list
        if (list.isEmpty()) {
            return false;
        }
        if (list.size() == 1) {
            //compare with the only element in the list if it has only one element
            if (list.get(0).getFruitID().trim().equals(id.trim())) {
                return true;
            } else {
                return false;
            }
        } else {
            //go through all list's element
            for (int i = 0; i < list.size(); i++) {
                //compare the input id with every id in the list
                if (list.get(i).getFruitID().trim().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    //check valid choice
    public int getNumber() {
        //users have to enter again if input is invalid
        while (true) {
            String stringChoice = sc.nextLine().trim();
            try {
                int choice = Integer.parseInt(stringChoice);
                //check the condition of choice after input success
                if (choice <= 0) {
                    throw new InputMismatchException();
                }
                return choice;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a number!");
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter again!");
            }
        }
    }
}
