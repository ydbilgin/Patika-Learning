import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> sayilar = new ArrayList<Integer>();
        int sayi ;
        System.out.println("Kaç tane sayı gireceksiniz : ");
        sayi = input.nextInt();
        int i= 1;
        if (sayi<1){
            System.out.println("Lütfen 0'dan büyük bir sayı giriniz : ");
            while (sayi<1){
                System.out.println("Kaç tane sayı gireceksiniz : ");
                sayi = input.nextInt();
            }
        }
        //girdiğim sayıları arrayin içerisinde tuttum.
        while (i<=sayi){
            System.out.println(i + ". sayıyı giriniz : ");
            int j = input.nextInt();
            sayilar.add(j);
            i++;
        }
        //arrayin 0. indexine en küçük sayıyı eşitledim. aşağıda tüm indexleri dolaşıp küçük bulmam halinde o değeri atadım.
        int enKucuk = sayilar.get(0);
        for (int j = 0; j < sayilar.size(); j++){
            if (sayilar.get(j) < enKucuk){
                enKucuk = sayilar.get(j);
            }
        }
        //arrayin 0. indexine en büyük sayıyı eşitledim. aşağıda tüm indexleri dolaşıp büyük bulmam halinde o değeri atadım.
        int enBuyuk = sayilar.get(0);
        for (int j = 0; j < sayilar.size(); j++){
            if (sayilar.get(j) > enKucuk){
                enBuyuk = sayilar.get(j);
            }
        }

        System.out.println("En büyük değer : " + enBuyuk);
        System.out.println("En küçük değer : " + enKucuk);

        /* array'e attığım için tek komutla da yapabiliyordum ama yapısını kurmak istedim.
        System.out.println(Collections.min(sayilar));
        System.out.println(Collections.max(sayilar));
        */

    }
}