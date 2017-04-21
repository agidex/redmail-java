/*
 * Log.java
 *
 * Created on 24.01.2013, 15:17:17
 */
package redmail;

import javax.swing.JTextArea;
/**
 *
 * @author alex
 */
public class Log {
    private JTextArea Panel;
        
    public Log() {
        
    }

    public void setPanel(JTextArea Panel) {
        this.Panel = Panel;
    }
    
    public void Add(String Message) {
        Panel.append(Message);
        Panel.append(String.format("\n"));
    }
    
}
