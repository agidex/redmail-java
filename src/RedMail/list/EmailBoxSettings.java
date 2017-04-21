/*
 * EmailBoxSettings.java
 *
 * Created on 23.01.2013, 16:58:41
 */
package redmail.list;

/**
 *
 * @author alex
 */
public class EmailBoxSettings {
    private String host;        //pop.mail.ru
    private int port;           //110
    private String type;        //pop3 imap
    private String login;       //emailforspam@gmail.com
    private String pass;        //********
    
    /**
     * Creates EmailBoxSettings object
     * @param NewHost Host (pop.mail.ru)
     * @param NewPort Port (110)
     * @param NewType (pop3)
     * @param NewLogin (asdgdfgeff)
     * @param NewPass (********)
     */
    public EmailBoxSettings(String NewHost, int NewPort, String NewType, String NewLogin, String NewPass) {
        host = NewHost;
        port = NewPort;
        type = NewType;
        login = NewLogin;
        pass = NewPass;
    }
    
    /**
     * Host
     * @return host
     */
    public String Host() {
        return host;
    }
    
    /**
     * Port
     * @return port
     */
    public int Port() {
        return port;
    }
    
    /**
     * Type
     * @return type
     */
    public String Type() {
        return type;
    }
    
    /**
     * Login
     * @return login
     */
    public String Login() {
        return login;
    }
    
    /**
     * Password
     * @return password
     */
    public String Password() {
        return pass;
    }
}
