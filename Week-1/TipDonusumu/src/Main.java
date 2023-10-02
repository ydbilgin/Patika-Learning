import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Lütfen bir tam sayı giriniz : ");
        int tamSayi = input.nextInt();
        System.out.println("Lütfen bir ondalıklı sayı giriniz : ");
        double ondalikliSayi = input.nextDouble();
        System.out.println("Tam sayının ondalıklı sayıya dönüşümü : " +(double) tamSayi);
        System.out.println("Ondalıklı sayının tam sayıya dönüşünü : " +(int) ondalikliSayi);


    }
}