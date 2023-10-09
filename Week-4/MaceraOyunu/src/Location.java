import java.util.Scanner;

public abstract class Location {
    private Player player;
    private String name;
    private int idLoc;
    public static Scanner input = new Scanner(System.in);
    public Location(int idLoc,Player player , String name) {
        this.idLoc = idLoc;
        this.player = player;
        this.name = name;

    }
    public abstract boolean  onLocation();

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdLoc() {
        return idLoc;
    }

    public void setIdLoc(int idLoc) {
        this.idLoc = idLoc;
    }
}
