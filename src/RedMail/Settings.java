/*
 * Settings.java
 *
 * Created on 20.01.2013, 19:32:11
 */
package redmail;

import redmail.list.EmailBoxSettings;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author alex
 */
public class Settings {
    private String LinksFilePath;
    private String LinksFileName;
    private String LinksFileItemSeparator;
    private String SettingsFilePath;
    private String SettingsFileName;
    
    private boolean AutoView;
    
    private List<EmailBoxSettings> email_list;
        
    public Settings() {
        email_list = new ArrayList<EmailBoxSettings>();
    }
    
    public void Load_DUMMY() {
        LinksFilePath = "/home/alex/NetBeansProjects/RedMail/dist/";
        LinksFileName= "links.txt";
        LinksFileItemSeparator = "|";
        AutoView = false;
    }
    
    public void Reload() {
//        DUMMY
    }
    
    public void Save() {
//        DUMMY
    }
    
    public String getLinksFilePath() {
        return LinksFilePath;
    }
    
    public String getLinksFileName() {
        return LinksFileName;
    }
    
    public String getLinksFileItemSeparator() {
        return LinksFileItemSeparator;
    }
}
