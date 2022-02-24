package com.uttamdawadi;

import com.uttamdawadi.model.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        //Convert the csv file data to list
        List<Transaction> transactions = readTransaction("src/com/uttamdawadi/resource/transaction.csv");
        //Calculation of Brokerage
        List<String> advisor = transactions.stream()
                .map(Transaction::getAdvisor)
                .distinct().collect(Collectors.toList());

        for (String e : advisor) {
            /*
             * The first row is the adviser code
             * */
            System.out.println(e);
            /* The second row is the total value of the transactions for that adviser
             * The total transaction value should be formatted with two decimal places, with a thousand separator.
             * The total transaction value column should be padded to 10 characters
             * */
            System.out.println(leftPadding(totalBrokerageAmount(transactions, e), ' ', 10));
            /* The third row shows the date/time of the oldest and newest transaction for the adviser
             * The date should be shown in Sydney local time
             * The date should be 2 digit day, 3 character month and 4 character year
             * */
            HashMap<String, String> dateList = (HashMap<String, String>) transactionDate(transactions, e);
            System.out.println(dateList.get("oldestDate") + " -> " + dateList.get("newestDate"));

        }
    }

    private static Map<String, String> transactionDate(List<Transaction> transaction, String adviser) {

        List<Transaction> datesList = transaction.stream().filter(name -> adviser.equals(name.getAdvisor()))
                .sorted(Comparator.comparing(Transaction::getTimestamp))
                .collect(Collectors.toList());
        String oldestDate = convertTimeStamp(datesList.get(0).getTimestamp());
        String newestDate = convertTimeStamp(datesList.get(datesList.size() - 1).getTimestamp());
        Map<String, String> tDate = new HashMap<>();
        tDate.put("oldestDate", oldestDate);
        tDate.put("newestDate", newestDate);
        return tDate;
    }

    public static String totalBrokerageAmount(List<Transaction> transaction, String adviser) {
        double totalAmount = 0;
        totalAmount = transaction.stream().
                filter(name -> adviser.equals(name.getAdvisor())).mapToDouble(Transaction::getValue).sum();
        return String.format("%,.2f", totalAmount);
    }

    public static List<Transaction> readTransaction(String fileName) {
        String COMMA_DELIMITER = ",";
        List<Transaction> records = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
            String line;
            String headerLine = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                Transaction tm = createTransaction(values);
                records.add(tm);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static String convertTimeStamp(Timestamp tm) {

        String DATE_FORMAT = "dd MMM yyyy";
        LocalDateTime ldt = tm.toLocalDateTime();
        ZonedDateTime zoneDateTime = ldt.atZone(ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.of("Australia/Sydney"));
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return format.format(zoneDateTime);
    }

    public static Timestamp convertStringToTimestamp(String strDate) {
        Timestamp t = null;
        Instant instant = Instant.parse(strDate);
        LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        t = Timestamp.valueOf(date);
        return t;
    }

    private static Transaction createTransaction(String[] metadata) {
        int txnid = Integer.parseInt(metadata[0]);
        Timestamp timestamp = convertStringToTimestamp(metadata[1]);
        String account = metadata[2];
        String advisor = metadata[3];
        double value = Double.parseDouble(metadata[4]);
        return new Transaction(txnid, timestamp, account, advisor, value);
    }

    public static String leftPadding(String input, char ch, int L) {
        return String.format("%" + L + "s", input).replace(' ', ch);
    }

}

