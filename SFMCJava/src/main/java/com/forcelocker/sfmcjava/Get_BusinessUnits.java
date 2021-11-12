package com.forcelocker.sfmcjava;

import com.exacttarget.fuelsdk.ETSdkException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Get_BusinessUnits {

    public ArrayList<BusinessUnit> GetBUList(String Prop_File_loc) throws ETSdkException, FileNotFoundException, IOException, UnirestException, SOAPException, ParserConfigurationException, SAXException {
        ArrayList<BusinessUnit> BU_Data = new ArrayList<BusinessUnit>();

        FileReader reader = new FileReader(Prop_File_loc);
        Properties prop = new Properties();
        prop.load(reader);
        String TenentId = prop.getProperty("TenantId");
        String ServiceURI = prop.getProperty("Service_URI");

        Get_GetAccessToken Tokenclass = new Get_GetAccessToken();
        HashMap<String, Object> Token = Tokenclass.TokenInfo(Prop_File_loc);
        String TokenValue = (String) Token.get("access_token");
        Integer Token_Expiry = (Integer) Token.get("expires_in");
        String token_type_value = (String) Token.get("token_type");

        String payload = get_BU_Payload(TokenValue, TenentId);

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post(ServiceURI)
                .header("Content-Type", "text/xml")
                .header("Authorization", "Bearer" + TokenValue)
                .header("Action", "Retrieve")
                .body(payload).asString();

        if (response.getStatus() == 200) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document res = builder.parse(new InputSource(new StringReader(response.getBody())));
            NodeList ResultsList = res.getElementsByTagName("Results");
            for (int i = 0; i < ResultsList.getLength(); i++) {
                Node p = ResultsList.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element Result = (Element) p;
                    NodeList BU = Result.getChildNodes();
                    String PartnerKey = "";
                    String ID = "";
                    String ObjectID = "";
                    String CustomerKey = "";
                    String AccountType = "";
                    String ParentID = "";
                    String Name = "";
                    String FromName = "";
                    String BusinessName = "";
                    String EditionID = "";
                    String Subscription = "";
                    String MID = "";
                    String ParentMID = "";;
                    BusinessUnit BU_temp = new BusinessUnit(PartnerKey, ID, ObjectID, CustomerKey, AccountType, ParentID, Name, FromName, BusinessName, EditionID, Subscription, MID, ParentMID);
                    for (int j = 0; j < BU.getLength(); j++) {
                        Node BU_field = BU.item(j);
                        Element name = (Element) BU_field;
                        if (name.getTagName() == "PartnerKey") {
                            BU_temp.setPartnerKey(name.getTextContent());
                        }
                        if (name.getTagName() == "ID") {
                            BU_temp.setID(name.getTextContent());
                        }
                        if (name.getTagName() == "ObjectID") {
                            BU_temp.setObjectID(name.getTextContent());
                        }
                        if (name.getTagName() == "CustomerKey") {
                            BU_temp.setCustomerKey(name.getTextContent());
                        }
                        if (name.getTagName() == "AccountType") {
                            BU_temp.setAccountType(name.getTextContent());
                        }
                        if (name.getTagName() == "ParentID") {
                            BU_temp.setParentID(name.getTextContent());
                        }
                        if (name.getTagName() == "Name") {
                            BU_temp.setName(name.getTextContent());
                        }
                        if (name.getTagName() == "FromName") {
                            BU_temp.setFromName(name.getTextContent());
                        }
                        if (name.getTagName() == "BusinessName") {
                            BU_temp.setBusinessName(name.getTextContent());
                        }
                        if (name.getTagName() == "EditionID") {
                            BU_temp.setEditionID(name.getTextContent());
                        }
                        if (name.getTagName() == "Subscription") {
                            BU_temp.setSubscription(name.getTextContent());
                        }
                        if (name.getTagName() == "Client") {
                            BU_temp.setMID(name.getChildNodes().item(0).getTextContent());
                        }
                    }
                    BU_Data.add(BU_temp);
                }
            }
        }

        return (BU_Data);
    }

    static String get_BU_Payload(String AccessToken, String TenentId) throws SOAPException, IOException {
        String BU_Payload = "";
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage soapMsg = factory.createMessage();
        SOAPPart part = soapMsg.getSOAPPart();

        //Envelope
        SOAPEnvelope envelope = part.getEnvelope();
        envelope.setPrefix("s");
        envelope.addNamespaceDeclaration("a", "http://schemas.xmlsoap.org/ws/2004/08/addressing");
        envelope.addNamespaceDeclaration("u", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");

        //Header
        SOAPHeader header = envelope.getHeader();
        header.setPrefix("s");
        header.addChildElement("Action", "a").addTextNode("Retrieve").setAttribute("s:mustUnderstand", "1");
        header.addChildElement("To", "a").addTextNode("https://" + TenentId + ".soap.marketingcloudapis.com/Service.asmx").setAttribute("s:mustUnderstand", "1");
        header.addChildElement("fueloauth", "a").addTextNode(AccessToken).setAttribute("xmlns", "http://exacttarget.com");

        //Body
        SOAPBody body = envelope.getBody();
        body.setPrefix("s");
        body.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        body.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        SOAPBodyElement Wrapper_Method_Element = (SOAPBodyElement) body.addChildElement("RetrieveRequestMsg", "", "http://exacttarget.com/wsdl/partnerAPI");
        SOAPBodyElement Method_Element = (SOAPBodyElement) Wrapper_Method_Element.addChildElement("RetrieveRequest");
        Method_Element.addChildElement("ObjectType").addTextNode("BusinessUnit");
        Method_Element.addChildElement("QueryAllAccounts").addTextNode("true");
        Method_Element.addChildElement("Properties").addTextNode("Name");
        Method_Element.addChildElement("Properties").addTextNode("AccountType");
        Method_Element.addChildElement("Properties").addTextNode("BusinessName");
        Method_Element.addChildElement("Properties").addTextNode("ParentID");
        Method_Element.addChildElement("Properties").addTextNode("CustomerKey");
        Method_Element.addChildElement("Properties").addTextNode("EditionID");
        Method_Element.addChildElement("Properties").addTextNode("FromName");
        Method_Element.addChildElement("Properties").addTextNode("ID");
        Method_Element.addChildElement("Properties").addTextNode("Client.ID");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        soapMsg.writeTo(stream);
        BU_Payload = new String(stream.toByteArray(), "utf-8");
        return BU_Payload;
    }
}
