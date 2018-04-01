package edu.home.problem;
// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem3 {

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public int checkWinner(List<List<String>> codeList,
                           List<String> shoppingCart) {
        int winStatus = 1;

        int lastFruitCartIndex = -1;
        if( codeList != null && !codeList.isEmpty() && shoppingCart != null && !shoppingCart.isEmpty() ) {
            for (List<String> codes : codeList) {
                if (codes != null && !codes.isEmpty()) {
                    for (int i = 0; i < codes.size(); i++) {
                        String code = codes.get(i);
                        List<String> subCart = shoppingCart.subList(lastFruitCartIndex + 1, shoppingCart.size());
                        int fruitCartIndex = lastFruitCartIndex + 1 + subCart.indexOf(code);
                        if ((i == 0 && fruitCartIndex > lastFruitCartIndex)
                            || (i > 0 && fruitCartIndex == lastFruitCartIndex + 1)) {
                            lastFruitCartIndex = fruitCartIndex;
                        } else {
                            winStatus = 0;
                            break;
                        }
                    }
                    if (winStatus == 0) {
                        break;
                    }
                }
            }
        } else {
            winStatus = 0;
        }

        return winStatus;
    }

    // METHOD SIGNATURE ENDS


    public static void main(String[] args) {
        Problem3 problem = new Problem3();

        List<List<String>> codeList =
        Arrays.asList( Arrays.asList(), Arrays.asList("a", "a"), Arrays.asList("b", "o", "a"), Arrays.asList("b") );

        List<String> shoppingCart = Arrays.asList("o", "a", "a", "b", "o", "a", "b");

        System.out.println("" + problem.checkWinner(codeList, shoppingCart));
    }
}
