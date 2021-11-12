package com.forcelocker.sfmcjava;

import com.exacttarget.fuelsdk.ETSdkException;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import org.xml.sax.SAXException;

public class Test_Get_BusinessUnits {
    public static void main(String[] args) throws ETSdkException, IOException, FileNotFoundException, UnirestException, SOAPException, ParserConfigurationException, SAXException{
        Get_BusinessUnits BU = new Get_BusinessUnits();
        ArrayList<BusinessUnit> BU_Data = new ArrayList<BusinessUnit>();
        String PropFile = "D:\\Works\\Java\\SFMC_Test_KPMGNL\\src\\main\\java\\com\\mycompany\\sfmc_test_kpmgnl\\fuelsdk.properties";
        BU_Data = BU.GetBUList(PropFile);
        
        for(int ii=0;ii<BU_Data.size();ii++)
        {
           BusinessUnit temp1 = BU_Data.get(ii);
           System.out.println("Name:"+temp1.getParentID());
        }
    }
}
