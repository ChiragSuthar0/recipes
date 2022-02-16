import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        List<String> input = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());
        int numSwap = scanner.nextInt();
        for (int i = 0; i < numSwap; i++) {
            int firstIdx = scanner.nextInt();
            int secondIdx = scanner.nextInt();
            Collections.swap(input, firstIdx, secondIdx);
        }
        input.forEach(s -> System.out.print(s + " "));
    }
}
