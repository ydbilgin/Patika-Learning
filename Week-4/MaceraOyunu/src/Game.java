cdimport java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    public void gameStart(){
        System.out.println("Macera oyununa hoşgeldiniz !");
        System.out.println("Lütfen bir isim giriniz : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getName() + " Hoşgeldiniz! ");
        player.selectChar();
    }
}
