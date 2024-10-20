package task2.parser;

import task2.entity.Flower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class FlowersSaxBuilder extends AbstractFlowersBuilder {
    private static final Logger LOGGER = LogManager.getLogger();
    private FlowersHandler handler = new FlowersHandler();
    private XMLReader reader;

    public FlowersSaxBuilder() {
        // more code
        // reader configuration
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
            reader.setErrorHandler(new FlowerErrorHandler());
            reader.setContentHandler(handler);
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.error("ParserConfigurationException or SAXException", e);
            e.printStackTrace(); // log
        }
    }

    public FlowersSaxBuilder(Set<Flower> flowers) {
        super(flowers);
        // more code
    }

    @Override
    public void buildSetFlowers(String fileName) {
        try {
            reader.parse(fileName);
        } catch (IOException | SAXException e) {
            e.printStackTrace(); // log
        }
        flowers = handler.getFlowers();
    }
    // private methods
}


