package com.vratsel.payment.tracker.threads;

import com.vratsel.payment.tracker.PaymentTracker;

import static com.vratsel.payment.tracker.util.Helper.CURRENT;
/**
 * Created by Vladimir on 12.03.2017.
 */
public class OutputThread extends Thread {
    PaymentTracker tracker;
    public OutputThread(PaymentTracker tracker) {
        this.tracker = tracker;
        setDaemon(true);
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(60000);
                System.out.println(CURRENT + tracker);
            }catch (InterruptedException e) {}
        }
    }
}
