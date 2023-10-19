import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //Kullanıcadan nasıl bir board büyüklüğünde oyun oynamak istediğinin input'unu alıyoruz
        int rowNumber = 0;
        int colNumber = 0;
        ////DEĞERLENDİRME FORMU 7
        System.out.println("Lütfen satır sayısı giriniz: ");
        while (!input.hasNextInt()) { //girilen değerin integer olup olmadığını kontrol ediyorum.
            System.out.println("Geçerli bir tamsayı giriniz: ");
            System.out.println("Lütfen satır sayısı giriniz: ");
            input.next(); // Geçersiz girişi oku ve atla
        }
        rowNumber = input.nextInt();

        System.out.println("Lütfen sütun sayısı giriniz: ");
        while (!input.hasNextInt()) { //girilen değerin integer olup olmadığını kontrol ediyorum.
            System.out.println("Geçerli bir tamsayı giriniz: ");
            System.out.println("Lütfen sütun sayısı giriniz: ");
            input.next(); // Geçersiz girişi oku ve atla
        }
        colNumber = input.nextInt();


        MineSweeper mine = new MineSweeper(rowNumber, colNumber);

    }
}