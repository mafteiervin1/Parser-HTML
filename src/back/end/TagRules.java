/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.end;

/**
 *
 * @author Ervin
 */
public class TagRules {
    String pattern;
    int isClosed;
    public TagRules(String p)
    {
        this.pattern=p;
        this.isClosed=0;
    }
}
