/*
 * LinksList.java
 *
 * Created on 31.10.2012, 18:01:18
 */
package redmail.list;

import redmail.Log;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
/**
 *
 * @author alex
 */
public class LinksList {
    private List<Link2Go> lst;
    private Log log;
    
    private String LinksFilePath;
    private String LinksFileName;
    private String LinksFileItemSeparator;
    
    private int CurrentLinkNumber;
    
    private boolean CanPrev;
    private boolean CanCurr;
    private boolean CanNext;
    
    /**
     * Constuctor of LinksList class
     * Turning all Can**** variables to false
     */
    public LinksList(Log log){
        lst = new ArrayList<Link2Go>();
        this.log = log;
        CurrentLinkNumber = -1;
        CanPrev = false;
        CanCurr = false;
        CanNext = false; 
    }
    
    /**
     * Adding new link to list
     * @param Line String which contains link and interval
     * @param Separator Begin, end of line and item separator
     */
    public void LinkAdd(String Line,String Separator) {
        Parser parser = new Parser();
        parser.setItemSeparator(Separator);
        
        if (parser.canParse(Line)) {
            Link2Go link2go = parser.Parse(Line);
            lst.add(link2go);
        }
        else {
//            parse error - write down in the log
        }
    }
    
    /**
     * Adding new link to list
     * @param NewLink2Go Link2Go typed link
     */
    public void LinkAdd(Link2Go NewLink2Go) {
        lst.add(NewLink2Go);
    }
    
    /**
     * Checks ability to extract previous link
     * @return true if previous link can be ejected
     */
    public boolean canPrev() {
        if (CurrentLinkNumber < 1) {
            CanPrev = false;
        }
        else {
            CanPrev = true;
        }
        
        return CanPrev;
    }
    
    /**
     * Checks ability to extract current link
     * @return true if current link can be ejected
     */
    public boolean canCurr() {
        return CanCurr;
    }
    
    /**
     * Checks ability to extract next link
     * @return true if next link can be ejected
     */
    public boolean canNext() {
        return CanNext;
    }
    
    /**
     * Returns previous link in list (without range checking)
     * @return previous Link2Go link
     */
    public Link2Go Prev() {
        Link2Go PreviousLink = new Link2Go("google.com",15);
        
        CurrentLinkNumber--;
        PreviousLink = lst.get(CurrentLinkNumber);
        
        if (CurrentLinkNumber == 0) {
            CanPrev = false;
            if (lst.size() > 1) {
                CanNext = true;
            }
        }
        
        CanCurr = true;
        
        return PreviousLink;
    }
    
    /**
     * Returns current link in list (without range checking)
     * @return current Link2Go link
     */
    public Link2Go Curr() {
        Link2Go CurrentLink = new Link2Go("google.com",15);
        
        CurrentLink = lst.get(CurrentLinkNumber);
        
        return CurrentLink;
    }
    
    /**
     * Returns next link in list (without range checking)
     * @return next Link2Go link
     */
    public Link2Go Next() {
        Link2Go NextLink = new Link2Go("google.com",15);
        
        CurrentLinkNumber++;
        NextLink = lst.get(CurrentLinkNumber);
        
        if (CurrentLinkNumber == lst.size()-1) {
            CanNext = false;
        }
        
        CanCurr = true;
        
        return NextLink;
    }
    
    /**
     * Removing link 
     * @param Index index of removing link
     */
    public void RemoveLink_DUMMY(int Index) {
//        removing link
//        DUMMY
    }
    
    /**
     * Number of current link
     * @return number of current link in list
     */
    public int getCurrentLink() {
        return CurrentLinkNumber;
    }
    
    /**
     * Defines new number of current link
     * @param NewCurrent Number of new current link
     */
    public void setCurrentLink(int NewCurrent) {
        CurrentLinkNumber = NewCurrent;
    }
    
    /**
     * Count of links
     * @return number of links in list
     */
    public int getLinksCount() {
        return lst.size();
    }
    
    /**
     * Deleting all links in list
     */
    public void ClearList() {
        lst.clear();
        CurrentLinkNumber = -1;
        CanPrev = false;
        CanCurr = false;
        CanNext = false;
    }
    
    /**
     * Getting links from email and adding them to list
     * @return number of added links
     */
    public int GetFromEmail() {
        LinksEmail links_email = new LinksEmail();
        
        List LinksFromEmail = new ArrayList<Link2Go>();
        LinksFromEmail = links_email.GetLinks();
        
        int count = LinksFromEmail.size();
        lst.addAll(LinksFromEmail);
        
        if (count > 0) {
            CanNext = true;
        }
        
        return count;
    }
    
    /**
     * Sets path, name and item separator of file
     * @param NewLinksFilePath Path of file with links
     * @param NewLinksFileName Name of file with links
     * @param NewSeparator Begin, end of line and item separator
     */
    public void SetFileLocation(String NewLinksFilePath, String NewLinksFileName, String NewSeparator) {
        LinksFilePath = NewLinksFilePath;
        LinksFileName = NewLinksFileName;
        LinksFileItemSeparator = NewSeparator;
    }
    
    /**
     * Reading links from file and adding them to the list. It uses LinksFile class.
     * @return number of added links
     */
    public int ReadFromFile() {
        LinksFile links_file = new LinksFile(LinksFilePath, LinksFileName, this.log);
        links_file.setItemSeparator(LinksFileItemSeparator);
        
        List LinksFromFile = new ArrayList<Link2Go>();
        
        try {
            LinksFromFile = links_file.ReadLinks();
            int count = LinksFromFile.size();
            lst.addAll(LinksFromFile);
        
            if (count > 0) {
                CanNext = true;
            }
            log.Add(String.valueOf(count)+String.format("\tlinks read from file"));
            return count;
        }
        catch (IOException exp) {
            log.Add("Error while reading file.");
        }
        return -1;
    }
    
    /**
     * Writing links to filelinks to the list. It uses LinksFile class.
     */
    public void SaveToFile() {
        LinksFile links_file = new LinksFile(LinksFilePath, LinksFileName, this.log);
        links_file.setItemSeparator(LinksFileItemSeparator);
        try {
            links_file.WriteLinks(lst);
            log.Add(String.valueOf(this.getLinksCount())+String.format("\tlinks written to file"));
        }
        catch (IOException exc) {
            log.Add("Error while saving to file");
        }
    }
}