package edu.home.problem;


import java.util.ArrayList;
import java.util.List;

public class Problem1 {

    public List<Integer> getAnagramIndices(String haystack, String needle ) {
        List<Integer> anagramIndices = new ArrayList<Integer>();

        int needleLength = needle.length();
        int haystackLength = haystack.length();

        for( int i = 0; i < haystackLength - needleLength; i++ ) {
            String haystackSub = haystack.substring(i, i + needleLength);
            if( areAnangrams(needle, haystackSub) ) {
                anagramIndices.add(i);
            }
        }

        return anagramIndices;
    }

    private boolean areAnangrams(String str1, String str2) {

        StringBuffer str2buff = new StringBuffer(str2);
        str2buff.indexOf("");
        if( str1.length() == str2.length()
            && str1.chars().sum() == str2.chars().sum() ) {
            str1.chars().forEach( ch -> {
                String s = Character.toString((char)ch);
                int i = str2buff.indexOf(s);
                if(  i > -1 ) {
                    str2buff.deleteCharAt(i);
                }
            } );
        }
        return str2buff.length() == 0;
    }


    public static void main(String[] args) {
        Problem1 problem1 = new Problem1();
        List<Integer> anagaramIndices = problem1.getAnagramIndices("bbbababaaabbbb", "ab" );
        System.out.println("anagaramIndices = " + anagaramIndices);
    }
}
