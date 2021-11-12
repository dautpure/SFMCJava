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

public class Get_DataFolder {

    public ArrayList<DataFolder> GetDataFolderList(String Prop_File_loc) throws FileNotFoundException, IOException, UnirestException, SOAPException, ParserConfigurationException, SAXException {
        ArrayList<DataFolder> DataFolder_Data = new ArrayList<DataFolder>();
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
        String payload = get_DataFolder_Payload(TokenValue, TenentId);

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
                    String MID = "";
                    String CreatedDate = "";
                    String ID = "";
                    String ObjectID = "";
                    String CustomerKey = "";
                    String ParentFolderId = "";
                    String Name = "";
                    String ContentType = "";
                    String AllowChildren = "";
                    String Description = "";
                    String ParentFolder = "";
                    String PartnerKey = "";
                    String Parentfolder_id = "";
                    String Parentfolder_objectid = "";
                    
                    DataFolder DataFolder_Temp = new DataFolder(AllowChildren, ContentType, CreatedDate, CustomerKey, Description, ID, Name, ObjectID, ParentFolder, PartnerKey, MID,Parentfolder_id,Parentfolder_objectid);
                    for (int j = 0; j < BU.getLength(); j++) {
                        Node BU_field = BU.item(j);
                        Element name = (Element) BU_field;
                        if (name.getTagName() == "CreatedDate") {
                            DataFolder_Temp.setCreatedDate(name.getTextContent());
                        }
                        if (name.getTagName() == "ID") {
                            DataFolder_Temp.setID(name.getTextContent());
                        }
                        if (name.getTagName() == "ObjectID") {
                            DataFolder_Temp.setObjectID(name.getTextContent());
                        }
                        if (name.getTagName() == "CustomerKey") {
                            DataFolder_Temp.setCustomerKey(name.getTextContent());
                        }
                        if (name.getTagName() == "Name") {
                            DataFolder_Temp.setName(name.getTextContent());
                        }
                        if (name.getTagName() == "ContentType") {
                            DataFolder_Temp.setContentType(name.getTextContent());
                        }
                        if (name.getTagName() == "AllowChildren") {
                            DataFolder_Temp.setAllowChildren(name.getTextContent());
                        }
                        if (name.getTagName() == "Client") {
                            DataFolder_Temp.setMID(name.getChildNodes().item(0).getTextContent());
                        }
                        if (name.getTagName() == "ParentFolder") {
                            NodeList ParentFolder_Node = name.getChildNodes();
                            for(int k=0;k<ParentFolder_Node.getLength();k++)
                            {
                                if(ParentFolder_Node.item(k).getNodeName()=="ID")
                                {
                                    DataFolder_Temp.setParentfolder_id(ParentFolder_Node.item(k).getTextContent());
                                }
                                if(ParentFolder_Node.item(k).getNodeName()=="ObjectID")
                                {
                                    DataFolder_Temp.setParentfolder_objectid(ParentFolder_Node.item(k).getTextContent());
                                }
                            }
                        }                        
                    }
                    DataFolder_Data.add(DataFolder_Temp);
                }
            }
        }

        return (DataFolder_Data);
    }

    static String get_DataFolder_Payload(String AccessToken, String TenentId) throws SOAPException, IOException {
        String DataFolder_Payload = "";

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
        Method_Element.addChildElement("ObjectType").addTextNode("DataFolder");
        Method_Element.addChildElement("QueryAllAccounts").addTextNode("true");
        Method_Element.addChildElement("Properties").addTextNode("Name");
        Method_Element.addChildElement("Properties").addTextNode("AllowChildren");
        Method_Element.addChildElement("Properties").addTextNode("Client.ID");
        Method_Element.addChildElement("Properties").addTextNode("ContentType");
        Method_Element.addChildElement("Properties").addTextNode("CreatedDate");
        Method_Element.addChildElement("Properties").addTextNode("CustomerKey");
        Method_Element.addChildElement("Properties").addTextNode("ID");
        Method_Element.addChildElement("Properties").addTextNode("ObjectID");
        Method_Element.addChildElement("Properties").addTextNode("ParentFolder.ID");
        Method_Element.addChildElement("Properties").addTextNode("ParentFolder.Name");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        soapMsg.writeTo(stream);
        DataFolder_Payload = new String(stream.toByteArray(), "utf-8");

        return (DataFolder_Payload);
    }
}
