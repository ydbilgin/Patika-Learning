import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        ArrayList<Integer>fibonacci = new ArrayList<Integer>();
        System.out.println("Fibonacci serisinin eleman sayısını giriniz : ");
        int  n1 = 0,n2 = 1 , n3 ;
        int sayi = inp.nextInt();
        for (int i = 0 ; i<sayi ;i++){
            n3 = n1 + n2; //fibonacci serisi anlatımındaki n1 en soldaki , n2 ortadaki toplam ve n3 sonucu temsil ediyor.
            fibonacci.add(n1);
            n1 = n2;
            n2  = n3;

        }
        System.out.println(fibonacci);
    }
}