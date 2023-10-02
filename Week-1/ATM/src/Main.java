import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int tryCount = 3, balance = 1500;
        String userName, password, correctUser = "patika", correctPassword = "dev";
        Scanner inp = new Scanner(System.in);
        boolean notFinished = true;

        while (tryCount > 0 && notFinished) {
            System.out.println("Lütfen kullanıcı adınızı giriniz : ");
            userName = inp.nextLine();
            System.out.println("Lütfen şifrenizi giriniz : ");
            password = inp.nextLine();
            if ((userName.equals(correctUser)) && (password.equals(correctPassword))) {
                System.out.println("Başarıyla giriş yaptınız.");
                System.out.println("Yapmak istediğiniz işlemi seçiniz : ");
                System.out.println("1- Para yatır\n2-Para çek\n3-Bakiye sorgula\n4-Çıkış yap");
                int islem = inp.nextInt();
                switch (islem) {
                    case 1:
                        System.out.println("Yatırmak istediğiniz tutarı giriniz : ");
                        int paraYatir = inp.nextInt();
                        balance += paraYatir;
                        System.out.println(paraYatir + " TL yatırma işlemi başarıyla gerçekleşti.");
                        System.out.println("Yeni bakiyeniz : " + balance);
                        notFinished = false;
                        break;
                    case 2:
                        System.out.println("Çekmek istediğiniz tutarı giriniz : ");
                        int paraCek = inp.nextInt();
                        while (balance < paraCek) {
                            System.out.println("Hatalı giriş! Çekeceğiniz tutar bakiyenizden yüksek olamaz.");
                            System.out.println("Çekmek istediğiniz tutarı giriniz : ");
                            paraCek = inp.nextInt();

                        }
                        balance -= paraCek;
                        System.out.println(paraCek + " TL para çekme işleminiz başarıyla gerçekleşti.");
                        System.out.println("Kalan bakiyeniz : " + balance);
                        notFinished = false;
                        break;
                    case 3:
                        System.out.println("Bakiyeniz : " + balance);
                        break;
                    case 4:
                        System.out.println("Çıkış yapılıyor. İyi günler dileriz .");
                        notFinished = false;
                        break;
                    default:
                        System.out.println("Hatalı giriş yaptınız!");
                        notFinished = false;



                }
            } else {
                tryCount--;
                if (tryCount == 0) {
                    System.out.println("Hakkınız kalmamıştır! Lütfen bankayla iletişime geçiniz.");


                } else {
                    System.out.println("Hatalı kullanıcı adı veya şifre! Lütfen tekrar deneyiniz");
                    System.out.println("Kalan hakkınınız : " + tryCount);
                }


            }

        }

    }
}