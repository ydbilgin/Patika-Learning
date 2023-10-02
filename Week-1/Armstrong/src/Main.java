import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int a , basamak= 0, toplam = 0;
        Scanner inp = new Scanner(System.in);
        System.out.println("Bir sayı giriniz : ");
        a = inp.nextInt();
        while (a != 0){
            basamak = a % 10;
            toplam = toplam + basamak;
            a = a/10;

        }
        System.out.println("Basamakların toplamı : " + toplam);


    }
}