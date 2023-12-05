package org.perseo1326;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import java.io.IOException;
import java.util.List;

public class WebScrapper {


    private WebClient webClient = new WebClient();
    private HtmlPage page;


    public WebScrapper(String url) throws IOException {
        this.page = getWebPage(url);
    }

    private HtmlPage getWebPage(String url) throws IOException {

        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        return webClient.getPage(url);
    }

    public void extractInformation() throws IOException{
        
        HtmlForm searchForm = null;
        HtmlInput submitButton;

        List<HtmlForm> items = this.page.getByXPath("//form");

        for( HtmlForm element : items ) {
            if(element.getAttribute("role").equals("search")){
                System.out.println(element.toString());
                searchForm = element; 
            }
        }
                
        HtmlTextInput textNode = searchForm.getInputByName("s");
        textNode.type("HTML");

        System.out.println(textNode.toString());

        submitButton = searchForm.getInputByValue("Search");
        this.page = submitButton.click();
    }

    public void findByElement() {

        DomNodeList<DomElement> articles = this.page.getElementsByTagName("article");

        for( DomElement article : articles) {

            List<DomElement> elements = article.getByXPath("header/h1");

            // System.out.println("ELEMENTOS DEL ARTICLE: ");
            for(DomElement h1 : elements ){

                System.out.println(h1.getTextContent() );
            }

        }

        // for( DomNode nodo : nodosDom ){
        //     System.out.println(nodo.toString());
        // }


    }
}
