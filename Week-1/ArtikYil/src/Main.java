import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int yil,check1,check2,check3;

        System.out.println("Yıl giriniz:");
        yil = input.nextInt();
        check1 = yil % 4;
        check2 = yil % 100;
        check3 = yil % 400;


        if ((check1== 0 && check2 != 0) || check3 == 0) {
            System.out.println(yil + " bir artık yıldır.");
        } else {
            System.out.println(yil + " bir artık yıl değildir");
        }


    }
}