package org.perseo1326;
import java.io.Console;
import java.io.IOException;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

public class Main {

    private static String readUserInput(String message){

        Console console = System.console();
        String str = console.readLine(message);
        if(str.equals("")){
            System.out.println("Debe ingresar un " + message);
            readUserInput(message);
        }
        return str;
    }

    
    private static Page loginProcess(HtmlElement htmlElement, String userId, String pass) throws IOException{

            HtmlElement loginForm = htmlElement.getOneHtmlElementByAttribute("form", "name", "bsUserForm");

            // System.out.println(loginForm);

            HtmlElement inputUserId = loginForm.getOneHtmlElementByAttribute("input", "id", "UserId");
            inputUserId.setAttribute("value", userId);

            // System.out.println(inputUserId);

            HtmlElement inputPass = loginForm.getOneHtmlElementByAttribute("input", "id", "UserPassword");
            inputPass.setAttribute("value", pass);

            // System.out.println(inputPass);

            HtmlElement inputSubmit = loginForm.getOneHtmlElementByAttribute("input", "name", "loginButton");
            return inputSubmit.click();

    }

    public static void main(String[] args) {

        // final String URL = "https://www.baeldung.com/htmlunit";
        // final String URL = "https://www.devhut.net/vba-creating-an-html-document/";
        String userId = "";
        String pass = ""; 
        final String LOGIN_URL = "http://servidor.com/dispatch/";

        System.out.print("\033[H\033[2J");
        System.out.flush();

        try {

            userId = readUserInput("Nombre de usuario: ");
            pass = readUserInput("Contraseña: ");
            WebScrapper webScrapper = new WebScrapper(LOGIN_URL);

            Page pagina = loginProcess(webScrapper.getPage().getBody(), userId, pass);
            System.out.println("GET WEB RESPONSE: \n" + pagina.getWebResponse().getContentAsString());
            // System.out.println(pagina.toString());


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FailingHttpStatusCodeException e){
            System.out.println(e.getLocalizedMessage());
        } catch (ElementNotFoundException e) {
            System.out.println("EXCEPCION: Elemento NO encontrado...");
            System.out.println(e.getMessage() + "\n");
        }


    }
}