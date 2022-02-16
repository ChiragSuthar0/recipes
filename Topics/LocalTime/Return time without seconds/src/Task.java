import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String point = scanner.next();
        LocalTime localTime = LocalTime.parse(point);
        System.out.println(localTime.withSecond(0));
    }
}
