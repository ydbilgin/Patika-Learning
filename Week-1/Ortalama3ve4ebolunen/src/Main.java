import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int k , toplam = 0 , sayiAdedi = 0;
        double ortalama;
        // input alıyorum
        System.out.println("Sayı giriniz : ");
        k = input.nextInt();
        System.out.println("3 ve 4'e tam bölünen sayılar : ");
        //For döngüsü içerisinde 3 ve 4 e tam bölünebilen kontrolü
        for ( int i = 0; i<=k;i++){
            if ((i % 3 ==0) &&(i % 4 == 0) ){
                System.out.print(  i + " "); // 3 ve 4 e tam bölünen sayıları göstermek için
                toplam= toplam+i;
                sayiAdedi++;

            }
        }
        ortalama = toplam / sayiAdedi;
        if (sayiAdedi>0){
            System.out.println("\n3 ve 4'e bölünebilen sayıların ortalaması : " + ortalama);
        }else {
            System.out.println("Girdiğiniz sayıya kadar 3 ve 4'e bölünebilen sayı bulunamadı. ");
        }

    }
}