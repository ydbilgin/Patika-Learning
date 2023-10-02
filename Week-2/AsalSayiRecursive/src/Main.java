import java.util.Scanner;

public class Main {
    static boolean isAsal(int a, int b) {
        while (b > 2) {
            //a sayısından 1 eksiğiyle başlayarak 2 ye kadar modunu aldırıyorum. eğer bölünebiliyorsa asal sayı değil bölünemiyorsa asal sayı
            if (a % b == 0) {
                System.out.println(a + " asal sayı değildir.");
                return false;
            } else if (b == 3) {
                System.out.println(a + " asal sayıdır .");
                return true;
            }
            b--;
        }
        return isAsal(a, b - 1);

    }

    public static void main(String[] args) {
        int a, b;
        Scanner input = new Scanner(System.in);
        System.out.println("Sayı : ");
        a = input.nextInt();
        b = a - 1;

        isAsal(a, b);


    }
}


