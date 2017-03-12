package com.vratsel.payment.tracker.util;
import com.vratsel.payment.tracker.Payment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by Vladimir on 12.03.2017.
 */
public class Helper {
    public static final String BALANCE = "Balance";
    public static final String CURRENT = "Current ";
    public static final String INITIAL = "Initial ";
    public static final String IMPOSSIBLE_TO_PARSE = "Impossible to parse as a payment:";
    public static final String FINISHED = "Program finished.";
    public static final String QUIT = "quit";

    public static Payment parseAsPayment(String input) {
        String pattern = "^([a-z, A-Z]{3})[\\s]+(-?\\d+)$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input.trim());
        try {
            if (m.find()) {
                Payment payment = new Payment(m.group(1).toUpperCase(), Integer.valueOf(m.group(2)));
//            System.out.println(payment);
                return payment;
            } else {
                System.out.println(IMPOSSIBLE_TO_PARSE + input);
                return null;
            }
        }catch(Exception e) {
            System.out.println(IMPOSSIBLE_TO_PARSE + input + "\n" + e.getClass() + e.getMessage());
            return null;
        }
    }
}
