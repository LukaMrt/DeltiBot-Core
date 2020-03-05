package fr.lukam.deltibot.core.infrastructure.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class YAMLParserUtils {

    public static Map<String, String> getParsedYaml(File file) throws IOException {

        JarFile jarFile = new JarFile(file);
        JarEntry entry = jarFile.getJarEntry("plugin.yml");

        Map<String, String> values = new HashMap<>();

        InputStream inputStream = jarFile.getInputStream(entry);

        int data;
        StringBuilder currentKey = new StringBuilder();
        StringBuilder currentValue = new StringBuilder();
        boolean key = true;
        int blank = 0;

        while ((data = inputStream.read()) != -1) {

            char letter = (char) (data);

            if (letter == ' ' || letter == '\r' || letter == '\n') {
                ++blank;

                continue;
            }

            if (blank == 1) {

                key = false;

            }

            if (blank == 2) {

                key = true;
                values.put(currentKey.toString().substring(0, currentKey.length() - 1), currentValue.toString());
                currentKey = new StringBuilder();
                currentValue = new StringBuilder();

            }

            blank = 0;

            if (key) {
                currentKey.append(letter);
            } else {
                currentValue.append(letter);
            }

        }

        values.put(currentKey.toString().substring(0, currentKey.length() - 1), currentValue.toString());

        return values;

    }

}
