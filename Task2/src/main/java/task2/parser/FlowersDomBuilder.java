package task2.parser;

import task2.builder.FlowerBuilder;
import task2.builder.PoisonousFlowerBuilder;
import task2.entity.*;
import task2.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FlowersDomBuilder extends AbstractFlowersBuilder {
    private static final Logger LOGGER = LogManager.getLogger();
    private DocumentBuilder docBuilder;

    public FlowersDomBuilder() {
        flowers = new HashSet<Flower>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("ParserConfigurationException or SAXException", e);
            e.printStackTrace();
        }
    }

    public FlowersDomBuilder(Set<Flower> flowers) {
        super(flowers);
    }

    @Override
    public void buildSetFlowers(String fileName) {
        Document doc;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList flowersList = root.getElementsByTagName("flower");
            for (int i = 0; i < flowersList.getLength(); i++) {
                Element flowerElement = (Element) flowersList.item(i);
                Flower flower = buildFlower(flowerElement);
                flowers.add(flower);
            }
            NodeList poisonousFlowersList = root.getElementsByTagName("poisonous_flower");
            for (int i = 0; i < poisonousFlowersList.getLength(); i++) {
                Element flowerElement = (Element) poisonousFlowersList.item(i);
                PoisonousFlower flower = buildPoisonousFlower(flowerElement);
                flowers.add(flower);
            }
        } catch (IOException | SAXException | CustomException e) {
            LOGGER.error("ParserConfigurationException or SAXException", e);
            e.printStackTrace();
        }
    }

    private Flower buildFlower(Element flowerElement) throws CustomException {
        Element growingTipElement = (Element) flowerElement.getElementsByTagName("growing_tips").item(0);
        Element visualDescriptionElement = (Element) flowerElement.getElementsByTagName("visual_parameters").item(0);
        return new FlowerBuilder()
                .id(flowerElement.getAttribute("id"))
                .name(getElementTextContent(flowerElement, "name"))
                .growingTip(buildGrowingTip(growingTipElement))
                .multiplyingType(MultiplyingType.getMultiplyingTypeByValue(getElementTextContent(flowerElement, "multiplying")))
                .origin(getElementTextContent(flowerElement, "origin"))
                .soilType(Soil.getSoilByValue(getElementTextContent(flowerElement, "soil")))
                .visualDescription(buildVisualDescription(visualDescriptionElement))
                .date(LocalDate.parse(getElementTextContent(flowerElement, "receipt_date")))
                .build();
    }

    private PoisonousFlower buildPoisonousFlower(Element flowerElement) throws CustomException {
        Element growingTipElement = (Element) flowerElement.getElementsByTagName("growing_tips").item(0);
        Element visualDescriptionElement = (Element) flowerElement.getElementsByTagName("visual_parameters").item(0);
        return (PoisonousFlower) new PoisonousFlowerBuilder()
                .dangerLevel(DangerLevel.getDangerLevelByValue(flowerElement.getAttribute("danger_level")))
                .id(flowerElement.getAttribute("id"))
                .name(getElementTextContent(flowerElement, "name"))
                .growingTip(buildGrowingTip(growingTipElement))
                .multiplyingType(MultiplyingType.getMultiplyingTypeByValue(getElementTextContent(flowerElement, "multiplying")))
                .origin(getElementTextContent(flowerElement, "origin"))
                .soilType(Soil.getSoilByValue(getElementTextContent(flowerElement, "soil")))
                .visualDescription(buildVisualDescription(visualDescriptionElement))
                .date(LocalDate.parse(getElementTextContent(flowerElement, "receipt_date")))
                .build();
    }

    private GrowingTip buildGrowingTip(Element growingTipElement) throws CustomException {
        GrowingTip growingTip = new GrowingTip();
        growingTip.setGrowthTemperature(Integer.parseInt(getElementTextContent(growingTipElement, "growth_temperature")));
        growingTip.setWeeklyWatering(Integer.parseInt(getElementTextContent(growingTipElement, "weekly_watering")));
        growingTip.setPhotophilous(Boolean.parseBoolean(getElementTextContent(growingTipElement, "is_photophilous")));
        return growingTip;
    }

    private VisualDescription buildVisualDescription(Element visualDescriptionElement) throws CustomException {
        VisualDescription visualDescription = new VisualDescription();
        visualDescription.setAverageSize(Integer.parseInt(getElementTextContent(visualDescriptionElement, "average_size")));
        visualDescription.setStemColour(Colour.getColourByValue(getElementTextContent(visualDescriptionElement, "stem_colour")));
        visualDescription.setPetalsColour(Colour.getColourByValue(getElementTextContent(visualDescriptionElement, "petals_colour")));
        return visualDescription;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}


