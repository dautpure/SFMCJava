package com.forcelocker.sfmcjava;

import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import org.xml.sax.SAXException;

public class Test_Get_DataExtension {
    public static void main(String[] arg) throws IOException, FileNotFoundException, UnirestException, SOAPException, ParserConfigurationException, SAXException
    {
        Get_DataExtension de = new Get_DataExtension();
        ArrayList<DataExtension> DE_Data = new ArrayList<DataExtension>();
        String PropFile = "D:\\Works\\Java\\SFMC_Test_KPMGNL\\src\\main\\java\\com\\mycompany\\sfmc_test_kpmgnl\\fuelsdk.properties";
        DE_Data = de.GetDataExtensionList(PropFile);
        for(int ii=0;ii<DE_Data.size();ii++)
        {
           DataExtension temp1 = DE_Data.get(ii);
           System.out.println("MID:"+temp1.getMID()+"   Name:"+temp1.getName());
        }
    }
}
