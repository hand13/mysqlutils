package com.hand13.sql.utils;

import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.io.InputStream;

/**
 * @version $Revision$ $Date$
 * @author $Author$
*/
public class Lexer {
    TokenType type;
    String value;
    private InputStream in;
    int look;

    public Lexer(@NotNull InputStream in) {
        this.in = in;
    }

    public void nextChar(){
        int c;
        try{
            c = in.read();
        }catch (IOException ioe) {
            System.out.println("end of file\n");
            c = -1;
        }
        look = c;
    }

    private boolean isEnd(int c) {
        return c==-1;
    }

    public void slipSpace(){
        nextChar();
        while (isSpace(look) && !isEnd(look)){
            nextChar();
        }
    }

    private boolean isSpace(int c) {
        return c == ' ' || c == '\t' || c == '\n';
    }


    public void getToken(){
        StringBuilder str = new StringBuilder();
        while (look != -1 && !isSpace(look)) {
            str.append((char)look);
            nextChar();
        }
        value = str.toString();
        String pv = value.toLowerCase();
        switch (pv) {
            case "":
                type = null;
                break;
            case "select":
                type = TokenType.SELECT;
                break;
            case "insert":
                type = TokenType.INSERT;
                break;
            case "update":
                type = TokenType.UPDATE;
            case "delete":
                type = TokenType.DELETE;
                break;
            case "*":
                type = TokenType.WILDCARD;
                break;
            case "from":
                type = TokenType.FROM;
                break;
            default:
                type = TokenType.OBJECT;
        }
    }

}
