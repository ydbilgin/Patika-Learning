import java.util.ArrayList;

import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Kaç adet sayılı bir dizi istiyorsunuz? Giriniz :   ");
        int numberOfArray = input.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        // kaç adet sayı girilecekse bunları bi list arrayinin içine atıyorum.
        for (int i = 0; i < numberOfArray; i++) {
            System.out.println((i + 1) + " . Elemanı : ");
            int valueOfIndex = input.nextInt();
            list.add(valueOfIndex);
        }
        System.out.println("Hangi sayıyı kontrol etmek istiyorsunuz? Giriniz : ");
        int checkNumber = input.nextInt();
        Collections.sort(list);
        ArrayList<Integer> negativeCheck = new ArrayList<>();
        ArrayList<Integer> positiveCheck = new ArrayList<>();
        for (int i : list){
            //5ten büyük sayıları bi arraylistin içine attım
            if (checkNumber-i <= 0 ){
                positiveCheck.add(i);
                //5ten küçük sayıları bir arraylistin içine attım.
            }else{
                negativeCheck.add(i);
            }

        }
        //iki listeyi de küçükten büyüğe sıraladım.
        Collections.sort(negativeCheck);
        Collections.sort(positiveCheck);
        //pozitif olanların 0. indexi işime yarıyor size almama gerek yok ama negatiflerin en büyüğü lazım olacğaından sizeını alıp print ederken en son indexini yazdırdım.
        int size=negativeCheck.size();
        System.out.println("Girilen sayıdan küçük en yakın sayı : " + negativeCheck.get(size-1) );
        System.out.println("Girilen sayıdan büyük en yakın sayı : " + positiveCheck.get(0));


    }
}