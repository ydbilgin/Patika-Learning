import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double n , toplam = 0;
        Scanner inp = new Scanner(System.in);
        System.out.println("Bir sayı giriniz : ");
        n = inp.nextDouble();
        for (double i=1; i<=n ; i++){
            toplam = toplam + (Double)(1/i);

        }
        String toplamStr = String.valueOf(toplam);
        toplamStr = toplamStr.substring(0,5);

        System.out.println("Harmonik serisinin sonucu : " + toplamStr);
    }
}