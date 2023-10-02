import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Lütfen a,b,c sayılarını sırasıyla giriniz.");
        int a = input.nextInt();
        int b = input.nextInt();
        int c = input.nextInt();
        System.out.println("Yapmak istediğiniz işlemi seçiniz :\n1- (a+b)*(c-b)\n2- a+(b*c)-b\n3- (a+b)*c-b");
        int kosul = input.nextInt();
        switch (kosul){
            case 1:
                int deger = (a+b)*(c-b);
                System.out.println("(a+b)*(c-b) = " + deger);
                break;
            case 2:
                deger = a+(b*c)-b;
                System.out.println("a+(b*c)-b = " + deger);
                break;
            case 3:
                deger = (a+b)*c-b;
                System.out.println("(a+b)*c-b = " + deger);
                break;
            default:
                System.out.println("Hatalı tuşlama yaptınız!");
        }
    }
}