package lw03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    //Problem 1: Playlist (List)
    static void problem1() {
        List<String> playlist = new ArrayList<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ", 2);
            String type = parts[0];

            if (type.equals("ADD") && parts.length == 2) {
                playlist.add(parts[1]);
            } else if (type.equals("INSERT") && parts.length == 2) {
                String[] p = parts[1].split(" ", 2); // <INDEX> <SONG>
                int index = Integer.parseInt(p[0]);
                playlist.add(index, p[1]);
            } else if (type.equals("REMOVE") && parts.length == 2) {
                playlist.remove(parts[1]); // removes first occurrence, no-op if absent
            }
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    //Problem 2: Workshop participants (Set)
    static void problem2() {
        Set<String> participants = new LinkedHashSet<>(); // keeps first-appearance order
        int duplicates = 0;

        Scanner sc = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        while (sc.hasNextLine()) {
            String name = sc.nextLine().trim();
            if (name.isEmpty()) continue;

            if (!participants.add(name)) { // add() returns false if already present
                duplicates++;
            }
        }

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int no = 1;
        for (String name : participants) {
            System.out.println(no++ + ". " + name);
        }
        System.out.println("Duplicate registrations: " + duplicates);
    }

    //Problem 3: Inventory (Map)
    static void problem3() {
        Map<String, Integer> stock = new LinkedHashMap<>(); // keeps first-appearance order
        int failedSales = 0;

        Scanner sc = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            String type = parts[0];
            String product = parts[1];
            int qty = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                stock.put(product, stock.getOrDefault(product, 0) + qty);
            } else if (type.equals("SELL")) {
                if (stock.containsKey(product) && stock.get(product) >= qty) {
                    stock.put(product, stock.get(product) - qty);
                } else {
                    failedSales++;
                }
            }
        }

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> e : stock.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }

    public static void main(String[] args) {
        problem1();
        System.out.println();
        problem2();
        System.out.println();
        problem3();
    }
}
