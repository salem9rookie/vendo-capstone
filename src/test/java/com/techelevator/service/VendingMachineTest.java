package com.techelevator.service;

import static org.junit.Assert.*;

import com.techelevator.model.Product;
import com.techelevator.view.Menu;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @Before
    public void setup() {
        Menu menu = new Menu(System.in, System.out);
        vendingMachine = new VendingMachine(menu);
    }

    @Test
    public void loadProductsTest() {
        vendingMachine.loadProducts("vendingmachine.csv");
        assertNotNull(vendingMachine.getProducts());
        assertFalse(vendingMachine.getProducts().isEmpty());
    }
    @Test
    public void findProductsBySlotNumberTest(){
        Product foundProduct = vendingMachine.findProductBySlotNumber("A1");
        assertNotNull(foundProduct);
        assertEquals("Potato Crisps", foundProduct.getName());
        assertEquals(3.05, foundProduct.getPrice(), 0.00);
        assertEquals("Chip", foundProduct.getType());

    }
}