package co.edu.uptc.save_content_url.services;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class readURL {
    URL url;

    public void setURL(URL url) {
        this.url = url;
    }

    public List<String> readContentURL() throws IOException {
        URL urlMunicipios = new URL("https://es.wikipedia.org/wiki/Wikiproyecto:Colombia/Listado_de_municipios");
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlMunicipios.openStream()));
        List<String> list = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();
        return list;
    }

    public List<String> extracStrings() throws IOException {
        List<String> list = readContentURL();
        List<String> listMunicipios = new ArrayList<>();
        int i = 0;
        for (String string : list) {
            if (i > 487 && i < 1592+19 && i != 1592) {
                int empieza = 0;
                for (int k = 0; k < string.length() - 5; k++) {
                    String temp = string.substring(k, k + 5);
                    if (temp.equals("title")) {
                        break;
                    }
                    empieza++;
                }
                int termina = 0;
                for (int j = empieza + 7; j < string.length(); j++) {
                    String temp1 = string.substring(j, j + 1);
                    if (temp1.equals("(") || temp1.equals("\"")) {
                        break;
                    }
                    termina++;
                }
                String municipio = string.substring(empieza + 7, (((empieza + 7) + termina)));
                //System.out.println(string.substring(empieza + 7, (((empieza + 7) + termina))));
                listMunicipios.add(municipio);
            }
            i++;
        }
        return listMunicipios;
    }

    public void createFile (List<String> listMunicipios) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("contenido.txt"));
        for (String string : listMunicipios) {
            writer.write(string + "\n");
        }
        writer.close();
    }
}