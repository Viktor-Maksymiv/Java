package Lab4;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Lab4 {
    public static void main(String[] args) {
        String gpxFilePath = "D:\\IdeaProjects\\Java\\src\\Lab4\\L-35-003-points.gpx";
        String kmlFilePath = "D:\\IdeaProjects\\Java\\src\\Lab4\\result.kml";
        creatorKML(gpxFilePath, kmlFilePath);
    }

    public static void creatorKML(String gpxPath, String kmlPath) {
        try {
            File gpxFile = new File(gpxPath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document gpxDoc = builder.parse(gpxFile);


            Document kmlDoc = builder.newDocument();
            Element kmlRoot = kmlDoc.createElement("kml");
            kmlDoc.appendChild(kmlRoot);

            Element document = kmlDoc.createElement("Document");
            kmlRoot.appendChild(document);
            NodeList styleIcon = gpxDoc.getElementsByTagName("sym");
            List<String> iconList = new ArrayList<>();

            for (int i = 0; i < styleIcon.getLength(); i++) {
                Node node = styleIcon.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String icons = node.getTextContent().trim();

                    if (iconList.contains(icons)) {
                        continue;
                    }
                    iconList.add(icons);

                    Element parentStyle = null;
                    
                    Element style = kmlDoc.createElement("Style");
                    if (icons.equals("Drinking Water")) {
                        style.setAttribute("id", "Blue");
                    } else{
                        style.setAttribute("id", "Green");
                    }


                    Element iconStyle = kmlDoc.createElement("IconStyle");
                    Element icon = kmlDoc.createElement("Icon");
                    Element href = kmlDoc.createElement("href");
                    if (icons.equals("Drinking Water")) {
                        href.setTextContent("http://maps.google.com/mapfiles/kml/pushpin/ltblu-pushpin.png");
                    } else{
                        href.setTextContent("http://maps.google.com/mapfiles/kml/pushpin/grn-pushpin.png");
                    }

                    icon.appendChild(href);
                    iconStyle.appendChild(icon);

                    Element hotSpot = kmlDoc.createElement("hotSpot");
                    hotSpot.setAttribute("x", "32");
                    hotSpot.setAttribute("y", "1");
                    hotSpot.setAttribute("xunits", "pixels");
                    hotSpot.setAttribute("yunits", "pixels");
                    iconStyle.appendChild(hotSpot);
                    style.appendChild(iconStyle);

                    if (parentStyle == null) {
                        document.appendChild(style);
                    } else {
                        parentStyle.appendChild(style);
                    }
                }
            }


            Element folder = kmlDoc.createElement("Folder");
            document.appendChild(folder);

            NodeList waypoints = gpxDoc.getElementsByTagName("wpt");
            for (int i = 0; i < waypoints.getLength(); i++) {
                Element wpt = (Element) waypoints.item(i);
                String lat = wpt.getAttribute("lat");
                String lon = wpt.getAttribute("lon");
                String name = getTextContent(wpt, "cmt");
                String sym = getTextContent(wpt, "sym");
                String ele = getTextContent(wpt, "ele");
                String link = getAttributeValue(wpt, "link", "href");

                Element placemark = kmlDoc.createElement("Placemark");
                folder.appendChild(placemark);

                Element nameElement = kmlDoc.createElement("name");
                nameElement.setTextContent(name);
                placemark.appendChild(nameElement);

                Element description = kmlDoc.createElement("description");
                description.setTextContent(link);
                placemark.appendChild(description);

                Element styleUrl = kmlDoc.createElement("styleUrl");
                if (sym.equals("Drinking Water")) {
                    styleUrl.setTextContent("#Blue");
                } else{
                    styleUrl.setTextContent("#Green");
                }
                placemark.appendChild(styleUrl);

                Element point = kmlDoc.createElement("Point");
                placemark.appendChild(point);

                Element coordinates = kmlDoc.createElement("coordinates");
                coordinates.setTextContent(lon + "," + lat + "," + ele);
                point.appendChild(coordinates);
            }


            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(kmlDoc);
            StreamResult result = new StreamResult(new File(kmlPath));
            transformer.transform(source, result);

            System.out.println("KML файл успішно створений: " + kmlPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTextContent(Element element, String tagName) {
        NodeList nodes = element.getElementsByTagName(tagName);
        return (nodes.getLength() > 0) ? nodes.item(0).getTextContent() : "Unknown";
    }

    private static String getAttributeValue(Element element, String tagName, String attributeName) {
        NodeList nodes = element.getElementsByTagName(tagName);
        if (nodes.getLength() > 0) {
            Element tagElement = (Element) nodes.item(0);
            return tagElement.getAttribute(attributeName);
        }
        return "Unknown";
    }
}
