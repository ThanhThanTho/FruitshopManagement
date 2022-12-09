/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package FruitManagement;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author NHAT ANH
 */
public class GettingDataTest {
    
    GettingData getData = new GettingData();

    public GettingDataTest() {
    }

    @Test
    public void shouldTakeUserInput() {
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals("3", getData.getInput());
    }

    @Test
    public void shouldReturnSum() {
        String out = "3\n4\n";
        InputStream in = new ByteArrayInputStream(out.getBytes());
        System.setIn(in);
        assertEquals(7, getData.start());
    }

    @Test
    public void testGetChoice() {
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int out = getData.getChoice();
        assertEquals(3, out);
    }

    @Test
    public void testGetFruitID() {
    }

    @Test
    public void testGetFruitName() {
    }

    @Test
    public void testGetFruitOrigin() {
    }

    @Test
    public void testGetBuyingItem() {
    }

    @Test
    public void testGetBuyingQuantity() {
    }

    @Test
    public void testGetCustomerName() {
    }

    @Test
    public void testGetString() {
    }

    @Test
    public void testGetFruitPrice() {
    }

    @Test
    public void testGetFruitQuantity() {
    }

    @Test
    public void testGetLoopChoice() {
    }

    @Test
    public void testGetLeftAlignString() {
    }

    @Test
    public void testGetCenterAlignString() {
    }

    @Test
    public void testCheckDublicateID() {
    }

    @Test
    public void testGetNumber() {
        
    }

}
