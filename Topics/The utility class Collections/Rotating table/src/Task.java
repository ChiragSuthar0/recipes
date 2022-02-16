import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        scanner.nextInt();

        List<String> tables = new ArrayList<>();
        for (int i = 0; i <= row; i++) {
            String input = scanner.nextLine();
            if (input.length() > 0) {
                tables.add(input);
            }
        }
        int distance = scanner.nextInt();
        Collections.rotate(tables, distance);
        tables.forEach(s -> System.out.println(s));
    }
}
