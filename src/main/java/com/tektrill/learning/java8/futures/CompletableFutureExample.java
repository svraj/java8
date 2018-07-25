package com.tektrill.learning.java8.futures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureExample {

    public static void main (String[] args)  throws Exception{
       // runAsyncExample();
        //supplyAsyncExample();
        runComposeExample();

    }

    private static void supplyAsyncExample() throws InterruptedException{
        /*
        CompletableFuture.runAsync() is useful for tasks that don’t return anything.
        But what if you want to return some result from your background task?
           Well, CompletableFuture.supplyAsync() is your companion.
           It takes a Supplier<T> and returns CompletableFuture<T> where T is the type of the value obtained by calling the given supplier -
         */

        CompletableFuture<String> nameFuture = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Tim";
        });

        try {
            //THIS IS BLOCKING!!!
            String nameFromFuture = nameFuture.get();

            System.out.println("Name From Future ="+nameFromFuture);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private static void runAsyncExample() throws InterruptedException{
        /*If you want to run some background task asynchronously and don’t want to return anything from the task,
            then you can use CompletableFuture.runAsync() method.
            It takes a Runnable object and returns CompletableFuture<Void>.*/
        // Using Lambda Expression

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // Simulate a long-running Job
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("I'll run in a separate thread than the main thread.");
        });

        TimeUnit.SECONDS.sleep(5);
    }

    private static void runComposeExample() {
        long start = System.currentTimeMillis();
        CompletableFuture helloFuture = CompletableFuture.supplyAsync(()-> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        });

        CompletableFuture nameFuture = CompletableFuture.supplyAsync(()-> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Tim";
        });

        CompletableFuture combinedFuture = helloFuture.thenCombine(nameFuture, (a, b) -> a + " " + b);

        try {
            System.out.println("Composed Result = "+combinedFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long totalTime =System.currentTimeMillis() - start;
        System.out.println("Executed in "+totalTime+ " ms");

    }

}
