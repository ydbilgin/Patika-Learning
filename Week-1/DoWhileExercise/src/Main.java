import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int k , toplam = 0;
        Scanner inp = new Scanner(System.in);

        do {
            System.out.print("Sayı giriniz : ");
            k = inp.nextInt();
            if (k % 4 == 0){
                toplam = toplam + k;

            }
        }while (k % 2 == 0);
        System.out.println("4'ün katı olan sayıların toplamı : " + toplam);
    }
}