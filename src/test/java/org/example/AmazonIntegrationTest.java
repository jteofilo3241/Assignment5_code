package org.example;
import org.example.Amazon.*;
import org.example.Amazon.Cost.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmazonIntegrationTest {
    private static ShoppingCart cart;
    //@BeforeAll

    @BeforeEach
    void amazonSetup() {
        Database database = new Database();
        database.resetDatabase();
        cart = new ShoppingCartAdaptor(database);
        Amazon amazon = new Amazon(cart, List.<PriceRule>of(
                new RegularCost(),
                new ExtraCostForElectronics(),
                new DeliveryPrice()
        ));
    }
    @Test
    @DisplayName("specification-based")
    public void testAmazonPrice() {
        Database database = new Database();
        ShoppingCart cart = new ShoppingCartAdaptor(database);
        Item itemOne = new Item(ItemType.OTHER, "Shoes", 4, 90);
        Item itemTwo = new Item(ItemType.ELECTRONIC, "phone", 2, 1000);
        cart.add(itemOne);
        cart.add(itemTwo);
        Amazon amazon = new Amazon(cart, List.<PriceRule>of(
                new RegularCost(),
                new ExtraCostForElectronics(),
                new DeliveryPrice()
        ));
        assertEquals(2372.5,amazon.calculate());
    }
    @Test
    @DisplayName("structural-based")
    public void testClose(){
        Database database = new Database();
        database.close();
        database.close();
    }
    @Test
    @DisplayName("structural-based")
    public void testNumItems(){
        Database database = new Database();
        database.resetDatabase();
        ShoppingCart cart = new ShoppingCartAdaptor(database);
        Item itemOne = new Item(ItemType.OTHER, "Shoes", 4, 90);
        Item itemTwo = new Item(ItemType.ELECTRONIC, "phone", 2, 1000);
        cart.add(itemOne);
        cart.add(itemTwo);
        cart.numberOfItems();

    }
    @Test
    @DisplayName("structural")
    public void testAddToCart(){
        ShoppingCart mockedCart = Mockito.mock(ShoppingCart.class);
        List<Item> items = new ArrayList<>();
        Mockito.when(mockedCart.getItems()).thenReturn(items);

        Amazon amazon = new Amazon(cart, List.<PriceRule>of(
                new RegularCost(),
                new ExtraCostForElectronics(),
                new DeliveryPrice()
        ));
        Item itemOne = new Item(ItemType.OTHER, "Shoes", 4, 90);
        Item itemTwo = new Item(ItemType.ELECTRONIC, "phone", 2, 1000);
        amazon.addToCart(itemOne);
        amazon.addToCart(itemTwo);
        items.add(itemOne);
        items.add(itemTwo);
        assertEquals(2,items.size());
    }
    }


