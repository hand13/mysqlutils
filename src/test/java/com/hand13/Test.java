package com.hand13;

import com.hand13.sql.utils.AstNode;
import com.hand13.sql.utils.Parser;

import java.io.StringBufferInputStream;

/**
 * @version $Revision$ $Date$
 * @author $Author$
*/
public class Test {
    public static void main(String[] args) {
        Parser parser = new Parser(new StringBufferInputStream("select * from wc"));
        AstNode node = parser.statement();
        System.out.println(node.toString());
    }
}
