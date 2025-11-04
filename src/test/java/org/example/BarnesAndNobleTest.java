package org.example;
import org.example.Barnes.BarnesAndNoble;
import org.example.Barnes.BookDatabase;
import org.example.Barnes.BuyBookProcess;
import org.example.Barnes.PurchaseSummary;
import org.example.Barnes.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

//import static jdk.internal.classfile.impl.verifier.VerifierImpl.verify;
import static org.junit.jupiter.api.Assertions.*;
import org.example.Barnes.BookDatabase;
import org.example.Barnes.BuyBookProcess;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BarnesAndNobleTest {
    @Test
    @DisplayName("specification-based")
    public void testGetPriceWorking() {
        BookDatabase bookDatabase = mock(BookDatabase.class);
        BuyBookProcess buyBookProcess = mock(BuyBookProcess.class);
        BarnesAndNoble barnesAndNoble = new BarnesAndNoble(bookDatabase,buyBookProcess);

        Book book = new Book("12345",10,5);
        when(bookDatabase.findByISBN("12345")).thenReturn(book);
        Map<String, Integer> map = Map.of("12345", 3);
        PurchaseSummary summary = barnesAndNoble.getPriceForCart(map);

        assertEquals(30,summary.getTotalPrice());

    }
    @Test
    @DisplayName("specification-based")
    public void testNull(){
        BookDatabase bookDatabase = mock(BookDatabase.class);
        BuyBookProcess buyBookProcess = mock(BuyBookProcess.class);
        BarnesAndNoble barnesAndNoble = new BarnesAndNoble(bookDatabase,buyBookProcess);
        PurchaseSummary summary = barnesAndNoble.getPriceForCart(null);
        assertNull(summary);
    }
    @Test
    @DisplayName("structural-based")
    public void testBookUnavailable(){
        BookDatabase bookDatabase = mock(BookDatabase.class);
        BuyBookProcess buyBookProcess = mock(BuyBookProcess.class);
        BarnesAndNoble barnesAndNoble = new BarnesAndNoble(bookDatabase,buyBookProcess);
        Book book = new Book("12345",10,5);
        when(bookDatabase.findByISBN("12345")).thenReturn(book);
        Map<String, Integer> map = Map.of("12345", 10  );
        PurchaseSummary summary = barnesAndNoble.getPriceForCart(map);
        Map<Book,Integer> unavailableB = summary.getUnavailable();
        assertTrue(unavailableB.containsKey(book));
        assertEquals(5, unavailableB.get(book));
        // verify(buyBookProcess,times(1)).buyBook(book,5);



    }
}
