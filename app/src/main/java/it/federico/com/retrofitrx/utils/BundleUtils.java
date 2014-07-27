package it.federico.com.retrofitrx.utils;

public final class BundleUtils {

    /**
     * Make a component specific extra name
     *
     * @param clazz the class.
     * @param name the extra key.
     * @return an extra key
     */
    public static String makeExtraKey(final Class<?> clazz, final String name) {
        return clazz.getCanonicalName() + "." + name;
    }
}
