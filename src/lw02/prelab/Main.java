package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        // 1. Read and store transactions
        LinkedList<String[]> transactions = new LinkedList<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("transaction.txt"));
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            
            if (line.isEmpty()) {
                continue;
            }
            
            String[] parts = line.split("\\s+");
            // parts[0] = name, parts[1] = type, parts[2] = amount
            transactions.add(parts);
        }
        sc.close();

        // 2. Create customer data (LinkedList<String[]>: {name, balance})
        LinkedList<String[]> customers = new LinkedList<>();

        for (String[] transaction : transactions) {
            String name = transaction[0];
            boolean alreadyExists = false;

            for (String[] customer : customers) {
                if (customer[0].equals(name)) {
                    alreadyExists = true;
                    break;
                }
            }

            if (!alreadyExists) {
                customers.add(new String[] { name, "0" });
            }
        }

        // 3. Process transactions using Queue (FIFO)
        Queue<String[]> transactionQueue = new LinkedList<>();
        transactionQueue.addAll(transactions);

        // 4. Store failed withdrawals using Stack (LIFO)
        Stack<String[]> failedTransactions = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] transaction = transactionQueue.poll();

            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            // Find the corresponding customer
            String[] customer = null;
            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    customer = c;
                    break;
                }
            }

            if (customer == null) {
                continue; // should not happen given how customers are built
            }

            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    // Withdrawal fails, balance unchanged
                    failedTransactions.push(transaction);
                } else {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        // 5. Display final balances and failed transactions
        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}