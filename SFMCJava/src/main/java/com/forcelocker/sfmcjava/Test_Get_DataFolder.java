package com.forcelocker.sfmcjava;

import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import org.xml.sax.SAXException;

public class Test_Get_DataFolder {
    public static void main(String[] arg) throws IOException, FileNotFoundException, UnirestException, SOAPException, ParserConfigurationException, SAXException
    {
        Get_DataFolder df = new Get_DataFolder();
        ArrayList<DataFolder> DF_Data = new ArrayList<DataFolder>();
        String PropFile = "D:\\Works\\Java\\SFMC_Test_KPMGNL\\src\\main\\java\\com\\mycompany\\sfmc_test_kpmgnl\\fuelsdk.properties";
        DF_Data = df.GetDataFolderList(PropFile);
        for(int ii=0;ii<DF_Data.size();ii++)
        {
           DataFolder temp1 = DF_Data.get(ii);
           System.out.println("MID:"+temp1.getMID()+" Name:"+temp1.getName());
        }
    }
}
