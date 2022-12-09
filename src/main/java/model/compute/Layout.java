package model.compute;

import utils.Pos;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Layout {

    final List<List<Integer>> map;

    public Layout() {
        map = new ArrayList<>();
        map.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        map.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        map.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        map.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1));
        map.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0));
        map.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0));
        map.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0));
        map.add(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0));
        map.add(Arrays.asList(0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0));
        map.add(Arrays.asList(0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0));
        map.add(Arrays.asList(0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0));
        map.add(Arrays.asList(0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        map.add(Arrays.asList(0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        map.add(Arrays.asList(0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
    }


    public List<List<Integer>> returnMap() {
        return this.map;
    }

    public boolean waterAtPos(Pos pos) {
        return this.map.get(pos.y/50).get(pos.x/50) == 1;
    }

}
