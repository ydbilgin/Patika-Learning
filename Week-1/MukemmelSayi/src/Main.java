import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> sayilar = new ArrayList<Integer>();
        System.out.println("Bir sayı giriniz : ");
        int sayi = input.nextInt();
        while (sayi<0){
            System.out.println("Lütfen 0'dan büyük bir değer giriniz : ");
            sayi = input.nextInt();
        }

        for (int i = 1; i <= sayi; i++) {
            if (sayi % i == 0) {
                sayilar.add(i);
            }
        }
        int mukSayi = 0;
        //size'ı kadar index sayının kendisini kapsadığı için -1 yaptım.
        for (int i = 0; i < sayilar.size() - 1; i++) {
            mukSayi += sayilar.get(i);

        }
        if (mukSayi == sayi) {
            System.out.println(sayi + " mükemmel sayıdır. ");
        } else {
            System.out.println(sayi + " mükemmel sayı değildir. ");
        }


    }
}


