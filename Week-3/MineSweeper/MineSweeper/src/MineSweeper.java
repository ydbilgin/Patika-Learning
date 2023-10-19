import java.util.Random;
import java.util.Scanner;


public class MineSweeper { //DEĞERLENDİRME FORMU 5
    int rowNumber;
    int colNumber;
    String[][] gameBoard;
    String[][] board;
    Scanner input = new Scanner(System.in);
    boolean isGameOver = false;
    int checkRow;
    int checkCol;
    int count = 0;
    boolean isWin = false;


    int coordinateMines = 0;

    //Kurucu metot
    MineSweeper(int rowNumber, int colNumber) {

        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.gameBoard = new String[rowNumber][colNumber];
        this.board = new String[rowNumber][colNumber];

        //oyunu başlattığımız metodu çağırdık.
        gameStart();


    }


    //board oluşturma metodu; Tüm indexler "-" olan board yaratıyoruz bu metotla
    public void createBoard(String[][] create) {
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                create[i][j] = " - ";
            }

        }
    }


    //boarda mayın yerleştirme metodu; board büyüklüğünün 4'te 1'i kadar mayın olması istendi.
    //random sınıfıyla satır ve sütun sayısına kadar rastgele sayı üretip ikisini mayın eklemek istediğimiz 2D arrayin indexleri olarak aldık.
    //Eğer bu indexte "-" varsa "*" ile değiştirerek mayın sayısını 1 azalttık. mayın sayısı 0 olana kadar bu işlem devam etti.
    public void placingMines(String[][] placing) {
        int mineCount = (rowNumber * colNumber) / 4; //DEĞERLENDİRME FORMU 8
        if (mineCount < 1) {
            mineCount = 1;
        }
        while (mineCount > 0) {
            Random r = new Random();
            int colRandom = r.nextInt(colNumber);
            int rowRandom = r.nextInt(rowNumber);
            if (placing[rowRandom][colRandom] == " - ") {
                placing[rowRandom][colRandom] = " * ";
                mineCount--;


            }
        }
    }

    //2D arrayi ekrana yazdırmak için kullandığımız metot.
    public void printBoard(String[][] print) {
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                System.out.print(print[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Kullanıcıdan alınan değerlerin koordinatlarının 8 tarafının kontrol edildiği metot. Eğer herhangi bir yerinde mayın görürsek coordinateMines sayısını 1 artırıyoruz.
    public void checkCoordinates() {
        int mineCount = (rowNumber * colNumber) / 4;
        if (mineCount < 1) {
            mineCount = 1;
        }
        if (board[checkRow][checkCol] != " * ") { //DEĞERLENDİRME FORMU 10
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (((checkRow + i >= 0) && (checkRow + i <= (this.rowNumber - 1))) && ((checkCol + j >= 0) && (checkCol + j <= this.colNumber - 1))) {
                        if (this.board[checkRow + i][checkCol + j] == " * ") {
                            coordinateMines++;
                            this.gameBoard[checkRow][checkCol] = " " + coordinateMines + " "; //DEĞERLENDİRME FORMU 11
                        }//hiç bulamamışsak 0 yazdırıyoruz.
                        if (coordinateMines == 0) {
                            this.gameBoard[checkRow][checkCol] = " 0 "; //DEĞERLENDİRME FORMU 12
                        }
                    } else
                        continue; // oyun alanı dışına denk gelen indexler için continue ile işlem yapmadan devam ediyoruz
                }
            }
            //count 0'dan başlıyor bu bizim mayın harici yer sayımız
            count++;
            // eğer count sayısı oynanabilir alan sayısına ulaşırsa isWin booleanı true'ya dönüyor ve gameWin metodunu çağırıyoruz.
            if (count == ((rowNumber * colNumber) - mineCount)) { //DEĞERLENDİRME FORMU 14
                isWin = true;
                gameWin();
            } else { //eğer count sayısı henüz ulaşmamışsa güncellediğimiz gameBoard'umuzu yazdırıp coordinateMines'ı 0lıyoruz. Döngüye devam ediyor.
                printBoard(gameBoard); //DEĞERLENDİRME FORMU 11-12
                coordinateMines = 0;
            }

        } else { //Mayına basmışsak isGameOver true oluyor ve gameOver metoduna giriyor
            isGameOver = true; //DEĞERLENDİRME FORMU 13
            gameOver();
        }
    }

    //Oyunu kazanma durumu
    public void gameWin() { //DEĞERLENDİRME FORMU 6
        if (isWin) {
            for (int i = 0; i < rowNumber; i++) {
                for (int j = 0; j < colNumber; j++) {
                    if (board[i][j].equals(" * ")) { //datada tuttuğumuz mayınları aşağıdaki satırda oyuncuyla etkileşimde olduğumuz 2d arrayin içine attım.
                        gameBoard[i][j] = " * ";
                    }
                }
            }
            System.out.println("Kazandın!"); //DEĞERLENDİRME FORMU 15
            printBoard(gameBoard);
            isGameOver = true;

        }
    }

    //Oyunu başlattığımız metot.Bu metodun içerisinde diğer metotları çağırardık.
    public void gameStart() { //DEĞERLENDİRME FORMU 6
        createBoard(board); //board isimli 2D arrayime mapin boyutu kadar her indexine - atadım.
        createBoard(gameBoard); //gameBoard isimli 2D arrayime mapin boyutu kadar her indexine - atadım.Bu benim kullanıcıyla etkileşime geçecek olan mapim.
        placingMines(board); //Data tuttuğum mapimin içerisine mayınlar eklediğim metod.
        System.out.println("=======Mayınların Konumu=====");
        printBoard(board); // 2D arrayi yazdırma metoduyla mapin mayınlı halini yazdırdım.
        System.out.println("===== Oyun başladı ====");
        printBoard(gameBoard); //İlk başta oyuncuyla etkileşime geçtiğim board mayınsız gözüküyor ve ekrana full "-" şeklinde çıkıyor.

        while (!isGameOver) { //Oyun hala devam ediyorsa kullanıcıdan oyunu oynamak için satır ve sütun sayısı istiyorum.
            System.out.println("Satır giriniz(1-" + rowNumber + " arasında) : "); //DEĞERLENDİRME FORMU 9
            while (!input.hasNextInt()) { //girilen değerin int olup olmadığını kontrol ediyorum.
                System.out.println("Geçerli bir tamsayı giriniz: "); //DEĞERLENDİRME FORMU 9
                System.out.println("Satır giriniz(1-" + rowNumber + " arasında) : ");
                input.next(); // Geçersiz girişi oku ve atla
            }
            this.checkRow = input.nextInt() - 1;

            System.out.println("Sütun giriniz(1-" + colNumber + " arasında) : ");
            while (!input.hasNextInt()) { //girilen değerin int olup olmadığını kontrol ediyorum.
                System.out.println("Geçerli bir tamsayı giriniz: ");
                System.out.println("Sütun giriniz(1-" + colNumber + " arasında) : ");
                input.next(); // Geçersiz girişi oku ve atla
            }
            this.checkCol = input.nextInt() - 1;

            if (gameBoard[checkRow][checkCol] == " - ") {
                checkCoordinates(); // Eğer girdiğimiz indexe daha önce sayı atanmamışsa koordinat kontrol metodunu çağırıyoruz.
            } else {
                System.out.println("Zaten daha önce o koordinatı girdiniz!");
            }
            // Kullanıcı girişlerini doğrulama: Eğer boyuttan büyük veya 0'dan küçük sayı girerse tekrar sorduruyorum.
            if (checkRow < 0 || checkRow >= rowNumber || checkCol < 0 || checkCol >= colNumber) {
                System.out.println("Geçersiz satır veya sütun girdiniz. Lütfen geçerli bir giriş yapın.");
                continue; // Geçersiz girişleri tekrar almak için döngüyü devam ettiriyor
            }
        }
    }

    //Oyun kaybetme durumu
    public void gameOver() { //DEĞERLENDİRME FORMU 6
        //eğer oyun bittiyse koordinatları girdiğimiz
        if (isGameOver) {
            for (int i = 0; i < rowNumber; i++) {
                for (int j = 0; j < colNumber; j++) {
                    if (board[i][j].equals(" * ")) { // datada tuttuğumuz mayınları aşağıdaki satırda oyuncuyla etkileşimde olduğumuz 2d arrayin içine attım.
                        gameBoard[i][j] = " * ";
                    }
                }
            }
            System.out.println("Kaybettin!"); //DEĞERLENDİRME FORMU 15
            printBoard(gameBoard);

        }
    }


}
