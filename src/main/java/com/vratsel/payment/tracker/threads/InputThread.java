package com.vratsel.payment.tracker.threads;

import com.vratsel.payment.tracker.PaymentTracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.vratsel.payment.tracker.util.Helper.*;
/**
 * Created by Vladimir on 12.03.2017.
 */
public class InputThread extends Thread {
    private PaymentTracker tracker;
    public InputThread(PaymentTracker tracker) {
        this.tracker = tracker;
    }

    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String input = br.readLine();
            while (!QUIT.equals(input.toLowerCase())) {
                tracker.addPayment(parseAsPayment(input));
                input = br.readLine();
            }
        }catch (IOException e) {
            System.out.println("Error. Program will be terminated");
            e.printStackTrace();
        }
    }
}
