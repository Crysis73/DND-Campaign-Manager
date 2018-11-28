package edu.bsu.cs222;


import java.io.FileWriter;
import java.io.IOException;

class JsonWriter {




    void writeCampaignJson(Campaign campaign){
        String jsonString = campaign.generateJsonString();
        String filename;
        if(campaign.getCampaignName().contains(".json")){
            filename = campaign.getCampaignName();
        }else{
            filename = campaign.getCampaignName()+".json";
        }
        try (FileWriter file = new FileWriter(filename)) {
            file.write(jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
