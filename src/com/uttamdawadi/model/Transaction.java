package com.uttamdawadi.model;

import java.sql.Time;
import java.sql.Timestamp;

public class Transaction {

    private int txnid;
    private Timestamp timestamp;
    private String account;
    private String advisor;
    private double value;

    public Transaction(int txnid, Timestamp timestamp, String account, String advisor, double value) {
        this.txnid = txnid;
        this.timestamp = timestamp;
        this.account = account;
        this.advisor = advisor;
        this.value = value;
    }

    public Transaction() {

    }


    public int getTxnid() {
        return txnid;
    }

    public void setTxnid(int txnid) {
        this.txnid = txnid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "txnid=" + txnid +
                ", timestamp=" + timestamp +
                ", account='" + account + '\'' +
                ", advisor='" + advisor + '\'' +
                ", value=" + value +
                '}';
    }
}
