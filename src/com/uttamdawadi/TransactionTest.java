package com.uttamdawadi;

import com.uttamdawadi.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.List;


class TransactionTest {

    final List<Transaction> transactions = Main.readTransaction("src/com/uttamdawadi/resource/transaction.csv");

    @Test
    void timestampConversationOne() {

        Timestamp insertTimestamp = Timestamp.valueOf("2020-10-12 09:41:09");
        String convertedTimestamp = Main.convertTimeStamp(insertTimestamp);
        Assertions.assertEquals("12 Oct 2020",convertedTimestamp);
    }
    @Test
    void timestampConversationTwo() {

        Timestamp insertTimestamp = Timestamp.valueOf("2020-10-11 13:41:35");
        String convertedTimestamp = Main.convertTimeStamp(insertTimestamp);
        Assertions.assertEquals("12 Oct 2020",convertedTimestamp);
    }
    @Test
    void getTotalAmountABC(){

        String advisor = "ABC";
        String totalAmount = Main.totalBrokerageAmount(transactions,advisor);
        Assertions.assertEquals("5,712.00",totalAmount);

    }
    @Test
    void getTotalAmountCBA(){

        String advisor = "CBA";
        String totalAmount = Main.totalBrokerageAmount(transactions,advisor);
        Assertions.assertEquals("653.00",totalAmount);
    }

    @Test
    void convertStringToTimestampToSydneyTimezone(){
        String stringTimestamp = "2020-10-12T09:41:09.327899600Z";
        Timestamp time = Main.convertStringToTimestamp(stringTimestamp);
        String convertedTimestamp = Main.convertTimeStamp(time);
        Assertions.assertEquals("12 Oct 2020",convertedTimestamp);
    }

}