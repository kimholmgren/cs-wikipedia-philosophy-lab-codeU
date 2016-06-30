package com.flatironschool.javacs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.jsoup.select.Elements;

public class WikiPhilosophy {
	
	final static WikiFetcher wf = WikiFetcher.getInstance();
	
	/**
	 * Tests a conjecture about Wikipedia and Philosophy.
	 * 
	 * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
	 * 
	 * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     *    that does not exist, or when a loop occurs
	 * 
	 * @param args
	 * @throws IOException
	 */

 private static void countToPhilosophy(String url, List<String> prevurls) throws IOException {
    if(url=="https://en.wikipedia.org/wiki/Philosophy") {
      System.out.println("SUCCESS! Total links: " + prevurls.size());
      return;
    } else {
      System.out.println("Got to line 36");
      //get the first paragraph of the url
      Elements paragraphs = wf.fetchWikipedia(url);
      Element firstPara = paragraphs.get(0);
      //get first link out of the paragraph
      //need to add the rules
        Iterable<Node> iter = new WikiNodeIterable(firstPara);
        Elements links = firstPara.getElementsByTag("a");
        if(links==null) {
          System.out.println("FAILURE: Reached a page with no links");
          return;
        }
        Element firstlink = links.first();
        String link = firstlink.attr("href");
        System.out.println(link);
        String newurl = "https://en.wikipedia.org" + link;
        if(prevurls.contains(newurl)) {
          System.out.println("FAILURE: Reached an endless loop");
        }
        System.out.println(newurl);
        //recursively call with new url and new list of previous urls
        prevurls.add(url);
        countToPhilosophy(newurl, prevurls);
        return;
     }

  }
	public static void main(String[] args) throws IOException {
    System.out.println("Got to inside the method");
    String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
    System.out.println("Got to line 64");
    List<String> prevurls = new ArrayList<String>();
    System.out.println("Got to line 66");
    countToPhilosophy(url, prevurls);


    //might be better w a for loop but leaving this for now

    /*String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
    Elements paragraphs = wf.fetchWikipedia(url);

    Element firstPara = paragraphs.get(0);
    
    Iterable<Node> iter = new WikiNodeIterable(firstPara);*/
    /*for(Node node: iter) {
        //System.out.println("NEW NODE:" + node); 
        if(node.hasAttr("href")) {
          String link = node.attr("href");
          if(link=="wiki/philosophy") {
            ctr++;
            System.out.println(ctr);
            return;
          }
          String newurl = "https://en.wikipedia.org" + link;
          System.out.println(newurl);
          paragraphs = wf.fetchWikipedia(newurl);
          firstPara = paragraphs.get(0);
          iter = new WikiNodeIterable(firstPara);
          ctr++;
         System.out.println(ctr);
        } else {
          //System.out.println("NOT A URL");
        }*/
        /*Element firstlink = node.first();
        String link = firstlink.attr("href");
        System.out.println("link");
        String newurl = "https://en.wikipedia.org" + link;
        System.out.println("newurl"); */

    

    
   /* Elements links = firstPara.getElementsByTag("a");
    Element firstlink = links.first();
    System.out.println(firstlink.attr("href")); */


        
      



//within the paragraph look for a href=wiki/...

      //takes a URL for a Wikipedia page, downloads, parses

      // traverse resulting DOM tree to find first valid link
          //valid means it's in the content, not sidebar or boxout
          //not in italics or parentheses
          //skip external links, links to current page, red links
          //skip if text starts with uppercase letter


          //2 types of nodes - textnode and element. element need to typecast to access the tag and other info
          //when you find an element that has a link, check if it's in italics by following parent links up the tree. i or em tag in the parent chain means link is in italics
          //to check if link is in parentheses scan through text as you traverse the tree and eep track of opening and closing parentheses (and keep track of nested parentheses)



      //if page has no links or have seen first link, program indicates failure and exits

      //if linke matches URL of philosophy page, program indicates success and exits

      //otherwise it goes back to step 1

        //java to philosophy should take 7 links

	}
}
