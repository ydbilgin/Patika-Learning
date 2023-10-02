import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int  n = 0 , r = 0 , toplam = 1 ;
        System.out.println("n^r formatında n değerini giriniz : ");
        n = input.nextInt();
        System.out.println("n^r formatında r değerini giriniz : ");
        r = input.nextInt();
        for (int i= 1 ; i<=r ; i++ ){
            toplam *= n;
        }
        System.out.println("Sonuç : " + toplam);



    }
}