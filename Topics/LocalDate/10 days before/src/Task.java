import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        LocalDate localDate = LocalDate.parse(input);
        System.out.println(localDate.minusDays(10));
    }
}
