package edu.home.problem;
// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
import java.util.*;

public class Problem4 {

    static class Pos {
        int row, col;

        Pos( int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int levelField(int numRows, int numColumns, List<List<Integer>> field)
    {
        Map<Integer, Pos> trees = getTrees(field);

        Pos fromPos = new Pos(0, 0);
        int minSteps = 0;
        for( Pos toPos : trees.values() ) {
            minSteps += getMinSteps(fromPos, toPos, field);
            fromPos = toPos;
            if( minSteps < 0 ) {
                break;
            }
        }
        return minSteps;
    }

    private int getMinSteps(Pos fromPos, Pos toPos, List<List<Integer>> field) {
        int minSteps = -1;



        return minSteps;
    }

    private Map<Integer,Pos> getTrees(List<List<Integer>> field) {
        Map<Integer, Pos> trees = new TreeMap<>();

        for (int row = 0; row < field.size(); row++) {
            List<Integer> fieldRow = field.get(row);
            for (int col = 0; col < fieldRow.size(); col++) {
                int height = fieldRow.get(row);
                if(height > 1) {
                    trees.put(height, new Pos(row, col));
                }
            }
        }
        return trees;
    }
    // METHOD SIGNATURE ENDS


    public static void main(String[] args) {
        Problem4 problem = new Problem4();

        List<List<String>> codeList =
        Arrays.asList( Arrays.asList("o"), Arrays.asList("a", "a"), Arrays.asList("b", "o", "a"), Arrays.asList("b") );

        List<String> shoppingCart = Arrays.asList("o", "a", "a", "b", "o", "a", "b");

//        System.out.println("" + problem.levelField(codeList, shoppingCart));
    }
}
