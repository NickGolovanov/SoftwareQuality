package nhl.stenden.factorymethod;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import nhl.stenden.*;
import nhl.stenden.observer.Presentation;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;


/**
 * XMLAccessor class for handling XML file operations.
 * 
 * SOLID Principles Implementation:
 * - Single Responsibility Principle (SRP): Handles only XML file reading/writing operations
 * - Liskov Substitution Principle (LSP): Properly implements Accessor interface contract
 * - Open-Closed Principle (OCP): Can be extended for different XML formats without modification
 * - Dependency Inversion Principle (DIP): Implements abstract Accessor interface
 */
public class XMLAccessor extends Accessor
{
    /**
     * Default API to use.
     */
    protected static final String DEFAULT_API_TO_USE = "dom";

    private String getTitle(Element element, String tagName)
    {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();
    }

    public void loadFile(Presentation presentation, String filename) throws IOException
    {
        int slideNumber, itemNumber, max = 0, maxItems = 0;
        try
        {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename)); // Create a JDOM document
            Element doc = document.getDocumentElement();
            presentation.setTitle(this.getTitle(doc, XmlAttributes.SHOW_TITLE.getAttribute()));

            NodeList slides = doc.getElementsByTagName(XmlAttributes.SLIDE.getAttribute());
            max = slides.getLength();
            for (slideNumber = 0; slideNumber < max; slideNumber++)
            {
                Element xmlSlide = (Element) slides.item(slideNumber);
                Slide slide = new Slide();
                slide.setTitle(this.getTitle(xmlSlide, XmlAttributes.SLIDE_TITLE.getAttribute()));
                presentation.append(slide);

                NodeList slideItems = xmlSlide.getElementsByTagName(XmlAttributes.ITEM.getAttribute());
                maxItems = slideItems.getLength();
                for (itemNumber = 0; itemNumber < maxItems; itemNumber++)
                {
                    Element item = (Element) slideItems.item(itemNumber);
                    this.loadSlideItem(slide, item);
                }
            }
        } catch (SAXException sax)
        {
            throw new IOException("XML parsing error: " + sax.getMessage(), sax);
        } catch (ParserConfigurationException pcx)
        {
            throw new IOException("Parser configuration error: " + ErrorMessage.PARSER_CONFIG_ERROR.getErrorMessage(), pcx);
        }
    }

    private void loadSlideItem(Slide slide, Element item)
    {
        int level = 1; // default
        NamedNodeMap attributes = item.getAttributes();
        String levelText = attributes.getNamedItem(XmlAttributes.LEVEL.getAttribute()).getTextContent();
        if (levelText != null)
        {
            try
            {
                level = Integer.parseInt(levelText);
            } catch (NumberFormatException x)
            {
                System.err.println(ErrorMessage.NUMBER_FORMAT_ERROR.getErrorMessage());
            }
        }
        String type = attributes.getNamedItem(XmlAttributes.KIND.getAttribute()).getTextContent();
        if (XmlAttributes.TEXT.getAttribute().equals(type))
        {
            slide.append(new TextItem(level, item.getTextContent()));
        } else
        {
            if (XmlAttributes.IMAGE.getAttribute().equals(type))
            {
                slide.append(new BitmapItem(level, item.getTextContent()));
            } else
            {
                System.err.println(ErrorMessage.UNKNOWN_TYPE.getErrorMessage());
            }
        }
    }

    public void saveFile(Presentation presentation, String filename) throws IOException
    {
        PrintWriter out = new PrintWriter(new FileWriter(filename));
        out.println("<?xml version=\"1.0\"?>");
        out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
        out.println("<presentation>");
        out.print("<showtitle>");
        out.print(presentation.getTitle());
        out.println("</showtitle>");
        for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++)
        {
            Slide slide = presentation.getSlide(slideNumber);
            out.println("<slide>");
            out.println("<title>" + slide.getTitle() + "</title>");
            List<SlideItem> slideItems = slide.getSlides();
            for (int itemNumber = 0; itemNumber < slideItems.size(); itemNumber++)
            {
                SlideItem slideItem = slide.getSlide(itemNumber);
                out.print("<item kind=");
                if (slideItem instanceof TextItem)
                {
                    out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
                    out.print(((TextItem) slideItem).getText());
                } else
                {
                    if (slideItem instanceof BitmapItem)
                    {
                        out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
                        out.print(((BitmapItem) slideItem).getImageName());
                    } else
                    {
                        System.out.println("Ignoring " + slideItem);
                    }
                }
                out.println("</item>");
            }
            out.println("</slide>");
        }
        out.println("</presentation>");
        out.close();
    }
}
