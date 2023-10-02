import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Çizdireceğiniz ters üçgenin uzunluğunu giriniz : ");
        int height = input.nextInt();
        for (int i = height ; i>=1 ; i--){
            for (int j = 1 ; j<= height-i ; j++){
                System.out.print(" ");
            }
            for (int k = 1; k<=(i*2)-1 ; k++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}