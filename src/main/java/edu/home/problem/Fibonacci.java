package edu.home.problem;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public interface Fibonacci {

    long fib(int n);

    class Metrics {
        String algorithm;
        int input;
        long fib;
        long timeTakenMiS;
        String formattedString() {
            return String.format("%5d %-16s %18d %8d", input, algorithm, fib, timeTakenMiS);
        }
    }

    default Metrics getFibWithMetrics(int n) {
        long startTime = System.nanoTime();
        long fibn = fib(n);
        long endTime = System.nanoTime();
        long timeTakenMiS = (endTime - startTime) / 1000;
        Metrics metrics = new Metrics();
        metrics.input = n;
        metrics.algorithm = this.getClass().getSimpleName().replaceAll("Fibonacci", "");
        metrics.fib = fibn;
        metrics.timeTakenMiS = timeTakenMiS;
//        System.out.println(metrics.formattedString());
        return metrics;
    }

    default CompletableFuture<Metrics> getFibWithMetricsFuture(int n) {
        CompletableFuture<Metrics> completableFuture
            = CompletableFuture.supplyAsync(() -> getFibWithMetrics(n));
        return  completableFuture;
    }

    static void main(String[] args) {
        Fibonacci recurseFibonacci = new RecurseFibonacci();
        Fibonacci recurseMemoizedFibonacci = new RecurseMemoizedFibonacci();
        Fibonacci bottomUpMemoizedFibonacci = new BottomUpMemoizedFibonacci();
        List<CompletableFuture<Metrics>> fibonacciFutures = new ArrayList<>();
        for (int i = 5; i < 81; i+=5) {
            if( i < 41) {
                fibonacciFutures.add(recurseFibonacci.getFibWithMetricsFuture(i));
            }
            fibonacciFutures.add(recurseMemoizedFibonacci.getFibWithMetricsFuture(i));
            fibonacciFutures.add(bottomUpMemoizedFibonacci.getFibWithMetricsFuture(i));
        }

        System.out.println(String.format("%5s %-16s %18s %8s",
            "Input", "Algorithm", "Fibonacci", "Time Taken (MiS)"));
//        fibonacciFutures.stream()
//            .map(CompletableFuture::join);
        System.out.println(
            fibonacciFutures.stream()
                .map(CompletableFuture::join)
                .sorted( Comparator.comparingInt(metrics -> metrics.input) )
                .map( Metrics::formattedString )
                .collect(Collectors.joining("\n")) );
    }

}

class RecurseFibonacci implements Fibonacci {
    public long fib(int n) {
        long result = 1;
        if (n > 2) {
            result = fib(n - 1) + fib(n - 2);
        }
        return result;
    }
}

class RecurseMemoizedFibonacci implements Fibonacci {
    public long fib(int n) {
        long[] fibs = new long[n+1];
        fibs[1] = 1;
        fibs[2] = 1;
        return fib(n, fibs);
    }
    public long fib(int n, long[] fibs) {
        long result = 1;
        if (n > 2) {
            if( fibs[n] > 0 ) {
                result = fibs[n];
            } else {
                result = fib(n - 1, fibs) + fib(n - 2, fibs);
                fibs[n] = result;
            }
        }
        return result;
    }
}

class BottomUpMemoizedFibonacci implements Fibonacci {
    public long fib(int n) {
        long result = 1;
        if (n > 2) {
            long[] fibs = new long[n + 1];
            fibs[1] = 1;
            fibs[2] = 1;
            for( int i = 3; i < n + 1; i++ ) {
                fibs[i] = fibs[i-1] + fibs[i-2];
            }
            result = fibs[n];
        }
        return result;
    }
}
