package org.perseo1326;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

//        final String URL = "https://info.cern.ch/hypertext/WWW/TheProject.html";
        // final String URL = "https://www.baeldung.com/htmlunit";
        final String URL = "https://www.devhut.net/vba-creating-an-html-document/";

        System.out.print("\033[H\033[2J");
        System.out.flush();

        try {
            WebScrapper webScrapper = new WebScrapper(URL);

            webScrapper.extractInformation();
            System.out.println("\n");

            webScrapper.findByElement();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}