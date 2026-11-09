
import java.util.*;

public class Main {

    // Selection sort (ascending), berhenti setelah 'passes' putaran
    static void sortCards(int[] arr, int passes) {
        int n = arr.length;
        for (int i = 0; i < passes && i < n - 1; i++) {
            int idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[idx]) {
                    idx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }
    }

    // Linear search: kembalikan index target, atau -1 jika tidak ditemukan
    static int findCard(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(Main.class.getResourceAsStream("cards.txt"));

        int n = sc.nextInt();
        int[] cards = new int[n];
        for (int i = 0; i < n; i++) {
            cards[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] requests = new int[m];
        for (int i = 0; i < m; i++) {
            requests[i] = sc.nextInt();
        }
        sc.close();

        sortCards(cards, 2);

        // Tampilkan array setelah 2 pass
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(cards[i]);
        }
        System.out.println();

        // Tampilkan hasil pencarian sesuai urutan permintaan
        for (int i = 0; i < m; i++) {
            int pos = findCard(cards, requests[i]);
            if (pos == -1) {
                System.out.println(requests[i] + ": NOT FOUND");
            } else {
                System.out.println(requests[i] + ": FOUND at index " + pos);
            }
        }
    }
}