import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int toplamNot = 0;
        int gecerliDersSayisi = 0;
        int sinifGecmeNotu = 55;

        String[] dersler = {"Türkçe", "Matematik", "Fizik", "Kimya", "Müzik"};

        int i;
        for (i = 0; i < dersler.length; i++) {
            String ders = dersler[i];
            System.out.print(ders + " notunu giriniz (0-100 arası): ");
            int not = input.nextInt();

            if (not >= 0 && not <= 100) {
                toplamNot += not;
                gecerliDersSayisi++;
            } else {
                System.out.println("Geçersiz not girişi! Not 0 ile 100 arasında olmalı.");
            }
        }

        if (gecerliDersSayisi > 0) {
            double ortalama = (double) toplamNot / gecerliDersSayisi;
            System.out.println("Derslerin Ortalaması: " + ortalama);
            if (ortalama >= sinifGecmeNotu) {
                System.out.println("Sınıfı geçtiniz!");
            } else {
                System.out.println("Sınıfı geçemediniz!");
            }

        } else {
            System.out.println("Geçerli not girilmedi, ortalama hesaplanamıyor.");


        }
    }
}