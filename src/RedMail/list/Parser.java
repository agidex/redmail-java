/*
 * Parser.java
 *
 * Created on 31.10.2012, 18:01:18
 */
package redmail.list;

/**
 *
 * @author alex
 */
public class Parser {
    String Separator;
    int SeparatorsCount;
    
    public Parser(String NewSeparator) {
        Separator = NewSeparator;
        SeparatorsCount = 3;
    }
    
    public Parser() {
        SeparatorsCount = 3;
    }
    
    /**
     * Sets item separator
     * @param NewItemSeparator New item separator
     */
    public void setItemSeparator(String NewItemSeparator) {
        Separator = NewItemSeparator;
    }
    
    /**
     * Gets item separator
     * @return String item separator
     */
    public String getItemSeparator() {
        return Separator;
    }
    
    public int getItemSeparatorsCount() {
        return SeparatorsCount;
    }
    
    /**
     * Checks Line to be parsed
     * @param Line Line to check
     * @return true if Line can be parsed
     */
    public boolean canParse(String Line) {
        if (countOccurrences(Line,this.Separator) == SeparatorsCount){
            return true;
        }
        return false;
//        put some regexp here
    }
    
    /**
     * Parsing Line to Link2Go object
     * @param Line Line to parse
     * @return Link2Go representation of Link
     */
    public Link2Go Parse(String Line) {
        
        String[] lst = Line.split("\\"+Separator);
        
        String link = lst[1];
        int interval = Integer.parseInt(lst[2]);
                        
        Link2Go link2go = new Link2Go(link,interval);
        
        return link2go;
    }
    
    /**
     * Packs Link2Go object to String
     * @param link2go Link2Go object to pack
     * @return String representstion of Link2Go object
     */
    public String Pack(Link2Go link2go) {
        String PackedLink = Separator + link2go.getLink() + Separator 
                + String.valueOf(link2go.getInterval()) + Separator;
        
        return PackedLink;
    }
    
    public int countOccurrences(String Line, String Example) {
        int count = 0;
        int Position = -Example.length();
        
        if (Example.length() == 0){
            return 0;
        }
        
        while ((Position = Line.indexOf(Example, Position + Example.length())) != -1){
            count++;
        }
        
        return count;
    }
}