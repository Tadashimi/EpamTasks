package Task13_TransportataionWithSAXParsing.storage.initor.impl.sax;

import Task13_TransportataionWithSAXParsing.common.solutions.utils.JavaUtilDataUtils;
import Task13_TransportataionWithSAXParsing.storage.initor.impl.FileDataInitor.ParsedTransportation;
import Task13_TransportataionWithSAXParsing.transportation.domain.Transportation;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TransportationHandler extends DefaultHandler {
    private static final String TRANSPORTATIONS = "transportations";
    private static final String TRANSPORTATION = "transportation";
    private static final String CARGO_ID = "cargo_id";
    private static final String CARRIER_ID = "carrier_id";
    private static final String DESCRIPTION = "description";
    private static final String BILL_TO = "billTo";
    private static final String DATE = "date";

    private String elementValue;
    private List<ParsedTransportation> parsedTransportationList;
    private ParsedTransportation parsedTransportation;
    private Transportation transportation;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case TRANSPORTATIONS:
                parsedTransportationList = new ArrayList<>();
                break;
            case TRANSPORTATION:
                parsedTransportation = new ParsedTransportation();
                transportation = new Transportation();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case CARGO_ID:
                parsedTransportation.setCargoRef(elementValue);
                break;
            case CARRIER_ID:
                parsedTransportation.setCarrierRef(elementValue);
                break;
            case DESCRIPTION:
                transportation.setDescription(elementValue);
                break;
            case BILL_TO:
                transportation.setBillTo(elementValue);
                break;
            case DATE:
                try {
                    transportation.setTransportationBeginDate(JavaUtilDataUtils.valueOf(elementValue));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case TRANSPORTATION:
                parsedTransportation.setTransportation(transportation);
                parsedTransportationList.add(parsedTransportation);
                parsedTransportation = null;
                transportation = null;
                break;
        }
    }

    public List<ParsedTransportation> getParsedTransportationList() {
        return parsedTransportationList;
    }
}
