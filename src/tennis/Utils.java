package tennis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    //trivia note: traditionally umpire moved the minute hand on a clock, hence 15,30 ,40(45), Game...They abbrviated 45 -> 40 because 45 is really long in french
    private static Map<Integer, Integer> points_to_clockface = new HashMap<>();
    static {
        points_to_clockface.put(0, 0);
        points_to_clockface.put(1, 15);
        points_to_clockface.put(2, 30);
        points_to_clockface.put(3, 40);
    }

    public static int tennisify(Integer integer) {
        return points_to_clockface.get(integer);
    }

    public static boolean isSetFinished(int a, int b) {
        int sum = a + b;
        return a == 7 || b == 7  //then it's 7-6 or 7-5
                || ((a == 6 || b == 6) && sum < 11); //if someone is on 6 and it's less than 6-5
    }

    public static boolean isTiebreaker(int a, int b) {
        return a == 6 &&  b == 6;
    }
}
