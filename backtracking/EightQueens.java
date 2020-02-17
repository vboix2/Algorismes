package backtracking;

public class EightQueens {
    public static void main(String[] args) {

        int[] solution=new int[8];
        
        int i=0;
        while (i<=7 && i>=0){
            if (isRightPosition(i, solution)){
                i++;
                if (i>7){
                    break;
                }
                solution[i]=0;
            } else {
                solution[i]++;
                while (solution[i]>7){
                    i--;
                    solution[i]++;
                }
            }
        }
        printSolution(solution);
    }

    public static boolean isRightPosition(int i, int[] solution){
        for (int j=0;j<i;j++){
            //No queens in the same column
            if (solution[j]==solution[i]) {
                return false;
            }
            //No queens in diagonal
            if (solution[i]-solution[j]==i-j){
                return false;
            }
            if (solution[j]-solution[i]==i-j){
                return false;
            }
        }
        return true;
    }

    public static void printSolution(int[] solution){
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if (solution[i] == j){
                    System.out.print("* ");
                } else {
                    System.out.print("· ");
                }
            }
            System.out.println();
        }
    }
    
}
