package model.compute;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Layout {

    public List<List<Integer>> returnMap() {
        final List<List<Integer>> output = new ArrayList<>();
//        output.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));

        output.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        output.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        output.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        output.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1));
        output.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0));
        output.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0));
        output.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0));
        output.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0));
        output.add(Arrays.asList(0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0));
        output.add(Arrays.asList(0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0));
        output.add(Arrays.asList(0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0));
        output.add(Arrays.asList(0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        output.add(Arrays.asList(0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        output.add(Arrays.asList(0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));

        return output;
    }
}
