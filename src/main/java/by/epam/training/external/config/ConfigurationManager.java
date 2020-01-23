package by.epam.training.external.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Singleton.
 * The class allows read *.property files.
 */
public enum ConfigurationManager {
    INSTANCE;
    private final String localePropertyFile = "locale.locale";

    /**
     * Reads localized word from resource file and returns in as String.
     *
     * @param locale current user locale
     * @param key key-word that needs to be localized
     * @return localized word
     */
    public String getText(Locale locale, String key) {
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
        ResourceBundle localeBundle = ResourceBundle.getBundle(localePropertyFile, locale, new UTF8Control());
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
}
