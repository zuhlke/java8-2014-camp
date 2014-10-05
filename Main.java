package com.company;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.time.Month.JULY;

public class Main {
    public static void main(String[] args) throws ScriptException, FileNotFoundException, NoSuchMethodException {
        streams();
//
//        simpleNumStreams();
//        moreIntStreams();
//
//        showThreadExecution();
//
//        mapFunction();
//        filterFunction();
//        filterDistinctFunction();
//        mapRedFunction();
//
//        dateTimeNow();
//        dateTimeManipulation();
//        dateTimeFixed();
//        timeZone();
//        miscDateTime();
//        timeStamp();
//
//        nashorn1();
//        nashorn2();
//        nashorn3();

    }

    private static void streams() {
        Stream.of("This", "is", "a very simple", "stream", "of", "strings").forEach(System.out::println);

        System.out.println("----------------------------");

        String sentence = "This is a very simple stream of an array of strings";
        Stream.of(sentence.split(" ")).forEach(System.out::println);
    }

    private static void simpleNumStreams() {
        IntStream.range(1, 10).forEach(System.out::print);
        System.out.println();

        LongStream.rangeClosed(1, 5).forEach(System.out::print);
    }

    private static void moreIntStreams() {
        IntStream.range(1, 10).forEach(System.out::print);
        System.out.print(" <<<<< IDENTICAL >>>>> ");

        IntStream.range(1, 10).forEach(num -> System.out.print(num));
        System.out.println();


        IntStream.range(1, 10).forEach(num -> {
            System.out.print(num);
            System.out.print(",");
        });
        System.out.println();
    }

    private static void showThreadExecution() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from thread - the old way");
            }
        }).start();

        new Thread(() -> System.out.println("Hello from thread - the new way")).start();
    }

    private static void mapFunction() {
        List<String> countries = Arrays.asList("USA", "UK", "Canada", "Germany", "Slovenia");

        List<String> collectedList = countries.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(collectedList);

    }

    private static void filterFunction() {
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> evens = values.stream()
                .filter(val -> val % 2 == 0)
                .collect(Collectors.toList());

        long evensCount = values.stream()
                .filter(val -> val % 2 == 0)
                .count();

        System.out.println("Found " + evensCount + ": " +evens);
    }

    private static void filterDistinctFunction() {
        final List<Integer> values = Arrays.asList(1, 1, 2, 3, 4, 3, 5, 6,6, 7, 8,8,9, 9, 10, 1);

        List<Integer> odds = values.stream()
                .distinct()
                .filter(Main::evaluate)
                .collect(Collectors.toList());

        long oddCount = values.stream()
                .distinct()
                .filter(Main::evaluate)
                .count();

        System.out.println("Found " + oddCount + ": " +odds);
    }

    private static boolean evaluate(Integer val) {
        return val % 2 != 0;
    }

    private static void mapRedFunction() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        list.forEach(System.out::print);

        int sum = list.stream()
                .map(x -> x * x)
                .reduce((val, accumulator) -> val + accumulator)
                .get();

        System.out.println(sum);
    }

    private static void dateTimeNow() {
        LocalTime timeNow = LocalTime.now();
        System.out.println("Current Time: " + timeNow);

        LocalDate today = LocalDate.now();
        System.out.println("Current Date: " + today);

        LocalDateTime dateTimeNow = LocalDateTime.now();
        System.out.println("Date Time now: " + dateTimeNow);
    }

    private static void dateTimeManipulation() {
        LocalTime timeNow = LocalTime.now();
        System.out.println("4 Hours ago: " + timeNow.minusHours(4));
        System.out.println("30 minutes from now: " + timeNow.plusMinutes(30));

        LocalDate today = LocalDate.now();
        System.out.println("20 Years ago: " + today.minusYears(20));
        System.out.println("1000 Months from now: " + today.plusMonths(1000));

        LocalDateTime dateTimeNow = LocalDateTime.now();
        System.out.println("2 Years 1 Month from now: " + dateTimeNow.plusMonths(1).plusYears(2));
    }

    private static void dateTimeFixed() {
        LocalTime fixedTime = LocalTime.of(20, 59);
        System.out.println("\nConstructed Date: " + fixedTime);

        LocalDate julyDate = LocalDate.of(2014, JULY, 14);
        System.out.println("Constructed Date: " + julyDate);

        LocalDateTime fixedDateTime = LocalDateTime.of(julyDate, fixedTime);
        System.out.println("Constructed DateTime: "+fixedDateTime);
    }

    private static void timeZone() {
        System.out.println(LocalDateTime.now());
        LocalDate USA = LocalDate.now(ZoneId.of("America/Los_Angeles"));
        LocalTime USA_TIME =  LocalTime.now(ZoneId.of("America/Los_Angeles"));

        LocalDate AUS = LocalDate.now(ZoneId.of("Australia/Sydney"));
        LocalTime AUS_TIME =  LocalTime.now(ZoneId.of("Australia/Sydney"));

        System.out.println("\nCurrent Date for USA: " + USA + " Time: " + USA_TIME);
        System.out.println("Current Date for AUS: " + AUS + " Time: " + AUS_TIME);
    }

    private static void miscDateTime() {
        LocalDate postEpochDate = LocalDate.ofEpochDay(1024);
        System.out.println("1024 days after from epoch: " + postEpochDate);

        LocalDate christmasDay = LocalDate.ofYearDay(2014, 359);
        System.out.println("Christmas day of 2014: " + christmasDay);

        LocalTime futureTime = LocalTime.ofSecondOfDay(60*60*16);
        System.out.println("4 pm: " + futureTime);

        String format1 = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
        String format2 = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("Formatted Date: " + format1);
        System.out.println("Basic ISO Date: " + format2);
    }

    private static void timeStamp() {
        Instant timestamp = Instant.now();
        System.out.println("Current Timestamp: " + timestamp);

        Instant specificTime = Instant.ofEpochSecond(987654321);
        System.out.println("Specific Time: " + specificTime);
    }

    private static void nashorn1() throws ScriptException, FileNotFoundException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('JavaScript Hello World!');");

        engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("script.js"));

        Invocable invocable = (Invocable) engine;

        Object result = invocable.invokeFunction("invokeMe", "Java Demo");
        System.out.println(result);
    }

    private static void nashorn2() throws ScriptException, FileNotFoundException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("script.js"));

        Invocable invocable = (Invocable) engine;

        Object result = invocable.invokeFunction("showObjectInfo", LocalDateTime.now());
        System.out.println(result);
    }

    private static void nashorn3() throws ScriptException, FileNotFoundException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("script2.js"));
    }

    public static String myJavaMethod(String message) {
        System.out.println(message);
        return "return statement from Java";
    }
}
