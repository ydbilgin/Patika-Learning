

public class Main {
    public static void main(String[] args) {
        Fighter f1 = new Fighter("Ahmet",10,122,100,10);
        Fighter f2 = new Fighter("Mehmet",20,83,85,25);

        Match match = new Match(f1,f2, 85,100);
        match.run();
    }
}