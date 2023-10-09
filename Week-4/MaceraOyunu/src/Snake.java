import java.util.Random;

public class Snake extends Monster{

    public Snake() {
        super(4, "Yılan",randomDamage(), 12,25);
    }

    private static int randomDamage(){
        Random r = new Random();
        return r.nextInt(4) + 3;
    }



}
