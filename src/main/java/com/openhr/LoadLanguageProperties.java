package com.openhr;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class LoadLanguageProperties {

    private Properties configProp = new Properties();
    public static final String OPENHR_EN = "Lang.properties";
    public static final String OPENHR_AM = "Lang_am.properties";
    public static final String OPENHR_OM = "Lang_om.properties";

    private String langToLoad;
    private Set<String> propertyKeys;

    public void loadProps() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("com/openhr/lang/" + langToLoad);

        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getConfigProp() {
        return configProp;
    }

    public void setConfigProp(Properties configProp) {
        this.configProp = configProp;
    }

    public String getLangToLoad() {
        return langToLoad;
    }

    public void setLangToLoad(String langToLoad) {
        this.langToLoad = langToLoad;
    }

    public Set<String> getPropertyKeys() {
        return propertyKeys;
    }

    public void setPropertyKeys(Set<String> propertyKeys) {
        this.propertyKeys = propertyKeys;
    }

}
