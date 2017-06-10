package edu.home.problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Problem2 {

    public String[][] matchLunches( String[][] lunchMenuPairs, String[][] teamCuisinePreferences ) {

        int responseSize = 0;
        Map<String, List<String>> memberLunchOptions = new HashMap<>();
        for( int i=0; i < teamCuisinePreferences.length; i++ ) {
            String member = teamCuisinePreferences[i][0];
            String prefCuisine = teamCuisinePreferences[i][1];

            List menuOptions = new ArrayList();
            for( int j=0; j < lunchMenuPairs.length; j++ ) {
                String cuisine = lunchMenuPairs[j][0];
                String dish = lunchMenuPairs[j][1];
                if( prefCuisine.equals("*") || prefCuisine.equals(cuisine) ) {
                    menuOptions.add(dish);
                }
            }
            memberLunchOptions.put(member, menuOptions);
            responseSize += menuOptions.size();
        }

        String[][] teamLunchOptions = new String[responseSize][2];

        AtomicInteger i = new AtomicInteger(0);
        memberLunchOptions.forEach( (member, menuOptions) -> {
            menuOptions.forEach( lunchOption -> {
                teamLunchOptions[i.get()][0] = member;
                teamLunchOptions[i.get()][1] = lunchOption;
                i.incrementAndGet();
            } );
        } );

        return teamLunchOptions;
    }

    public static void main(String[] args) {
        Problem2 problem2 = new Problem2();
        String[][] lunchMenuPairs = new String[1][2];
        lunchMenuPairs[0][0] = "c1";
        lunchMenuPairs[0][1] = "d1";
        String[][] teamCuisinePreferences = new String[1][2];
        teamCuisinePreferences[0][0] = "m1";
        teamCuisinePreferences[0][1] = "c1";
        String[][] teamLunchOptions = problem2.matchLunches(lunchMenuPairs, teamCuisinePreferences);
        for (int i = 0; i < teamLunchOptions.length; i++) {
            System.out.println("[ " + teamLunchOptions[i][0] + ", " + teamLunchOptions[i][1] + " ]");
        }
    }
}
