import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String firstLine = scanner.nextLine();
        String secondLine = scanner.nextLine();

        String[] firstSeq = firstLine.split(" ");
        String[] secondSeq = secondLine.split(" ");

        int firstIndex = Collections.indexOfSubList(List.of(firstSeq), List.of(secondSeq));
        int lastIndex = Collections.lastIndexOfSubList(List.of(firstSeq), List.of(secondSeq));
        System.out.println(String.format("%d %d", firstIndex, lastIndex));
    }
}
