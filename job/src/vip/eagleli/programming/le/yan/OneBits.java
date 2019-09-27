package vip.eagleli.programming.le.yan;

import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Sides;


class Result {

    /*
     * Complete the 'getOneBits' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER n as parameter.
     */

    public static List<Integer> getOneBits(int n) {
    // Write your code here
        List<Integer> list = new ArrayList<>();
        int index = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                list.add(index);
            }
            n >>>= 1;
            index++;
        }
        int l = list.get(list.size() - 1);
        List<Integer> res = new ArrayList<>();
        res.add(list.size());
        for (int i = list.size() - 1; i > -1; i--) {
            res.add(l - list.get(i) + 1);
        }
        return res;
    }

}

public class OneBits {
    public static void main(String[] args) throws IOException {
       System.out.println(Result.getOneBits(161));
    }
}

