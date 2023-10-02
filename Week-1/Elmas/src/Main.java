import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int uzunluk, uzunluk1;
        Scanner inp = new Scanner(System.in);
        System.out.println("Elmasın uzunluk değerini giriniz (Tek Sayı) : ");
        uzunluk1 = inp.nextInt();
        uzunluk = (uzunluk1 - 1) / 2;
        if (uzunluk1 % 2 == 1) {
            //hangi satırda olduğu
            for (int i = 0; i <= uzunluk; i++) {
                //o satırda yıldızdan önce kaç boşluk olacağı
                for (int j = 0; j < (uzunluk - i); j++) {
                    System.out.print(" ");
                }
                //Yıldız sayısı
                for (int k = 1; k <= (2 * i + 1); k++) {
                    System.out.print("*");
                }
                System.out.println(" ");
            }

            for (int i = uzunluk - 1; i >= 0; i--) {
                for (int j = 0; j < (uzunluk - i); j++) {
                    System.out.print(" ");
                }
                //Yıldız sayısı
                for (int k = 1; k <= (2 * i + 1); k++) {
                    System.out.print("*");
                }
                System.out.println(" ");
            }


        } else {
            System.out.println("Lütfen tek sayı giriniz.");

        }

        /* Alternatif
        *
        for(int i=1;i<=num;i++) {
            System.out.print(" ");
            System.out.print(" ".repeat(num-i));
            System.out.println("*".repeat((2*i)-1));
        }
        System.out.println("*".repeat((2*num)+1));
        for(int i=num; i>=1 ; i--) {
            System.out.print(" ");
            System.out.println("*".repeat(2*i-1));
            System.out.print(" ".repeat((num-i)+1));
        }
        *
        *
        *
        *
        *
        *
        * */


    }
}




