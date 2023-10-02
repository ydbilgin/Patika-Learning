
public class Main {
    public static void main(String[] args) {
        int[][] A = {
                {1 , 2 , 3},
                {4 , 5 , 6 }
        };
        System.out.println("Orjinal Matris : ");
        for (int i= 0; i<A.length;i++){
            for (int j = 0; j< A[0].length;j++){
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
        int [][] aT = new int [A[0].length][A.length];
        for (int i = 0; i<A.length;i++){
            for (int j = 0 ; j<A[0].length; j++){
                 aT[j][i] = A[i][j];

            }
        }
        System.out.println("Transpozu : ");
        for (int i= 0; i<aT.length;i++){
            for (int j = 0; j< aT[0].length;j++){
                System.out.print(aT[i][j] + " ");
            }
            System.out.println();
        }





    }
}