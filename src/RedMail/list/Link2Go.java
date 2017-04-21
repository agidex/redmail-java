/*
 * LinksList.java
 *
 * Created on 31.10.2012, 18:01:18
 */
package redmail.list;
/**
 *
 * @author alex
 */
public class Link2Go {
    private String Link;
    private int Interval;
    
    /**
     * Creates new Link2Go object (with clean fields)
     */
    public Link2Go() {
        Link = "";
        Interval = 0;
    }
    
    /**
     * Creates new Link2Go object with fields
     * @param NewLink Link
     * @param NewInterval Interval
     */
    public Link2Go(String NewLink,int NewInterval) {
        Link = NewLink;
        Interval = NewInterval;
    }
    
    /**
     * Gets link
     * @return String link
     */
    public String getLink() {
        return Link;
    }

    /**
     * Gets interval
     * @return view interval
     */
    public int getInterval() {
        return Interval;
    }
}