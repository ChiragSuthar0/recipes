// write your answer here 

import java.time.LocalDate;
import java.util.Scanner;

public class Task {
  public static void main(String[] args) {
    // put your code here
    Scanner scanner = new Scanner(System.in);
    String input = scanner.next();
    LocalDate date = LocalDate.parse(input);
    System.out.println(date.plusWeeks(2));
  }
}
