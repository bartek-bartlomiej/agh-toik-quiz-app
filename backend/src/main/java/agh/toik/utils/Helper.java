package agh.toik.utils;

import java.util.UUID;

public class Helper {

    public static Long generateID() {
        return UUID.randomUUID().getMostSignificantBits() & (Long.MAX_VALUE >> 22);
    }

}
