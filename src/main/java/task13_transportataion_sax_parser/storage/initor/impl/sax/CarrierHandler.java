package task13_transportataion_sax_parser.storage.initor.impl.sax;

import task13_transportataion_sax_parser.carrier.domain.Carrier;
import task13_transportataion_sax_parser.carrier.domain.CarrierType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

public class CarrierHandler extends DefaultHandler {
    private static final String CARRIERS = "carriers";
    private static final String CARRIER = "carrier";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";

    private String elementValue;
    private Map<String, Carrier> carrierMap;
    private Carrier carrier;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case CARRIERS:
                carrierMap = new HashMap<>();
                break;
            case CARRIER:
                carrier = new Carrier();
                CarrierType type = CarrierType.valueOf(attributes.getValue("type"));
                carrier.setCarrierType(type);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case ID:
                if (carrier != null) {
                    carrierMap.put(elementValue, carrier);
                }
                break;
            case NAME:
                if (carrier != null) {
                    carrier.setName(elementValue);
                }
                break;
            case ADDRESS:
                carrier.setAddress(elementValue);
                break;
            case CARRIER:
                carrier = null;
        }
    }

    public Map<String, Carrier> getCarrierMap() {
        return carrierMap;
    }
}
