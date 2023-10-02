import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int nFac= 1 , rFac = 1,farkToplam = 1 ;
        int  n , r , kombinasyon , fark;
        System.out.println("C(n,r) formatında n değerini giriniz : ");
        n = input.nextInt();
        System.out.println("C(n,r) formatında r değerini giriniz : ");
        r = input.nextInt();
        if (n<r){
            System.out.println("Hatalı giriş yaptınız! ");
            System.exit(0);
        }
        fark = n-r;
        // Formülde bulunan n! formülü
        for (int i = 1 ; i <= n ; i++ ){
            nFac = i * nFac;
        }
        // Formülde bulunan r! formülü
        for (int i = 1 ; i <= r ; i++ ){
            rFac = i * rFac;
        }
        // Formülde bulunan (n-r)! formülü
        for (int i = 1 ; i <= fark ; i++ ){
            farkToplam = i * farkToplam;
        }
        // Formül
        kombinasyon = nFac/(rFac*farkToplam);
        System.out.println("C(" +n + "," + r+")" + "=" + kombinasyon );



    }
}