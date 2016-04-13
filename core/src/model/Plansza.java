package model;

import java.io.*;
import java.util.ArrayList;

public class Plansza {
    public ArrayList <Blok> listaObiektow;
    public ArrayList listaPociskow;

    public Plansza(String path) {
        listaObiektow = new ArrayList<Blok>();
        listaPociskow = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader("plansza.txt"));
            br.read();
            for (int i = 0; i < 32; i++) {
                String linia = br.readLine();
                for (int j = 0; j < 32; j++) {
                    char znak = linia.charAt(j * 2);
                    switch (znak) {
                        case 'C': {
                            this.listaObiektow.add(new Cegly(j, i));
                            break;
                        }
                        case 'K': {
                            this.listaObiektow.add(new Kamien(j, i));
                            break;
                        }
                        case 'Z': {
                            this.listaObiektow.add(new Zarosla(j, i));
                            break;
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Nie znaleziono pliku!");
        } catch (IOException ex) {
            ex.getMessage();
        }

    }

}
