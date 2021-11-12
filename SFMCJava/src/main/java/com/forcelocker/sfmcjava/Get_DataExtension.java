package com.forcelocker.sfmcjava;

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

public class Get_DataExtension {

    public ArrayList<DataExtension> GetDataExtensionList(String Prop_File_loc) throws FileNotFoundException, IOException, UnirestException, SOAPException, ParserConfigurationException, SAXException {
        ArrayList<DataExtension> DataExtension_Data = new ArrayList<DataExtension>();
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
        String payload = get_DataExtension_Payload(TokenValue, TenentId);

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
                    NodeList DE = Result.getChildNodes();
                    String CategoryID = "";
                    String CreatedDate = "";
                    String CustomerKey = "";
                    String DataRetentionPeriod = "";
                    String DataRetentionPeriodLength = "";
                    String DataRetentionPeriodUnitOfMeasure = "";
                    String DeleteAtEndOfRetentionPeriod = "";
                    String Description = "";
                    String IsSendable = "";
                    String IsTestable = "";
                    String Name = "";
                    String MID = "";
                    String ObjectID = "";
                    String PartnerKey = "";
                    String ResetRetentionPeriodOnImport = "";
                    String RetainUntil = "";
                    String RowBasedRetention = "";
                    String Status = "";
                    String SendableDataExtensionFieldName = "";
                    String SendableSubscriberFieldName = "";

                    DataExtension DataExtension_Temp = new DataExtension(CategoryID, CreatedDate, CustomerKey, DataRetentionPeriod, DataRetentionPeriodLength, DataRetentionPeriodUnitOfMeasure, DeleteAtEndOfRetentionPeriod, Description, IsSendable, IsTestable, Name, MID, ObjectID, PartnerKey, ResetRetentionPeriodOnImport, RetainUntil, RowBasedRetention, Status, SendableDataExtensionFieldName, SendableSubscriberFieldName);
                    for (int j = 0; j < DE.getLength(); j++) {
                        Node BU_field = DE.item(j);
                        Element name = (Element) BU_field;
                        if (name.getTagName() == "CategoryID") {
                            DataExtension_Temp.setCategoryID(name.getTextContent());
                        }
                        if (name.getTagName() == "CreatedDate") {
                            DataExtension_Temp.setCreatedDate(name.getTextContent());
                        }
                        if (name.getTagName() == "CustomerKey") {
                            DataExtension_Temp.setCustomerKey(name.getTextContent());
                        }
                        if (name.getTagName() == "DataRetentionPeriod") {
                            DataExtension_Temp.setDataRetentionPeriod(name.getTextContent());
                        }
                        if (name.getTagName() == "DataRetentionPeriodLength") {
                            DataExtension_Temp.setDataRetentionPeriodLength(name.getTextContent());
                        }
                        if (name.getTagName() == "DataRetentionPeriodUnitOfMeasure") {
                            DataExtension_Temp.setDataRetentionPeriodUnitOfMeasure(name.getTextContent());
                        }
                        if (name.getTagName() == "DeleteAtEndOfRetentionPeriod") {
                            DataExtension_Temp.setDeleteAtEndOfRetentionPeriod(name.getTextContent());
                        }
                        if (name.getTagName() == "Description") {
                            DataExtension_Temp.setDescription(name.getTextContent());
                        }
                        if (name.getTagName() == "IsSendable") {
                            DataExtension_Temp.setIsSendable(name.getTextContent());
                        }
                        if (name.getTagName() == "IsTestable") {
                            DataExtension_Temp.setIsTestable(name.getTextContent());
                        }
                        if (name.getTagName() == "IsTestable") {
                            DataExtension_Temp.setIsTestable(name.getTextContent());
                        }
                        if (name.getTagName() == "Name") {
                            DataExtension_Temp.setName(name.getTextContent());
                        }
                        if (name.getTagName() == "Client") {
                            DataExtension_Temp.setMID(name.getChildNodes().item(0).getTextContent());
                        }
                        if (name.getTagName() == "IsObjectID") {
                            DataExtension_Temp.setObjectID(name.getTextContent());
                        }
                        if (name.getTagName() == "PartnerKey") {
                            DataExtension_Temp.setPartnerKey(name.getTextContent());
                        }
                        if (name.getTagName() == "ResetRetentionPeriodOnImport") {
                            DataExtension_Temp.setResetRetentionPeriodOnImport(name.getTextContent());
                        }
                        if (name.getTagName() == "RetainUntil") {
                            DataExtension_Temp.setRetainUntil(name.getTextContent());
                        }
                        if (name.getTagName() == "RowBasedRetention") {
                            DataExtension_Temp.setRowBasedRetention(name.getTextContent());
                        }
                        if (name.getTagName() == "Status") {
                            DataExtension_Temp.setStatus(name.getTextContent());
                        }                        
                        if (name.getTagName() == "SendableDataExtensionField"){
                            NodeList ParentFolder_Node = name.getChildNodes();
                            for(int k=0;k<ParentFolder_Node.getLength();k++){
                                if(ParentFolder_Node.item(k).getNodeName()=="Name")
                                {
                                    DataExtension_Temp.setSendableDataExtensionFieldName(ParentFolder_Node.item(k).getTextContent());
                                }                                
                            }
                        }                        
                        if (name.getTagName() == "SendableSubscriberField"){
                            NodeList ParentFolder_Node = name.getChildNodes();
                            for(int k=0;k<ParentFolder_Node.getLength();k++){
                                if(ParentFolder_Node.item(k).getNodeName()=="Name")
                                {
                                    DataExtension_Temp.setSendableSubscriberFieldName(ParentFolder_Node.item(k).getTextContent());
                                }                                
                            }
                        }                     
                    }
                    DataExtension_Data.add(DataExtension_Temp);
                }
            }
        }

        return (DataExtension_Data);
    }

    static String get_DataExtension_Payload(String AccessToken, String TenentId) throws SOAPException, IOException {
        String DataExtension_Payload = "";

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
        Method_Element.addChildElement("ObjectType").addTextNode("DataExtension");
        Method_Element.addChildElement("QueryAllAccounts").addTextNode("true");
        Method_Element.addChildElement("Properties").addTextNode("CategoryID");
        Method_Element.addChildElement("Properties").addTextNode("CreatedDate");
        Method_Element.addChildElement("Properties").addTextNode("CustomerKey");
        Method_Element.addChildElement("Properties").addTextNode("DataRetentionPeriod");
        Method_Element.addChildElement("Properties").addTextNode("DataRetentionPeriodLength");
        Method_Element.addChildElement("Properties").addTextNode("DataRetentionPeriodUnitOfMeasure");
        Method_Element.addChildElement("Properties").addTextNode("DeleteAtEndOfRetentionPeriod");
        Method_Element.addChildElement("Properties").addTextNode("Description");
        Method_Element.addChildElement("Properties").addTextNode("IsSendable");
        Method_Element.addChildElement("Properties").addTextNode("IsTestable");
        Method_Element.addChildElement("Properties").addTextNode("Name");
        Method_Element.addChildElement("Properties").addTextNode("Client.ID");
        Method_Element.addChildElement("Properties").addTextNode("ObjectID");
        Method_Element.addChildElement("Properties").addTextNode("PartnerKey");
        Method_Element.addChildElement("Properties").addTextNode("ResetRetentionPeriodOnImport");
        Method_Element.addChildElement("Properties").addTextNode("RetainUntil");
        Method_Element.addChildElement("Properties").addTextNode("RowBasedRetention");
        Method_Element.addChildElement("Properties").addTextNode("Status");
        Method_Element.addChildElement("Properties").addTextNode("SendableDataExtensionField.Name");
        Method_Element.addChildElement("Properties").addTextNode("SendableSubscriberField.Name");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        soapMsg.writeTo(stream);
        DataExtension_Payload = new String(stream.toByteArray(), "utf-8");

        return (DataExtension_Payload);
    }
}
