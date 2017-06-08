/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.end;

import java.io.File;

/**
 *
 * @author Ervin
 */
public class OpenFolder {

    OpenFolder(File FF) {
        
    }
    //functia asta verifica daca un folder are doar fisiere .html sau .css ,
    //daca nu au extensia asta le sterge.putem folosi asta ca metoda de siguranta.
    public void CheckForExtension(File folder)
    {
        File[] listOfFiles = folder.listFiles();
        String name = new String();
        for (int i = 0; i < listOfFiles.length; i++) {
            name=listOfFiles[i].getName();
            if(!(name.endsWith(".html"))&&!(name.endsWith(".css")))listOfFiles[i].delete();
        }
    }
       
    //returneaza numele ultimului fisier uploadat
    public String getLastFileUploadedName(File folder)
    {
        File[] listOfFiles = folder.listFiles();
        //String name = listOfFiles[listOfFiles.length-1].getName();
        return listOfFiles[listOfFiles.length-1].getName();  
    }
    
    //returneaza ultimul fisier uploadat
    public File getLastFileUploaded(File folder)
    {
        File[] listOfFiles = folder.listFiles();
        //String name = listOfFiles[listOfFiles.length-1].getName();
        return listOfFiles[listOfFiles.length-2];
        
    }
    
}
