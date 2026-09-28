package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static final int MAX_BORROW = 2;

    public static void main(String[] args) {

        LinkedList<String[]> requests = new LinkedList<>();

        LinkedList<String[]> books = new LinkedList<>();
        books.add(new String[]{"Kalkulus", "2"});
        books.add(new String[]{"Fisika", "1"});
        books.add(new String[]{"Statistika", "2"});

        LinkedList<String[]> members = new LinkedList<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.length() == 0) {
                continue;
            }
            Scanner lineScanner = new Scanner(line);
            String name = lineScanner.next();
            String title = lineScanner.next();
            lineScanner.close();

            requests.add(new String[]{name, title});

            boolean exists = false;
            for (int i = 0; i < members.size(); i++) {
                if (members.get(i)[0].equals(name)) {
                    exists = true;
                }
            }
            if (!exists) {
                members.add(new String[]{name, "0"});
            }
        }
        sc.close();
        
        Queue<String[]> queue = new LinkedList<>();
        for (int i = 0; i < requests.size(); i++) {
            queue.add(requests.get(i));
        }

        Stack<String[]> failed = new Stack<>();
        LinkedList<String[]> success = new LinkedList<>();

        while (!queue.isEmpty()) {
            String[] req = queue.poll();

            String[] book = null;
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i)[0].equals(req[1])) {
                    book = books.get(i);
                }
            }
            String[] member = null;
            for (int i = 0; i < members.size(); i++) {
                if (members.get(i)[0].equals(req[0])) {
                    member = members.get(i);
                }
            }

            boolean hasStock = false;
            boolean underLimit = false;
            if (book != null) {
                hasStock = Integer.parseInt(book[1]) > 0;
            }
            if (member != null) {
                underLimit = Integer.parseInt(member[1]) < MAX_BORROW;
            }

            if (hasStock && underLimit) {
                book[1] = String.valueOf(Integer.parseInt(book[1]) - 1);
                member[1] = String.valueOf(Integer.parseInt(member[1]) + 1);
                success.add(req);
            } else {
                failed.push(req);
            }
        }

        System.out.println("=== Successfully Processed Requests ===");
        for (int i = 0; i < success.size(); i++) {
            System.out.println(success.get(i)[0] + " " + success.get(i)[1]);
        }

        System.out.println();
        System.out.println("=== Remaining Book Stock ===");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i)[0] + " : " + books.get(i)[1]);
        }

        System.out.println();
        System.out.println("=== Failed Requests ===");
        while (!failed.isEmpty()) {
            String[] req = failed.pop();
            System.out.println(req[0] + " " + req[1]);
        }
    }
}
