import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
       int number = rand.nextInt(100);
       int right=0;
       int selected;
       ArrayList<Integer> wrong = new ArrayList<Integer>();
       boolean isWin = false;

       while (right<5){
           System.out.println("Lütfen tahmininizi giriniz: ");
           selected = input.nextInt();
           if (selected<0 || selected>99){
               System.out.println("Lütfen 0-100 arasında bir değer giriniz : ");
               continue;
           }

           if (number==selected){
               System.out.println("Tebrikler doğru tahmin! Tahmin ettiğin sayı : " + number);
                isWin=true;
               break;
           }else {
               System.out.println("Yanlış tahmin!");
               if (selected>number){
                   System.out.println(selected + " sayısı gizli sayıdan büyüktür.");
               }else {
                   System.out.println(selected + " sayısı gizli sayıdan küçüktür.");
               }


           }
           right++;
           wrong.add(selected);
           System.out.println("Kalan hakkın : " + (5-right));
       }
       if (isWin){
           System.out.println("Kaybettiniz ! ");
       }
        System.out.println("Gizli sayı : " + number);
        System.out.println(wrong);




    }
}