package com.vratsel.payment.tracker;

import com.vratsel.payment.tracker.threads.InputThread;
import com.vratsel.payment.tracker.threads.OutputThread;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


import static com.vratsel.payment.tracker.util.Helper.*;
/**
 * Created by Vladimir on 12.03.2017.
 */
public class Main {
    public static void main(String... args) {
        PaymentTracker tracker = new PaymentTracker();

        if (args.length == 1) {
            String fileName = args[0];
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                stream.forEach(string -> tracker.addPayment(parseAsPayment(string)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(INITIAL + tracker);

        new OutputThread(tracker).start();
        Thread it = new InputThread(tracker);
        it.start();
        try {
            it.join();
        }catch(InterruptedException e) {}
        System.out.println(FINISHED);
    }
}
