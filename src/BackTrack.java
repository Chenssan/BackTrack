public class BackTrack {
    static int[][] matrix;//0
    static int[] AElement;//
    static int ACurr = 0;
    static int BestACurr;
    static int[] BElement;
    static int BCurr = 0;
    static int BestBCurr;
    static int[] ABlock;//0
    static int[] BBlock;//0
    static int[] BestAElement;
    static int[] BestBElement;
    static int[] Choose;//无用？1
    static int Number;//A,B和占列数
    static int BestNumber;
    static int n;

    public static void backtrack (int k) {
        if(k > n) {
            Number = ACurr + BCurr;
            if (Number > BestNumber) {
                BestNumber = Number;
                BestAElement = AElement.clone();
                BestBElement = BElement.clone();
                BestACurr = ACurr;
                BestBCurr = BCurr;
            }
            return;
        }
        if (AConstraint(k)) {
            Choose[k] = 1;
            AElement[ACurr++] = k;
            for (int i = 0; i < matrix[0].length; i++) {
                ABlock[i] += matrix[k - 1][i];
            }
            backtrack(k + 1);
            Choose[k] = 0;
            for (int i = 0; i < matrix[0].length; i++) {
                ABlock[i] -= matrix[k - 1][i];
            }
            AElement[--ACurr] = 0;
        }
        if (BConstraint(k)) {
            Choose[k] = 2;
            BElement[BCurr++] = k;
            for (int i = 0; i < matrix[0].length; i++) {
                BBlock[i] += matrix[k - 1][i];
            }
            backtrack(k + 1);
            Choose[k] = 0;
            for (int i = 0; i < matrix[0].length; i++) {
                BBlock[i] -= matrix[k - 1][i];
            }
            BElement[--BCurr] = 0;
        }
        if (Bounding(k)) {
            Choose[k] = 3;
            backtrack(k + 1);
            Choose[k] = 0;
        }

    }
    public static boolean AConstraint(int k) {
        if (k == n && BCurr == 0) {
            return false;
        }
        int flag = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[k - 1][i] == 1) {
                if (BBlock[i] != 0) {
                    flag = 1;
                    break;
                }
            }
        }
        if (flag == 0) {
            return true;
        }
        return false;
    }
    public static boolean BConstraint(int k) {
        if (k == n && ACurr == 0) {
            return false;
        }
        int flag = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[k - 1][i] == 1) {
                if (ABlock[i] != 0) {
                    flag = 1;
                    break;
                }
            }
        }
        if (flag == 0) {
            return true;
        }
        return false;
    }
    public static boolean Bounding(int k) {
        if (k == n && (ACurr ==0 || BCurr == 0)) {
            return false;
        }
        if (n - k + Number > BestNumber) {//=?
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        matrix = new int[4][7];
        matrix[1][0] = 1;
        matrix[3][0] = 1;
        matrix[2][3] = 1;
        matrix[3][3] = 1;
        matrix[1][5] = 1;
        matrix[0][6] = 1;
        AElement = new int[4];
        BestAElement = new int[4];
        BElement = new int[4];
        BestBElement = new int[4];
        ABlock = new int[7];
        BBlock = new int[7];
        Choose = new int[8];
        n = 4;
        backtrack(1);
        System.out.println(BestNumber);
        for (int i = 0; i < BestACurr; i++) {
            System.out.print(BestAElement[i]);
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i < BestBCurr; i++) {
            System.out.print(BestBElement[i]);
            System.out.print(" ");
        }


    }
}
