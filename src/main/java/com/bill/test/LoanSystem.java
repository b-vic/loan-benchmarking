package com.bill.test;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class LoanSystem {

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void testStreams() {
        List<Loan> loanList = getLoans(100000);
        List<String> loanCustomers = getCustomersLargeLoanStreams(loanList);
        if (!loanCustomers.contains("123")) {
            throw new RuntimeException("Invalid result!");
        }
    }

    public List<String> getCustomersLargeLoanStreams(List<Loan> loanList) {
        return loanList.stream()
                .collect(Collectors.groupingBy(Loan::getClientId, Collectors.summingInt(Loan::getLoanAmount)))
                .entrySet()
                .stream().filter(v -> v.getValue() > 1000).map(Map.Entry::getKey).toList();
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void testLoops() {
        List<Loan> loanList = getLoans(100000);
        List<String> loanCustomers = getCustomersLargeLoanLoops(loanList);
        if (!loanCustomers.contains("123")) {
            throw new RuntimeException("Invalid result");
        }
    }

    public List<String> getCustomersLargeLoanLoops(List<Loan> loanList) {
        Map<String, Integer> customerLoanAmounts = new HashMap<>();

        for (Loan loan : loanList) {
            if (customerLoanAmounts.containsKey(loan.getClientId())) {
                //Add next loan amount to customer
                customerLoanAmounts.put(loan.getClientId(), customerLoanAmounts.get(loan.getClientId()) + loan.getLoanAmount());
            } else {
                //Add new loan amount for new customer
                customerLoanAmounts.put(loan.getClientId(), loan.getLoanAmount());
            }
        }

        List<String> clientList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : customerLoanAmounts.entrySet()) {
            if (entry.getValue() > 1000) {
                clientList.add(entry.getKey());
            }
        }

        return clientList;
    }

    private static List<Loan> getLoans() {
        Loan loan1 = new Loan();
        loan1.setClientId("123");
        loan1.setLoanId(1234);
        loan1.setLoanAmount(500);
        loan1.setLoanDurationMonths(12);

        Loan loan2 = new Loan();
        loan2.setClientId("123");
        loan2.setLoanId(12345);
        loan2.setLoanAmount(1500);
        loan2.setLoanDurationMonths(48);

        Loan loan3 = new Loan();
        loan3.setClientId("1234");
        loan3.setLoanId(12346);
        loan3.setLoanAmount(500);
        loan3.setLoanDurationMonths(48);

        return List.of(loan1, loan2, loan3);
    }

    private static List<Loan> getLoans(int numLoans) {
        List<Loan> loans = new ArrayList<>();

        //Ensure same 3 loans from before present:
        loans.addAll(getLoans());

        for (int i = 0; i < numLoans - 3; i++) {
            Loan loan = new Loan();
            //Customers between id 0 and 100:
            loan.setClientId(String.valueOf(new Random().nextInt(100)));
            loan.setLoanId(1234);
            //Loans between id 0 and 2000:
            loan.setLoanAmount(new Random().nextInt(2000));
            loan.setLoanDurationMonths(12);
            loans.add(loan);
        }
        return loans;
    }
}
