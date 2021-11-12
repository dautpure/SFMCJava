package com.forcelocker.sfmcjava;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import org.json.JSONObject;

public class Get_GetAccessTokenOnBU {
     public HashMap<String,Object> TokenInfo(String MID,String Prop_Loc) throws FileNotFoundException, IOException, UnirestException
    {
        HashMap<String,Object> TokenData = new HashMap<String,Object>();
        FileReader reader = new FileReader(Prop_Loc);  
        Properties prop = new Properties();
        prop.load(reader);
        
        //-------------Execute SFMC API ------------//
        String URL = prop.getProperty("Authentication_Base_URI")+"v2/token";
        String message;
        JSONObject json = new JSONObject();
        json.put("grant_type","client_credentials");
        json.put("client_id",prop.getProperty("Client_Id"));
        json.put("client_secret",prop.getProperty("Client_Secret"));
        json.put("account_id",prop.getProperty("MID"));
        message = json.toString();
        
        Unirest.setTimeouts(0, 0);
        HttpResponse<JsonNode> response = Unirest.post(URL)
                .header("Content-Type", "application/json")
                .body(message)
                .asJson();
        TokenData.put("access_token", response.getBody().getObject().get("access_token"));
        TokenData.put("expires_in", response.getBody().getObject().get("expires_in"));
        TokenData.put("token_type", response.getBody().getObject().get("token_type"));
        //-------------Execute SFMC API ------------//
        return TokenData;
    }   
}
