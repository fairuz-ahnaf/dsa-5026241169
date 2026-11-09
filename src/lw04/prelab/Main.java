package lw04.prelab;

import java.util.*;

public class Main {

    // Insertion sort descending, berhenti tepat setelah `passes` pass
    static void insertionSortDesc(int[] a, int passes) {
        int n = a.length;
        for (int i = 1; i <= passes && i < n; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] < key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    // Linear search (array belum terurut penuh, jadi binary search tidak valid)
    static int linearSearch(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));
        boolean first = true;

        // Baca test case satu per satu sampai file habis
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] branches = new int[n];
            for (int i = 0; i < n; i++) {
                branches[i] = sc.nextInt();
            }

            int m = sc.nextInt();
            int[] targets = new int[m];
            for (int i = 0; i < m; i++) {
                targets[i] = sc.nextInt();
            }

            insertionSortDesc(branches, 3);

            if (!first) {
                System.out.println(); // pemisah antar test case
            }
            first = false;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i > 0) sb.append(" ");
                sb.append(branches[i]);
            }
            System.out.println(sb);

            for (int t : targets) {
                int idx = linearSearch(branches, t);
                if (idx != -1) {
                    System.out.println(t + ": FOUND at index " + idx);
                } else {
                    System.out.println(t + ": NOT FOUND");
                }
            }
        }
        sc.close();
    }
}