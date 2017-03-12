package com.vratsel.payment.tracker.util;

import com.vratsel.payment.tracker.Payment;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.vratsel.payment.tracker.PaymentTrackerTest.*;
/**
 * Created by Vladimir on 12.03.2017.
 */
public class HelperTest {

    @Test
    public void testNegativeScenarios() {
        assertIncorrect("a2c 1234");
        assertIncorrect("a-c 1234");
        assertIncorrect("aac -1a234");
        assertIncorrect("aac1234");
        assertIncorrect("DDD 123-4");
        assertIncorrect("quit");
        assertIncorrect("USD 6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666");
    }

    @Test
    public void testPositiveScenarios() {
        assertPayment(" usd 456", USD, 456);
        assertPayment(" eUr -12", EUR, -12);
        assertPayment("   USD -44 ", USD, -44);
        assertPayment("EUr 1790", EUR, 1790);
    }


    private void assertPayment(String toParse, String currency, int amount) {
        assertEquals(new Payment(currency, amount), Helper.parseAsPayment(toParse));
    }
    private void assertIncorrect(String toParse) {
        assertNull(Helper.parseAsPayment(toParse));
    }
}
