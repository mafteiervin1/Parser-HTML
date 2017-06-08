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
public class Tag {
    public String name=new String();
    public int needClose;
    public int counter;
    public String needInName=new String();
    public Tag(String namee,int needClosee,String needIn)
    {
        this.name=namee;
        this.needClose=needClosee;
        this.counter=0;
        this.needInName=needIn;
    }
}
