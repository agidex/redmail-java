/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * Browser_DUMMY.java
 *
 * Created on 23.01.2013, 19:31:51
 */
package redmail;

import redmail.list.Link2Go;

import javax.swing.JLabel;
/**
 *
 * @author alex
 */
public class Browser_DUMMY {
    private JLabel Panel;
    
    public Browser_DUMMY() {
        
    }
    
    public void setPanel(JLabel panel) {
        Panel = panel;
    }
    
    public void Load(Link2Go link2go) {
        String link = link2go.getLink();
        int interval = link2go.getInterval();
        
        Panel.setText("Link: "+link+"   Interval: "+Integer.toString(interval));
    }
    
}
