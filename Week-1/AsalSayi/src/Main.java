import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> numberList = new ArrayList<Integer>();

        for (int i = 2; i <= 100; i++) {
            boolean asal = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    asal = false;
                }
            }
            if (asal) {
                numberList.add(i);
            }
        }
        System.out.println(numberList);
    }
}
