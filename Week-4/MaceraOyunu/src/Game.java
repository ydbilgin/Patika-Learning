import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void gameStart() {
        Location selectedLocation = null;
        System.out.println("Macera oyununa hoşgeldiniz !");
        System.out.print("Lütfen bir isim giriniz : ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getName() + " Hoşgeldiniz! ");
        player.selectChar();
        while (true){
            boolean select = true;
            while (select) {
                player.printInfo();
                Location[] locList = {new SafeHouse(player), new Toolstore(player) , new Cave(player) , new Forest(player), new River(player) , new Mine(player)};
                for (Location location : locList) {
                    System.out.println("ID : " + location.getIdLoc() + "\t Lokasyon : " + location.getName());
                }
                System.out.println("0 - Çıkış Yap --> Oyunu sonlandır.");
                System.out.println("----------------");
                System.out.println();
                System.out.print("Lütfen bir lokasyon seçiniz: ");
                System.out.println();
                int selectLoc = input.nextInt();
                switch (selectLoc) {
                    case 0:
                        selectedLocation = null;
                        select = false;
                        break;
                    case 1:
                        selectedLocation = new SafeHouse(player);
                        select = false;
                        break;
                    case 2:
                        selectedLocation = new Toolstore(player);
                        select = false;
                        break;
                    case 3:
                        selectedLocation = new Cave(player);
                        select=false;
                        break;
                    case 4:
                        selectedLocation = new Forest(player);
                        select=false;
                        break;
                    case 5:
                        selectedLocation= new River(player);
                        select=false;
                        break;
                    case 6:
                        selectedLocation = new Mine(player);
                        select=false;
                        break;
                    default:
                        System.out.println("Geçersiz seçim.");
                        select = true;
                        break;
                }


            }
            if (selectedLocation == null) {
                System.out.println("Görüşmek üzere!");
                break;
            }
            if (selectedLocation.onLocation()) {
            } else {
                System.out.println("GAME OVER!");
                break;
            }

        }




    }

}

