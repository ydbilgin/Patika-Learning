import java.util.Random;

public class Match {
    Fighter f1;
    Fighter f2;
    int maxWeight;
    int minWeight;


    Match(Fighter f1, Fighter f2, int minWeight, int maxWeight) {
        this.f1 = f1;
        this.f2 = f2;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    Fighter whoStart(int a) {
        if (a == 0) {
            return this.f1;
        } else {
            return this.f2;
        }

    }

    void run() {
        if (isCheck()) {
            while (this.f1.health >= 0 && this.f2.health >= 0) {
                Random r = new Random();
                int a = r.nextInt(2);
                whoStart(a);
                if (whoStart(a) == this.f1) {
                    f1.health = f2.hit(f1);
                    if (f1.health > 0) {
                        System.out.println(f1.name + " oyuncusunun kalan canı " + f1.health);
                    } else {
                        System.out.println(f2.name + " maçı kazandı!");
                        break;
                    }


                } else {
                    f1.health = f2.hit(f1);
                    if (f1.health > 0) {
                        System.out.println(f1.name + " oyuncusunun kalan canı " + f1.health);
                    } else {
                        System.out.println(f2.name + " maçı kazandı!");
                        break;
                    }


                }
            }
        } else {
            System.out.println("Sporcuların sikletleri farklı. Maç iptal!");
        }
    }

    boolean isCheck() {
        return ((this.f1.weight >= minWeight && this.f1.weight <= maxWeight) && (this.f2.weight >= minWeight && this.f2.weight <= maxWeight));
    }


}
