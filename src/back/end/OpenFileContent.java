/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.end;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ervin
 */
public class OpenFileContent {  
    public ArrayList<String> fileContent;
    public OpenFileContent(File ContentToParse)//construieste o lista de stringuri cu continutul unui fisier
    {
        this.fileContent = new ArrayList<String>();
        try {
            String line;
            BufferedReader in;
            in = new BufferedReader(new FileReader(ContentToParse));
            line = in.readLine();
            while(line != null)
            {
                fileContent.add(line);
                line = in.readLine();
            }
           
        } catch (IOException ex) {
            Logger.getLogger(ParserHtml.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void listAllContent()//afiseaza tot continutul unui fisier
    {
        int i;
        for(i=0;i<this.fileContent.size();i++)
            System.out.println(this.fileContent.get(i));
    }
    
}
