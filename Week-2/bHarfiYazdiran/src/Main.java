
public class Main {
    public static void main(String[] args) {
        //i = 0  i=3 i = 6  J = 2 i3/0 i 6/0
        String [][] letter = new String [7][4];

        for (int i = 0 ; i< letter.length;i++){
            for (int j = 0 ; j<letter[0].length;j++){
                if (i==0 || i ==3 || i ==6){
                    if (j!=3){
                        letter[i][j] = "* ";
                    }else{
                        letter[i][j] = " ";
                    }

                }else if ( j == 0 ||j ==3){
                    letter[i][j] = "* ";
                }else {
                    letter[i][j] = "  ";
                }

            }
        }

        for ( String[] row : letter){
            for (String col : row){
                System.out.print(col);
            }
            System.out.println();
        }
    }
}