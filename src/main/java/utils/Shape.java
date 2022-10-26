package utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Shape {
    ROUND,
    SQUARE,
    TRIANGLE,
    DIAMOND;


    private static final List<Shape> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    public static Shape random_shape()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
