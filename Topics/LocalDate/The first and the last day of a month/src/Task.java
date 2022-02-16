import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        LocalDate date = LocalDate.of(year, month, 1);
        int length = date.lengthOfMonth();
        System.out.println(date + " " + date.plusDays(length - 1));
        LocalDate today = LocalDate.now();
        System.out.println(today);

    }
}
