import java.util.Scanner;


public class Main {
    static void desenMetot(int n) {
        //n sayısını yan yana yazdırmak için
        System.out.print(n + " ");

        if (n <= 0) {
            return ;
        }

        desenMetot(n - 5);

        System.out.print(n + " ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("N Sayısını giriniz : ");
        int n = scanner.nextInt();
        scanner.close();

        desenMetot(n);
    }


}