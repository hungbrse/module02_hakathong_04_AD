package Exam_Advance_2;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số ISBN (10 chữ số): ");
        String isbn = scanner.nextLine();

        if (isbn.length() != 10 || !isbn.matches("\\d+")) {
            System.out.println("Số ISBN không hợp lệ. Đảm bảo rằng nó có đúng 10 chữ số.");
            return;
        }

        Stack<Character> stack = new Stack<>();
        for (char ch : isbn.toCharArray()) {
            stack.push(ch);
        }

        int sum = 0;
        int multiplier = 10;

        while (!stack.isEmpty()) {
            char digitChar = stack.pop();
            int digit = Character.getNumericValue(digitChar);
            sum += multiplier * digit;
            multiplier--;
        }

        if (sum % 11 == 0) {
            System.out.println("Số ISBN hợp lệ.");
        } else {
            System.out.println("Số ISBN không hợp lệ.");
        }
    }
}
