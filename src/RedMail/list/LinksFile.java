/*
 * Settings.java
 *
 * Created on 20.01.2013, 19:32:11
 */
package redmail.list;

import redmail.Log;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author alex
 */
public class LinksFile {
    private String FilePath;
    private String FileName;
    private String ItemSeparator;
    private Log log;
    
    public LinksFile(String NewFilePath, String NewFileName, Log NewLog) {
        FilePath = NewFilePath;
        FileName = NewFileName;
        log = NewLog;
    }
    
    /**
     * Set new item separator
     * @param NewItemSeparator  
     */
    public void setItemSeparator(String NewItemSeparator) {
        ItemSeparator = NewItemSeparator;
    }
    
    /**
     * Reading links from file. ItemSeparator is reqiured
     * @return lst - list of Link2Go objects
     * @throws IOException 
     */
    public List<Link2Go> ReadLinks() throws IOException { 
        List list = new ArrayList<Link2Go>();
        File FileWithLinks = new File(FilePath,FileName);
        try {
            BufferedReader input = new BufferedReader(new FileReader(FileWithLinks));
            String line;
            
            Parser parser = new Parser(ItemSeparator);
            
            while ((line = input.readLine()) != null) {
                if (parser.canParse(line)) {
                    Link2Go link2go = parser.Parse(line);
                    list.add(link2go);
                }
            }
            input.close();
        }
        catch (IOException ioexception) {
//            log.Add("Error while reading file.");
            throw new IOException();
        }
        return list;
    }
    
    /**
     * Writes links to file
     * @param ListToWrite list of Link2Go objects to write
     */
    public void WriteLinks(List<Link2Go> ListToWrite) throws IOException {
        File file = new File(this.FilePath,this.FileName);
        
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            int i;
            Parser parser = new Parser("|");
            String line;
            
            for(i = 0;i < ListToWrite.size();i++){
                line = parser.Pack(ListToWrite.get(i));
                output.write(line);
                output.newLine();
            }
            output.flush();
            output.close();
        }
        catch(IOException exception) {
            throw new IOException();
//            log.Add("Error while saving to file");
        }
    }
    
    /**
     * Cleaning file (not delete)
     */
    public void ClearFile() throws IOException {
        File file = new File(this.FilePath,this.FileName);
        
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            
            output.write("");
            output.newLine();
            
            output.flush();
            output.close();
        }
        catch(IOException exception) {
            //            log.Add("Error while clearing to file");
            throw new IOException();
        }
    }
}
