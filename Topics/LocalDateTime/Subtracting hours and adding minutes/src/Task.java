import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        LocalDateTime localDateTime = LocalDateTime.parse(input);
        System.out.println(localDateTime.minusHours(n).plusMinutes(m));
    }
}
