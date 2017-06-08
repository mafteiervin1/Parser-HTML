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
import java.lang.Object;
//org.jsoup.Jsoup
import java.io.BufferedReader;
import java.io.File;
import static java.io.FileDescriptor.in;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import static javax.management.Query.in;
import javax.swing.text.Document;
import static jdk.nashorn.tools.ShellFunctions.input;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Node;
import org.jsoup.safety.Whitelist;
import org.w3c.tidy.Tidy;
public class ParserHtml {
    
    public ArrayList<String> fileContent;
    public ArrayList<Tag> Tags=new ArrayList<Tag>();
    public ArrayList<TagRules> TagsRules=new ArrayList<TagRules>();
    public Whitelist WL=new Whitelist();
    public ParserHtml(ArrayList<String> Content)//construieste continutul unui fisier in clasa parser
    {
        this.fileContent = new ArrayList<String>();
        int i;
        for(i=0;i<Content.size();i++)
            this.fileContent.add(Content.get(i));
        
        //construieste vectorul de taguri si vectorul de reguli
        String globalAttributes="|accesskey=|class=|contenteditable=|contextmenu=|dir=|draggable=|dropzone=|hidden=|id=|lang=|spellcheck=|style=|tabindex=|title=|translate=";
        String eventAttributes="|onafterprint=|onbeforeprint=|onbeforeunload=|onerror=|onhashchange=|onload=|onmessage=|onoffline=|ononline=|"
                + "onpagehide=|onpageshow=|onpopstate=|onresize=|onstorage=|onunload=";
        Tag t;TagRules r;
        t=new Tag("<!-- -->",0,"");this.Tags.add(t); r=new TagRules("()");this.TagsRules.add(r);
        t=new Tag("<!DOCTYPE>",0,"");this.Tags.add(t); r=new TagRules("(html)");this.TagsRules.add(r);
        t=new Tag("<a>",1,"");this.Tags.add(t); r=new TagRules("(download|href=|hreflang=|media=|rel=|target=|type)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<abbr>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<address>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<area>",1,"<map>");this.Tags.add(t); r=new TagRules("(alt=|coords=|download|href=|hreflang=|media=|rel=|shape=|target=|type=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<article>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<aside>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<audio>",1,"");this.Tags.add(t); r=new TagRules("(autoplay|controls|loop|muted|preload=|src=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<b>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<base>",0,"");this.Tags.add(t); r=new TagRules("(href=|target=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<bdi>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<bdo>",1,"");this.Tags.add(t); r=new TagRules("(dir=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<blockquote>",1,"");this.Tags.add(t); r=new TagRules("(cite=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<body>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<br>",0,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<button>",1,"");this.Tags.add(t); r=new TagRules("(autofocus|disabled|form=|formaction=|formenctype=|formmethod=|formnovalidate|formtarget=|name=|type=|value=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<canvas>",1,"");this.Tags.add(t); r=new TagRules("(id=|width=|height=|style=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<caption>",1,"");this.Tags.add(t); r=new TagRules("(align=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<cite>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<code>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<col>",1,"");this.Tags.add(t); r=new TagRules("(span=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<colgroup>",1,"<table>");this.Tags.add(t); r=new TagRules("(span=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<data>",1,"");this.Tags.add(t); r=new TagRules("(value=)"+globalAttributes);this.TagsRules.add(r);
        t=new Tag("<datalist>",1,"");this.Tags.add(t); r=new TagRules("(id=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<dd>",1,"<dl>");this.Tags.add(t); r=new TagRules(""+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<del>",1,"");this.Tags.add(t); r=new TagRules("(cite=|datetime=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<details>",1,"");this.Tags.add(t); r=new TagRules("(open=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<dfn>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<dialog>",1,"");this.Tags.add(t); r=new TagRules("(open=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<div>",1,"");this.Tags.add(t); r=new TagRules("(align=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<dl>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<dt>",1,"<dl>");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<em>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<embed>",0,"");this.Tags.add(t); r=new TagRules("(height=|src=|type=|width=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<fieldset>",1,"");this.Tags.add(t); r=new TagRules("(disabled=|form=|name=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<figcaption>",1,"<figure>");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<figure>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<footer>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<form>",1,"");this.Tags.add(t); r=new TagRules("(accept-charset=|action=|autocomplete=|enctype=|method=|name=|novalidate=|target=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<h1>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<h2>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<h3>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<h4>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<h5>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<h6>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<head>",1,"");this.Tags.add(t); r=new TagRules("()");this.TagsRules.add(r);
        t=new Tag("<header>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<hr>",0,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<html>",1,"");this.Tags.add(t); r=new TagRules("(xmlns=)"+globalAttributes);this.TagsRules.add(r);
        t=new Tag("<i>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<iframe>",1,"");this.Tags.add(t); r=new TagRules("(name=|sandbox=|src=|srcdoc=|width=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<img>",1,"");this.Tags.add(t); r=new TagRules("(alt=|crossorigin=|height=|hspace=|ismap|longdesc=|sizes=|src=|srcset=|usemap=|width=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<input>",0,"");this.Tags.add(t); r=new TagRules("(accept=|alt=|autocomplete=|autofocus|checked|dirname=|disabled|form=|formaction=|formenctype=|formmethod=|formnovalidate=|formtarget=|height=|list=|max=|maxlength=|min=|multiple|name=|pattern=|placeholder=|readonly|required|size=|src=|step=|type=|value=|width=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<ins>",1,"");this.Tags.add(t); r=new TagRules("(cite=|datetime=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<kbd>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<keygen>",0,"");this.Tags.add(t); r=new TagRules("(autofocus|challenge|disabled|form=|keytype=|name=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<label>",1,"");this.Tags.add(t); r=new TagRules("(for=|form=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<legend>",1,"");this.Tags.add(t); r=new TagRules("(align=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<li>",1,"");this.Tags.add(t); r=new TagRules("(type=|value=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<link>",0,"");this.Tags.add(t); r=new TagRules("(crossorigin=|href=|hreflang=|media=|rel=|sizes=|type=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<main>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<map>",1,"<img>");this.Tags.add(t); r=new TagRules("(name=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<mark>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<meta>",1,"<head>");this.Tags.add(t); r=new TagRules("(charset=|content=|http-equiv=|name=|scheme=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<menu>",1,"");this.Tags.add(t); r=new TagRules("(label=|type=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<menuitem>",1,"<menu>");this.Tags.add(t); r=new TagRules("(checked=|command|default|disabled|icon=|label=|radiogroup=|type=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<meta>",1,"<menu>");this.Tags.add(t); r=new TagRules("(charset=|content=|http-equiv=|name=)"+globalAttributes);this.TagsRules.add(r);
        t=new Tag("<meter>",1,"");this.Tags.add(t); r=new TagRules("(form=|high=|low=|max=|min=|optimum=|value=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<nav>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<noscript>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes);this.TagsRules.add(r);
        t=new Tag("<object>",1,"<body>");this.Tags.add(t); r=new TagRules("(form=|height=|data=|name=|type=|usemap=|width=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<ol>",1,"");this.Tags.add(t); r=new TagRules("(reversed|start=|type=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<optgroup>",1,"");this.Tags.add(t); r=new TagRules("(disabled|label=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<option>",1,"<select> <datalist>");this.Tags.add(t); r=new TagRules("(disabled|selected|label=|value=)"+globalAttributes+eventAttributes);this.TagsRules.add(r); //in una sau alta 
        t=new Tag("<output>",1,"");this.Tags.add(t); r=new TagRules("(for=|form=|name=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<p>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<param>",1,"<object>");this.Tags.add(t); r=new TagRules("(name=|value=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<picture>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<pre>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<progress>",1,"");this.Tags.add(t); r=new TagRules("(max=|value=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<q>",1,"");this.Tags.add(t); r=new TagRules("(cite=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<rp>",1,"<ruby><rt>");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r); //in <ruby> <rt> adica in ambele taguri in ordinea asta
        t=new Tag("<rt>",1,"<ruby>");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<ruby>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<s>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<samp>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<script>",1,"");this.Tags.add(t); r=new TagRules("(async|defer|charset=|src=|type=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<section>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<select>",1,"");this.Tags.add(t); r=new TagRules("(autofocus|disabled|multiple|required|form=|name=|size=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<small>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<source>",0,"");this.Tags.add(t); r=new TagRules("(src=|srcset=|media=|sizes=|type=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<span>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<strike>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<strong>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<style>",1,"");this.Tags.add(t); r=new TagRules("(media=|scoped|type=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<sub>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<summary>",1,"<details>");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<sup>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<table>",1,"");this.Tags.add(t); r=new TagRules("(sortable)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<tbody>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<td>",1,"<tr>");this.Tags.add(t); r=new TagRules("(colspan=|headers=|rowspan=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<textarea>",1,"");this.Tags.add(t); r=new TagRules("(autofocus|disabled|readonly|required|cols=|dirname=|form=|maxlength=|name=|placeholder=|rows=|wrap=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<tfoot>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<th>",1,"<tr>");this.Tags.add(t); r=new TagRules("(abbr=|colspan=|headers=|rowspan=|scope=|sorted=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<thead>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<time>",1,"");this.Tags.add(t); r=new TagRules("(datetime=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<title>",1,"<head>");this.Tags.add(t); r=new TagRules("()"+globalAttributes);this.TagsRules.add(r);
        t=new Tag("<tr>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<track>",0,"<audio> <video>");this.Tags.add(t); r=new TagRules("(default|kind=|label=|src=|srclang=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<u>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<ul>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<var>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<video>",1,"");this.Tags.add(t); r=new TagRules("(autoplay|controls|loop|muted|height=|poster|preload=|width=|src=)"+globalAttributes+eventAttributes);this.TagsRules.add(r);
        t=new Tag("<wbr>",1,"");this.Tags.add(t); r=new TagRules("()"+globalAttributes+eventAttributes);this.TagsRules.add(r);//se inchide tot cu <wbr> 
           
    }
    public void countOpenTags() throws IOException
    {
        String content=new String();
        int j;
         for(j=0;j<this.fileContent.size();j++)
             content=content+this.fileContent.get(j)+"\n";
        org.jsoup.nodes.Document doc = Jsoup.parse(content);
        int i;
        for(i=0;i<this.Tags.size();i++)
        {
                    Pattern p = Pattern.compile(this.Tags.get(i).name.substring(0, this.Tags.get(i).name.length()-1)+".*(>)");
                    Pattern pp = Pattern.compile("</"+this.Tags.get(i).name.substring(1, this.Tags.get(i).name.length()-1) +".*(>)");
                    org.jsoup.select.Elements links = doc.getElementsByTag(this.Tags.get(i).name.substring(1, this.Tags.get(i).name.length()-1));
                    //System.out.println(this.Tags.get(getInd(this.Tags.get(i).needInName)).needInName);
                    if(links.isEmpty()==false)
                    {
                        //System.out.println(this.Tags.get(i).name.substring(0, this.Tags.get(i).name.length()-1));
                        Matcher m = p.matcher(content);
                        Matcher mm=pp.matcher(content);
                        while (m.find()) {
                            this.Tags.get(i).counter++;
                            }
                         while (mm.find()) {
                            this.TagsRules.get(i).isClosed++;
                         }
                        // System.out.println(this.Tags.get(getInd(this.Tags.get(i).needInName)).needInName);
                        if(!this.Tags.get(i).name.matches("<track>|<rp>|<option>"))
                        {
                            if(this.Tags.get(i).counter>0&&this.Tags.get(getInd(this.Tags.get(i).needInName)).counter<0)
                                System.err.println("Error: The tag "+this.Tags.get(i).name+" needs to be in tag "+this.Tags.get(i).needInName+"!");
                        }
                        
                    }
        }
        
        int ok=0;
        for(i=2;i<this.Tags.size();i++)
            if(this.Tags.get(i).needClose==1)
            {
                if(this.Tags.get(i).counter>0)
                     if(this.TagsRules.get(i).isClosed!=this.Tags.get(i).counter)
                     {
                         System.err.println("Error: Tag "+this.Tags.get(i).name + " is not closed!" );
                         ok=1;
                     }
            }
            else 
            {
                if(this.TagsRules.get(i).isClosed>0)
                {
                    System.err.println("Error: Tag "+this.Tags.get(i).name + " don`t need to be closed!" );
                    ok=1;
                }
            }               
        if(ok==0)System.out.println("No unclosed tags found!" );
        
    }
    public int getInd(String name)
    {
        int i;
        for(i=0;i<this.Tags.size();i++)
            if(this.Tags.get(i).needInName.equalsIgnoreCase(name))return i;
        return -1;
    }
    public void checkRules()
    {
        int i,j,eqcounter,eqcounter1;
        Pattern eqq=Pattern.compile("=");
        Pattern p=Pattern.compile(">");
        String content = null;
        int k;int ok=0;
        ArrayList<String> Rules;
        for(j=0;j<this.fileContent.size();j++)
             content=content+this.fileContent.get(j)+"\n";
       org.jsoup.nodes.Document doc = Jsoup.parse(content);
        //System.out.println(doc.getElementsByTag("track"));
        for(i=0;i<this.Tags.size();i++)
        {
                Pattern tag=Pattern.compile(this.Tags.get(i).name.substring(0, this.Tags.get(i).name.length()-1));
                Pattern rules = Pattern.compile(this.TagsRules.get(i).pattern);
                for(j=0;j<this.fileContent.size();j++)
                     {
                         eqcounter=eqcounter1=0;
                         Matcher eq= eqq.matcher(this.fileContent.get(j));
                         Matcher pp=p.matcher(this.fileContent.get(j));
                         doc=Jsoup.parse(this.fileContent.get(j));
                         
                         if(!doc.getElementsByTag(this.Tags.get(i).name.substring(1, this.Tags.get(i).name.length()-1)).toString().isEmpty()&&this.fileContent.get(j).contains(this.Tags.get(i).name.substring(0, this.Tags.get(i).name.length()-1))==true)
                         {  
                             eq=eqq.matcher(this.fileContent.get(j).substring(0,this.fileContent.get(j).indexOf(">") ));
                             String line=new String(tokLine(this.fileContent.get(j).substring(0,this.fileContent.get(j).indexOf(">") )));
                             //System.out.println("tok: "+tokLine(this.fileContent.get(j).substring(0,this.fileContent.get(j).indexOf(">") )));
                             while(eq.find())
                             {line=new String(tokLine(this.fileContent.get(j).substring(0,this.fileContent.get(j).indexOf(">") )));
                                 for(k=0;k<line.length();k++)
                                    if(line.toCharArray()[k]==' ')eqcounter++;
                             Rules=new ArrayList<String>(tok(this.TagsRules.get(i).pattern));
                             for(k=0;k<Rules.size();k++)
                                 if(doc.getElementsByTag(this.Tags.get(i).name.substring(1, this.Tags.get(i).name.length()-1)).hasAttr(Rules.get(k)))
                                 {
                                     eqcounter1++;
                                    // System.out.println(this.Tags.get(i).name+" "+Rules.get(k)+" "+eqcounter+" "+eqcounter1);
                                 }
                             //System.out.println(eqcounter+" "+eqcounter1);
                             if(eqcounter!=eqcounter1)
                             {
                                 ok=1;
                                 System.err.println("In tag "+this.Tags.get(i).name+ " at line "+ j +  " an atributte is not accepted! ");
                             }
                             }
                        }
                     }
        }
        if(ok==0)System.out.println("There are no tags who have unaccepted attributes!");
  }
    public void checkParent()
    {
        
    }
    public ArrayList<String> tok(String rules)
    {
        //System.out.println(rules);
        ArrayList<String> Rules=new ArrayList<String>();
        String delim = "|()";
       // StringTokenizer tok = new StringTokenizer(rules, delim, true);
        StringTokenizer stt = new StringTokenizer(rules,delim);
            while (stt.hasMoreTokens()){
                String token = stt.nextToken();
                if(token.endsWith("="))Rules.add(token.substring(0, token.length()-1));
                else Rules.add(token);
                //System.out.println(token.substring(0, token.length()-1));
            }
        return Rules;
    }
    
    public String tokLine(String line)
    {
        String Rules=new String();
        String delim = " ";
       // StringTokenizer tok = new StringTokenizer(rules, delim, true);
        StringTokenizer stt = new StringTokenizer(line,delim);
            while (stt.hasMoreTokens()){
                String token = stt.nextToken();
                Rules.concat(token);
                Rules=Rules+token+" ";     
            }
            //System.out.println(Rules);
        return Rules.substring(0, Rules.length()-1);
    }
    public void listTags()
    {
        int i;
        for(i=0;i<Tags.size();i++)
            System.out.println(i+" "+Tags.get(i).name+' '+Tags.get(i).needClose+' '+Tags.get(i).needInName);
    }
    
    public void listRules()
    {
        int i;
        for(i=0;i<Tags.size();i++)
            System.out.println(i+" "+Tags.get(i).name+' '+TagsRules.get(i).pattern);
    }
    
    public void listFileContent()
    {
        int i;
        for(i=0;i<fileContent.size();i++)
            System.out.println(this.fileContent.get(i));
    }
}
