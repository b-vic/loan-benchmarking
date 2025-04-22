package com.bill.test;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class Loan {

    private long loanId;
    private int loanAmount;
    private String clientId;
    private int loanDurationMonths;

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public int getLoanDurationMonths() {
        return loanDurationMonths;
    }

    public void setLoanDurationMonths(int loanDurationMonths) {
        this.loanDurationMonths = loanDurationMonths;
    }


}
