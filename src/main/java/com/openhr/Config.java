package com.openhr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.Serializer;
import nu.xom.ValidityException;

import com.openhr.settings.Settings;

public class Config {

    /**
     * @param args
     */
    public static String employeeIdPattern = "";
    public static String employeePromotionStrategy = "";
    public static String payrollStrategy = "";

    public static void readConfig() {
        String property;
        String value;
        Builder builder = new Builder();
        Document doc;
        try {
            doc = builder.build(new Config().getSettingsXMLFile());
            Element root = doc.getRootElement();

            Elements elements = root.getChildElements();
            System.out.println("Size of the elements : " + elements.size());
            for (int index = 0; index < elements.size(); index++) {
                property = elements.get(index).getChild(1).getValue();
                value = elements.get(index).getChild(3).getValue();

                if (property.equalsIgnoreCase("id-pattern")) {
                    employeeIdPattern = value;
                }

                if (property.equalsIgnoreCase("promotion-strategy")) {
                    employeePromotionStrategy = value;
                }

                if (property.equalsIgnoreCase("payroll-strategy")) {
                    payrollStrategy = value;
                }

            }
        } catch (ValidityException e) {
            e.printStackTrace();
        } catch (ParsingException | IOException e) {
            e.printStackTrace();
        }
    }

    public File getSettingsXMLFile() {
        String settingsXMLFilePath = getClass().getResource("/openhr-setting.xml").getPath();
        System.out.println(settingsXMLFilePath);
        File settingsXML = new File(settingsXMLFilePath);
        return settingsXML;
    }

    public static void writeConfig(Settings settings) {
        Document doc;
        Element root = new Element("openhr-settings");
        Element setting, name, value;
        //Employee Id pattern
        setting = new Element("setting");

        name = new Element("name");
        name.appendChild("id-pattern");
        value = new Element("value");
        value.appendChild(settings.getIdPattern());
        //Employee Id pattern
        setting.appendChild(name);
        setting.appendChild(value);
        //Employee Id pattern
        root.appendChild(setting);

        //Employee promotion strategy
        setting = new Element("setting");
        name = new Element("name");
        name.appendChild("promotion-strategy");
        value = new Element("value");
        value.appendChild(settings.getPromotionStrategy());
        //
        setting.appendChild(name);
        setting.appendChild(value);
        //
        root.appendChild(setting);
        //Payrool strategy
        setting = new Element("setting");
        name = new Element("name");
        name.appendChild("payroll-strategy");
        value = new Element("value");
        value.appendChild(settings.getPayrollStrategy());
        //
        setting.appendChild(name);
        setting.appendChild(value);
        //
        root.appendChild(setting);

        doc = new Document(root);

        try {
            System.out.println(doc.toXML());
            Serializer serializer = new Serializer(new FileOutputStream(new Config().getSettingsXMLFile()), "ISO-8859-1");
            serializer.setIndent(4);
            serializer.setMaxLength(64);
            serializer.write(doc);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
