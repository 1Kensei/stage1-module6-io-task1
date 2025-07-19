package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String name;
        String age;
        String email;
        String phone;

        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream inputStream = new FileInputStream(file)) {
            int b;
            while ((b = inputStream.read()) != -1) {
                stringBuilder.append((char) b);
            }
            String[] vals = stringBuilder.toString().trim().split("\r?\n");
            name = vals.length > 0 ? vals[0].substring(vals[0].indexOf(':') + 1).trim() : "";
            age = vals.length > 1 ? vals[1].substring(vals[1].indexOf(':') + 1).trim() : "";
            email = vals.length > 2 ? vals[2].substring(vals[2].indexOf(':') + 1).trim() : "";
            phone = vals.length > 3 ? vals[3].substring(vals[3].indexOf(':') + 1).trim() : "";

            return new Profile(name, Integer.parseInt(age), email, Long.parseLong(phone));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
