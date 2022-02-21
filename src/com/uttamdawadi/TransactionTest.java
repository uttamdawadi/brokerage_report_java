package com.uttamdawadi;

import com.uttamdawadi.model.Transaction;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.List;


class TransactionTest {

    Main main= new Main();
    List<Transaction> transactions = main.readTransaction("src/com/uttamdawadi/resource/transaction.csv");

    @Test
    void timestampConversationOne() {

        Timestamp insertTimestamp = Timestamp.valueOf("2020-10-12 09:41:09");
        String convertedTimestamp = main.convertTimeStamp(insertTimestamp);
        Assert.assertEquals("12 Oct 2020",convertedTimestamp);
    }
    @Test
    void timestampConversationTwo() {

        Timestamp insertTimestamp = Timestamp.valueOf("2020-10-11 13:41:35");
        String convertedTimestamp = main.convertTimeStamp(insertTimestamp);
        Assert.assertEquals("12 Oct 2020",convertedTimestamp);
    }
    @Test
    void getTotalAmountABC(){

        String advisor = "ABC";
        String totalAmount = main.totalBrokerageAmount(transactions,advisor);
        Assert.assertEquals("5,712.00",totalAmount);

    }
    @Test
    void getTotalAmountCBA(){

        String advisor = "CBA";
        String totalAmount = main.totalBrokerageAmount(transactions,advisor);
        Assert.assertEquals("653.00",totalAmount);
    }

    @Test
    void convertStringToTimestampToSydneyTimezone(){
        String stringTimestamp = "2020-10-12T09:41:09.327899600Z";
        Timestamp time = main.convertStringToTimestamp(stringTimestamp);
        String convertedTimestamp = main.convertTimeStamp(time);
        Assert.assertEquals("12 Oct 2020",convertedTimestamp);
    }

}