import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double sumH = 0;
        double avarageOfHarmonic = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Kaç adet sayının harmonik ortalamasını istiyorsunuz? Giriniz :  ");
        int n = input.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        // kaç adet sayı girilecekse bunları bi list arrayinin içine atıyorum.
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + " . Elemanı : ");
            int a = input.nextInt();
            list.add(a);
        }
        //arrayin tüm indexlerinin -1. kuvvetini alarak toplayıp bi toplam elde ettim.
        for (int i : list){
            sumH += (1.0/i);
        }
        //harmonik ortalama hesaplarken toplam sayıyı tüm değerlerin -1. kuvvet toplamına bölerek sonucu buldum
        avarageOfHarmonic = (n/sumH);
        System.out.println(avarageOfHarmonic);
    }
}