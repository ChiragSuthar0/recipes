import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        final Scanner scanner = new Scanner(System.in);
        final LocalTime baseTime = LocalTime.parse("20:00");
        int numberOfStores = scanner.nextInt();
        for (int i = 0; i < numberOfStores; i++) {
            String storeName = scanner.next();
            LocalTime storeTime = LocalTime.parse(scanner.next());
            if (storeTime.isAfter(baseTime)) {
                System.out.println(storeName);
            }
        }
    }
}
