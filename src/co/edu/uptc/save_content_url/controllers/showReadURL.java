package co.edu.uptc.save_content_url.controllers;

import java.io.IOException;

import co.edu.uptc.save_content_url.services.readURL;

public class showReadURL {
    public void showReadURL () {
        readURL readURL = new readURL();
        try {
            readURL.readContentURL();
            readURL.extracStrings();
            readURL.createFile(readURL.extracStrings());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
