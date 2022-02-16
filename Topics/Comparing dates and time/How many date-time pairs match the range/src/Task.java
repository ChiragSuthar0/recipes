import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        final Scanner scanner = new Scanner(System.in);
        final LocalDateTime firstDateTime = LocalDateTime.parse(scanner.next());
        final LocalDateTime secondDateTime = LocalDateTime.parse(scanner.next());
        LocalDateTime startDateTime = firstDateTime.isBefore(secondDateTime) ? firstDateTime : secondDateTime;
        LocalDateTime endDateTime = secondDateTime.isAfter(firstDateTime) ? secondDateTime : firstDateTime;

        int sol = 0;
        final int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            LocalDateTime localDateTime = LocalDateTime.parse(scanner.next());
            if (startDateTime.compareTo(localDateTime) <= 0 && endDateTime.isAfter(localDateTime)) {
                sol++;
            }
        }
        System.out.println(sol);
    }
}
