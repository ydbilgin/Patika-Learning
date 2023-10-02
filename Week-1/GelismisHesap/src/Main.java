import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static void toplama() {
        int number, result = 0, numberOfInput;
        Scanner input = new Scanner(System.in);
        System.out.println("Kaç sayıyı toplayacaksınız : ");
        numberOfInput = input.nextInt();
        for (int i = 1; i <= numberOfInput; i++) {
            System.out.println(i + ". sayıyı giriniz : ");
            number = input.nextInt();
            result += number;
        }
        System.out.println("Sonuç = " + result);
    }

    static void cikarma() {
        int number, result = 0, numberOfInput, number1;
        Scanner input = new Scanner(System.in);
        System.out.println("Çıkarılacak sayıyı giriniz : ");
        number1 = input.nextInt();
        System.out.println("Kaç sayı çıkaracaksınız : ");
        numberOfInput = input.nextInt();
        result = number1;
        for (int i = 1; i <= numberOfInput; i++) {
            System.out.println(i + ". çıkarılan sayıyı giriniz : ");
            number = input.nextInt();
            result -= number;
        }
        System.out.println("Sonuç = " + result);

    }

    static void carpma() {
        int number, result = 1, numberOfInput;
        Scanner input = new Scanner(System.in);
        System.out.println("Kaç sayı çarpacaksınız : ");
        numberOfInput = input.nextInt();
        for (int i = 1; i <= numberOfInput; i++) {
            System.out.println(i + ". çarpılan sayıyı giriniz : ");
            number = input.nextInt();
            result *= number;
        }
        System.out.println("Sonuç = " + result);

    }

    static void bolme() {
        double number, result = 0, numberOfInput, number1;
        Scanner input = new Scanner(System.in);
        System.out.println("Bölünecek sayıyı giriniz : ");
        number1 = input.nextInt();
        System.out.println("Kaç sayı böleceksiniz : ");
        numberOfInput = input.nextInt();
        result = number1;
        for (int i = 1; i <= numberOfInput; i++) {
            System.out.println(i + ". bölen sayıyı giriniz : ");
            number = input.nextInt();
            if (number == 0) {
                System.out.println("Bölen sayı 0 olamaz!");
                break;
            } else {
                result /= number;

            }

        }
        System.out.println("Sonuç = " + result);


    }

    static void uslu() {
        int n, r, result = 1;
        Scanner input = new Scanner(System.in);
        System.out.println("Taban değeri giriniz :  ");
        n = input.nextInt();
        System.out.println("Üs değeri giriniz :   ");
        r = input.nextInt();
        for (int i = 1; i <= r; i++) {
            result *= n;
        }
        System.out.println("Sonuç = " + result);


    }

    static void faktoriyel() {
        Scanner input = new Scanner(System.in);
        int number , result = 1;
        System.out.println("Sayı giriniz : ");
        number = input.nextInt();
        for (int i = 1 ; i <= number;i++){
            result *= i;
        }
        System.out.println("Sonuç = " + result);
    }
    static void mod(){
        int numberToMod , numberToGetMod , result;
        Scanner input = new Scanner(System.in);
        System.out.println("Bölünen sayı : ");
        numberToMod = input.nextInt();
        System.out.println("Bölen sayı : ");
        numberToGetMod = input.nextInt();
        result = numberToMod % numberToGetMod;
        System.out.println(numberToMod + "sayısının" + numberToGetMod + "sayısına bölümünden kalan = " + result);
    }
    static void alanCevre(){
        int en , boy , cevre , alan;
        Scanner input = new Scanner(System.in);
        System.out.println("Dikdörtgenin enini giriniz : ");
        en = input.nextInt();
        System.out.println("Dikdörtgenin boyunu giriniz : ");
        boy= input.nextInt();
        cevre = (en+boy)*2 ;
        alan = en*boy;
        System.out.println("Dikdörtgenin alanı = " + alan + "\nDikdörtgenin Çevresi = " + cevre);




    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int select;
        String menu = "1- Toplama İşlemi\n"
                + "2- Çıkarma İşlemi\n"
                + "3- Çarpma İşlemi\n"
                + "4- Bölme işlemi\n"
                + "5- Üslü Sayı Hesaplama\n"
                + "6- Faktoriyel Hesaplama\n"
                + "7- Kalan Hesaplama\n"
                + "8- Dikdörtgen Alan ve Çevre Hesabı\n"
                + "0- Çıkış Yap";
        System.out.println(menu);
        System.out.println("Lütfen bir işlem seçiniz : ");
        select = input.nextInt();
        switch (select){
            case 1 :
                toplama();
                break;
            case 2:
                cikarma();
                break;
            case 3:
                carpma();
                break;
            case 4:
                bolme();
                break;
            case 5:
                uslu();
                break;
            case 6:
                faktoriyel();
                break;
            case 7:
                mod();
                break;
            case 8:
                alanCevre();
                break;
            case 0:
                System.out.println("Çıkış yapılıyor!");
                break;
            default:
                System.out.println("Hatalı giriş yaptınız!");
        }
    }
}