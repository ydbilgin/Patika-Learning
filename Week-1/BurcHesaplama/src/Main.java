import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int month, day;

        System.out.println("Doğduğunuz ayı giriniz :  ");
        month = input.nextInt();
        System.out.println("Doğduğunuz günü giriniz :  ");
        day = input.nextInt();
        if ((day<1 || day > 31)|| (month<1 || month>12) ){
            System.out.println("Hatalı giriş yaptınız");
            System.exit(0);
        }
        //double check
        if ((day >= 1 && day <= 31) && (month >= 1 && month <= 12)) {
            if (month == 1) {
                if (day <= 21) {
                    System.out.println("Burcunuz oğlak burcudur.");
                } else {
                    System.out.println("Burcunuz kova burcudur. ");

                }
            } else if (month == 2) {
                if (day <= 19) {
                    System.out.println("Burcunuz kova burcudur. ");
                } else if ((day >= 30)) {
                    System.out.println("Hatalı giriş yaptınız! ");
                    System.exit(0);
                } else {
                    System.out.println("Burcunuz balık burcudur. ");
                }


            } else if (month == 3) {
                if (day <= 20) {
                    System.out.println("Burcunuz balık burcudur. ");
                } else {
                    System.out.println("Burcunuz koç burcudur. ");
                }
            } else if (month == 4) {
                if (day <= 20) {
                    System.out.println("Burcunuz koç burcudur. ");
                } else {
                    System.out.println("Burcunuz boğa burcudur. ");
                }
            } else if (month == 5) {
                if (day <= 21) {
                    System.out.println("Burcunuz boğa burcudur. ");
                } else {
                    System.out.println("Burcunuz ikizler burcudur. ");
                }
            } else if (month == 6) {
                if (day <= 22) {
                    System.out.println("Burcunuz ikizler burcudur. ");
                } else {
                    System.out.println("Burcunuz yengeç burcudur. ");
                }
            } else if (month == 7) {
                if (day <= 22) {
                    System.out.println("Burcunuz yengeç burcudur. ");
                } else {
                    System.out.println("Burcunuz aslan burcudur. ");
                }
            } else if (month == 8) {
                if (day <= 22) {
                    System.out.println("Burcunuz aslan burcudur. ");
                } else {
                    System.out.println("Burcunuz başak burcudur. ");
                }
            } else if (month == 9) {
                if (day <= 22) {
                    System.out.println("Burcunuz başak burcudur. ");
                } else {
                    System.out.println("Burcunuz terazi burcudur. ");
                }
            } else if (month == 10) {
                if (day <= 22) {
                    System.out.println("Burcunuz terazi burcudur. ");
                } else {
                    System.out.println("Burcunuz akrep burcudur. ");
                }
            } else if (month == 11) {
                if (day <= 21) {
                    System.out.println("Burcunuz akrep burcudur. ");
                } else System.out.println("Burcunuz yay burcudur. ");
            } else if (month == 12) {
                if (day <= 21) {
                    System.out.println("Burcunuz yay burcudur. ");
                } else {
                    System.out.println("Burcunuz oğlak burcudur. ");
                }
            }

        }


    }
}