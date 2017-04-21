/*
 * RedMail.java
 *
 * Created on 19.01.2012, 22:11:49
 */
package redmail;

import redmail.list.LinksList;

import javax.swing.JLabel;
import javax.swing.JTextArea;
/**
 * Main class of program
 * @author alex
 */
public class RedMail {
    private Settings settings;
    private LinksList links_list;
    private Browser_DUMMY browser;
    private Log log;
    
    public RedMail() {
        settings = new Settings();
        settings.Load_DUMMY();
        log = new Log();
        links_list = new LinksList(log);
        links_list.SetFileLocation(settings.getLinksFilePath(),settings.getLinksFileName(),settings.getLinksFileItemSeparator());
        browser = new Browser_DUMMY();        
    }
    
    public void setLogPanel(JTextArea panel) {
        log.setPanel(panel);
    }
    
    public void setBrowserPanel(JLabel panel) {
        browser.setPanel(panel);
    }
    
    public void ViewNext() {
        if (links_list.canNext()) {
            browser.Load(links_list.Next());
        }
    }  
    
    public void ViewCurr() {
        if (links_list.canCurr()) {
            browser.Load(links_list.Curr());
        }
    }
    
    public void ViewPrev() {
        if (links_list.canPrev()) {
            browser.Load(links_list.Prev());
        }
    }
    
    public void ReadLinksFromFile() {
        links_list.ReadFromFile();
        System.out.print(links_list.getLinksCount());
        System.out.println("\tlinks read from file");
    }
    
    public void WriteLinksToFile() {
        links_list.SaveToFile();
    }
}
