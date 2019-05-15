package com.hand13.sql.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @version $Revision$ $Date$
 * @author $Author$
*/
public class NFAstNode extends AstNode{
    String type;
    List<AstNode> children = new ArrayList<>();
}
