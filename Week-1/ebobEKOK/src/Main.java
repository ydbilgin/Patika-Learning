import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> n1ebob = new ArrayList<Integer>();
        ArrayList<Integer> n2ebob = new ArrayList<Integer>();
        ArrayList<Integer> ortak = new ArrayList<Integer>();
        System.out.println("İlk sayıyı giriniz : ");
        int n1 = input.nextInt();
        System.out.println("İkinci sayıyı giriniz : ");
        int n2 = input.nextInt();
        int i = 1;
        int j = 1;
        int ekok;
        while (i <= n1) {
            if (n1 % i == 0) {
                n1ebob.add(i);
            }
            i++;
        }
        while (j <= n2) {
            if (n2 % j == 0) {
                n2ebob.add(j);
            }
            j++;
        }
        for(Integer s : n1ebob ){
            if (n2ebob.contains(s)){
                ortak.add(s);
            }
        }
        //ortak array listesinin sizeını buluyorum
        int ortakSize = ortak.size();
        //küçükten büyüğe bir array çıkardığı için en sonuncu index en büyük indeximiz. en sonuncu index ise size - 1 ile bulunuyor.
        int ebob = ortak.get(ortakSize-1);
        ekok = (n1*n2)/ebob;

        System.out.println("Bu iki sayının ebob değeri : " + ebob);
        System.out.println("Bu iki sayının ekok değeri : " + ekok);


    }
}
