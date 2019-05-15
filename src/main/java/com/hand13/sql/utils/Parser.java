package com.hand13.sql.utils;

import java.io.InputStream;

/**
 * @version $Revision$ $Date$
 * @author $Author$
*/
public class Parser {
    private Lexer lexer;

    public Parser(InputStream in){
        lexer = new Lexer(in);
        init();
    }
    private FAstNode match(TokenType type,TokenType expected) {
        if(type != expected) {
            throw new RuntimeException("parse failed \n");
        }
        FAstNode node = new FAstNode();
        node.element = new Element();
        node.element.tokenType = type;
        node.element.value = lexer.value;
        return node;
    }

    private void init(){
        lexer.nextChar();
    }

    private void next(){
        lexer.slipSpace();
        lexer.getToken();
    }
    public AstNode statement(){
        AstNode node = null;
        if(lexer.look == 's') {
            node = select();
        }
        return node;
    }

    private static void addNode(NFAstNode parent,AstNode child) {
        parent.children.add(child);
    }

    private AstNode select(){
        NFAstNode nfAstNode = new NFAstNode();
        lexer.getToken();
        addNode(nfAstNode,match(lexer.type,TokenType.SELECT));
        next();
        addNode(nfAstNode,match(lexer.type,TokenType.WILDCARD));
        next();
        addNode(nfAstNode,match(lexer.type,TokenType.FROM));
        next();
        addNode(nfAstNode,match(lexer.type,TokenType.OBJECT));
        return nfAstNode;
    }
}
