package org.example;

import org.example.Amazon.Amazon;
import org.example.Amazon.Cost.*;
import org.example.Amazon.Item;
import org.example.Amazon.ShoppingCart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmazonUnitTest {
    @Test
    @DisplayName("specification-based")
    public void testSmallCart (){
        DeliveryPrice dp = new  DeliveryPrice();
        List<Item> items = List.of(new Item(ItemType.OTHER, "Shoes",1,90)
        ,new Item(ItemType.OTHER, "pencil",1,1),
                new Item(ItemType.OTHER,"pencil",1,4)
        );
        double result = dp.priceToAggregate(items);
        assertEquals(5,result);


    }
    @Test
    @DisplayName("specification-based")
    public void testMediumCart (){
        DeliveryPrice dp = new  DeliveryPrice();
        List<Item> items = List.of(
                new Item(ItemType.OTHER, "Shoes",1,90),
                new Item(ItemType.OTHER, "pencil",1,1),
                new Item(ItemType.OTHER, "shirt",1,10),
                new Item(ItemType.OTHER,"water bottle",1,30)
                //new Item(ItemType.OTHER, "chair",1,40)
        );
        double result = dp.priceToAggregate(items);
        assertEquals(12.5,result);
    }
    @Test
    @DisplayName("specification-based")
    public void testLargeCart (){
        DeliveryPrice dp = new  DeliveryPrice();
        List<Item> items = List.of(
                new Item(ItemType.OTHER, "Shoes",1,90),
                new Item(ItemType.OTHER, "pencil",1,1),
                new Item(ItemType.OTHER, "shirt",1,10),
                new Item(ItemType.OTHER,"water bottle",1,30),
                new Item(ItemType.OTHER, "chair",1,40),
                new Item(ItemType.OTHER, "Shoes",1,100),
                new Item(ItemType.OTHER, "pencil",1,2),
                new Item(ItemType.OTHER, "shirt",1,12),
                new Item(ItemType.OTHER,"water bottle",1,40),
                new Item(ItemType.OTHER, "chair",1,80),
                new Item(ItemType.OTHER, "chair",1,50)
        );
        double result = dp.priceToAggregate(items);
        assertEquals(20,result);
    }
    @Test
    @DisplayName("structural-based")
    public void testEmptyCart (){
        DeliveryPrice dp = new  DeliveryPrice();
        List<Item> emptyCart = new ArrayList<>();
        double result = dp.priceToAggregate(emptyCart);
        assertEquals(0,result);
    }
    @Test
    @DisplayName("specification-based")
    public void testElectronicCart (){
        ExtraCostForElectronics  ec = new ExtraCostForElectronics();
        List<Item> items = List.of(
                new Item(ItemType.ELECTRONIC,"TV",1,500),
                new Item(ItemType.OTHER,"shirt",1,20)
        );
        assertEquals(7.50,ec.priceToAggregate(items));
    }
    @Test
    @DisplayName("structural-based")
    public void testShoppingCartNoElec (){
        ExtraCostForElectronics ec = new ExtraCostForElectronics();
        List<Item> items = List.of(
                new Item(ItemType.OTHER,"shirt",1,20),
                new Item(ItemType.OTHER,"pencil",1,1)
        );
        assertEquals(0,ec.priceToAggregate(items));
    }
    @Test
    @DisplayName("structural-based")
    public void testCalcPriceItems () {
        RegularCost cost = new RegularCost();
        List<Item> items = List.of(
                new Item(ItemType.OTHER, "shoes", 1, 90),
                new Item(ItemType.OTHER, "pencil", 5, 1),
                new Item(ItemType.OTHER, "shirt", 2, 10),
                new Item(ItemType.OTHER, "water bottle", 1, 40)

        );
        assertEquals(155,cost.priceToAggregate(items));
    }
}
