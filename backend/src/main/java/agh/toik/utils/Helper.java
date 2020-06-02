package agh.toik.utils;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Helper {

    public static Long generateID() {
        return UUID.randomUUID().getMostSignificantBits() & (Long.MAX_VALUE >> 22);
    }

    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static String getRandomElementOfList(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

}
