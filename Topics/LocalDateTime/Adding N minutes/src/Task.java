import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        LocalDateTime localDateTime = LocalDateTime.parse(input);
        int minutes = scanner.nextInt();
        LocalDateTime newLocalDateTime = localDateTime.plusMinutes(minutes);
        System.out.println(String.format("%d %d %s", newLocalDateTime.getYear(),
                newLocalDateTime.getDayOfYear(), newLocalDateTime.toLocalTime()));
    }
}
