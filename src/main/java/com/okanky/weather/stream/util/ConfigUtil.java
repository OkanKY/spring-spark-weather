package com.okanky.weather.stream.util;

import java.util.Optional;
import java.util.function.Supplier;

public class ConfigUtil {

    private ConfigUtil() {
    }

    /**
     * resolve error control
     *
     * @param resolver
     * @param <T>
     * @return
     */
    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
