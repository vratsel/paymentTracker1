package com.vratsel.payment.tracker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vladimir on 12.03.2017.
 */
public class PaymentTrackerTest {
    private PaymentTracker tracker;
    public static final String USD = "USD";
    public static final String EUR = "EUR";

    @Before
    public void setUp() {
        tracker = new PaymentTracker();
    }

    @Test
    public void shouldReturnPaymentAmount() {
        addPayment(USD, 100);
        assertCurrenciesCount(1);
        assertBalance(USD, 100);
    }

    @Test
    public void shouldReturnPaymentsSummary() {
        addPayment(USD, 100);
        addPayment(USD, 15);
        addPayment(USD, -27);
        assertCurrenciesCount(1);
        assertBalance(USD, 88);
    }

    @Test
    public void shouldReturnSummaryForTwoCurrencies() {
        addPayment(USD, 100);
        addPayment(EUR, 15);
        addPayment(EUR, -15);
        addPayment(USD, -27);
        assertCurrenciesCount(2);
        assertBalance(USD, 73);
        assertBalance(EUR, 0);
        assertBalanceStringContains(USD, "73");
        assertBalanceStringDoesNotContain(EUR, "0");
    }

    @Test
    public void shouldBehaveThreadSafe() {
        Thread t1 = createAddingThread();
        Thread t2 = createAddingThread();
        Thread t3 = createSubstractingThread();
        Thread t4 = createSubstractingThread();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }catch (InterruptedException e) {
            fail();
        }
        assertCurrenciesCount(1);
        assertBalance(USD, 0);
    }

    private Thread createAddingThread() {
        return createThread(true);
    }

    private Thread createSubstractingThread() {
        return createThread(false);
    }

    private Thread createThread(boolean addingAmounts) {
        return new Thread(
                ()->{
                    for (int i=0; i<1000000; i++) {
                        tracker.addPayment(new Payment(USD, addingAmounts? i : -i));
                    }
                }
        );
    }

    private void assertBalanceStringDoesNotContain(String... strings) {
        String result = tracker.toString();
        for (String s: strings) {
            assertFalse(result.contains(s));
        }
    }

    private void assertBalanceStringContains(String... strings) {
        String result = tracker.toString();
        for (String s: strings) {
            assertTrue(result.contains(s));
        }
    }

    private void assertCurrenciesCount(int expected) {
        assertEquals(expected, tracker.getBalance().size());
    }

    private void assertBalance(String currency, int expectedAmount) {
        assertEquals(expectedAmount, (int)tracker.getBalance(currency));
    }

    private void addPayment(String currency, int amount) {
        tracker.addPayment(currency, amount);
    }



}
