import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        //4 ve 5 in kuvvetlerini iki farklı liste halinde tuttum
        List <Integer> kuvvet4 = new ArrayList<>();
        List <Integer> kuvvet5 = new ArrayList<>();
        int girilenDeger;
        Scanner inp = new Scanner(System.in);
        System.out.println("Lütfen bir sayı giriniz : ");
        girilenDeger = inp.nextInt();

        for (int i = 1; i <= girilenDeger; i = i * 4) {
            kuvvet4.add(i);
        }

        for (int k = 1; k <= girilenDeger; k = k * 5) {
            kuvvet5.add(k);
        }

        // tablo halinde yazdırmak için yaptığım işlemler
        //stringlerim biraz garip duruyor şu an ama daha güzel görünüm için bir şey denedim. ileride boşlukları daha güzel bi şekilde değiştireceğim
        System.out.println("Kuvvetler\t4\t        5"); // tabloda eşit bi şekilde oturması için kuvvetler charı kadar boşluk bıraktım
        int maxSize = Math.max(kuvvet4.size(), kuvvet5.size());
        for (int i = 0 ; i <= maxSize-1 ; i++){
            String veri4 = (i < kuvvet4.size()) ? "" + (kuvvet4.get(i)) : "-";
            String veri5 = (i < kuvvet5.size()) ? "" + (kuvvet5.get(i) ): "-";
            System.out.println(i+ "\t        " + veri4 + "\t        " + veri5);//aynı şekilde tablo oturtmak için boşluklar
        }


    }
}