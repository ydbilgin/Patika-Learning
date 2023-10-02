
import java.util.Scanner;
import java.lang.String;

public class Main {

    static boolean isPalindrom(int number){

        //aldığım sayıyı stringe çevirdim
        String str1 = Integer.toString(number);
        String reverse = "";
        //For içinde stringdeki char sayısını bulup indexini sondan başlayarak reverse stringine yazdırdım. Böylece girilen sayının son char'ı reverse stringinin ilk indexi olacak ve tam tersi çıkacak.
        for (int i = str1.length()-1 ; i>=0 ;i--){
            reverse += str1.charAt(i);
        }
        //tersine çevirdikten sonra tekrardan integera çevirdim
        int check = Integer.parseInt(reverse);
        //eşit olup olmadığını kontrol ettim
        if (check==number)
            return true;
        else
            return false;


    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Kontrol etmek istediğiniz sayıyı giriniz : ");
        int number = input.nextInt();


        System.out.println(isPalindrom(number));

    }
}