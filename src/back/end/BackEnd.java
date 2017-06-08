/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.end;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ervin
 */
public class BackEnd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        File F=new File("F:\\Facultate\\TW\\Proiect\\1"); 
        //File FF=new File("C:\\Users\\Ervin\\Desktop\\asd.txt");
        OpenFolder a=new OpenFolder(F);
        File FFF=a.getLastFileUploaded(F);
        OpenFileContent d=new OpenFileContent(FFF);
        d.listAllContent();
        //d.listAllContent();
        //System.out.println(a.getLastFileUploadedName(F));
        //OpenFolder test=new OpenFolder(F);
        //test.CheckForExtension(F);
    /*for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        System.out.println("File " + listOfFiles[i].getName());
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
      }
    }*/
    //System.out.println(listOfFiles[listOfFiles.length-1]);
    //String s="123-45-6789";
    /*if (s.matches("^(\\d{3}-?\\d{2}-?\\d{4})$")) {
				System.out.println("Found good SSN: " + s);}*/
        //ParserHtml parser=new ParserHtml(d.fileContent);
        //parser.listRules();
       //parser.countOpenTags();
       //parser.checkRules();
       int i;
       //for(i=0;i<parser.Tags.size();i++)
          //System.out.println(parser.Tags.get(i).name.substring(0, parser.Tags.get(i).name.length()-1));
        //System.out.println(parser.tok(parser.TagsRules.get(i).pattern));
       /* String s="<a>";
        String ss="<img asd asdasd";
        System.out.println((s.substring(1, s.length()-1)));*/
       
       /*String input = "<img src=\"smiley.gif\" alt=\"Smiley face\" height=\"42\" width=\"42\">";
       int ok=0;
		Pattern p = Pattern.compile("()"+"src=|alt=|height=|width=");
                Pattern pp = Pattern.compile("(<img).*(>)");
		Matcher m = pp.matcher(input);

		//List<String> animals = new ArrayList<String>();
		while (m.find()) {
			System.out.println("Found a " + m.group() + "." );
		}
*/
    }
    
}
