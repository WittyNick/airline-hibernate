package by.epam.training.external.locale;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Singleton.
 * The class allows read *.property files.
 */
public enum LocaleManager {
    INSTANCE;
    private final UTF8Control utf8Control = new UTF8Control();

    /**
     * Reads localized word from resource file and returns in as String.
     *
     * @param locale current user locale
     * @param key key-word that needs to be localized
     * @return localized word
     */
    public String getString(Locale locale, String key) {
        return getTextMap(locale, key).get(key);
    }

    /**
     * Reads localized words from resource file and returns Mat<String, String>
     *     where key is key-word, value is localized word.
     *
     * @param locale locale of current user
     * @param keys array of key-words that needs to be localized
     * @return Map of localized words
     */
    public Map<String, String> getTextMap(Locale locale, String... keys) {
        String localePropertyFile = "locale.locale";
        ResourceBundle localeBundle = ResourceBundle.getBundle(localePropertyFile, locale, utf8Control);
        Map<String, String> map = new HashMap<>();
        for (String key : keys) {
            String text = "";
            if (localeBundle.containsKey(key)) {
                text = localeBundle.getString(key);
            }
            map.put(key, text);
        }
        return map;
    }

    /**
     * Solve problem with ResourceBundle property files encoding.
     * Set encoding UTF-8 instead of default in Windows ISO-8859-1.
     */
    private static class UTF8Control extends ResourceBundle.Control {
        @Override
        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IOException {
            // The below is copy of the default implementation.
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");
            ResourceBundle bundle = null;
            InputStream stream = null;
            if (reload) {
                URL url = loader.getResource(resourceName);
                if (url != null) {
                    URLConnection connection = url.openConnection();
                    if (connection != null) {
                        connection.setUseCaches(false);
                        stream = connection.getInputStream();
                    }
                }
            } else {
                stream = loader.getResourceAsStream(resourceName);
            }
            if (stream != null) {
                try {
                    // Only this line is changed to make it to read properties files as UTF-8.
                    bundle = new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
                } finally {
                    stream.close();
                }
            }
            return bundle;
        }
    }
}
