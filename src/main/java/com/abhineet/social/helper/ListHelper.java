package com.abhineet.social.helper;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListHelper {
    public String convertListToCsv(List<String> csvStrings){
        if(csvStrings==null ) return null;
        if(csvStrings.size()==0) return "";
        if(csvStrings.size()==1) return csvStrings.get(0);
        StringBuffer stringBuffer = new StringBuffer(csvStrings.get(0));
        for(int i=1;i<csvStrings.size();i++){
            stringBuffer.append(",");
            stringBuffer.append(csvStrings.get(i));
        }
        return stringBuffer.toString();
    }
    public List<String> convertCsvToList(String csv){
        List<String> csvList= new ArrayList<>();
        if(csv==null) return csvList;
        String[] csvStrings= csv.split(",");
        if(csvStrings.length<1 && !csv.equals("")) {
            csvList.add(csv);
        }
        else {
            for (String csvString : csvStrings) {
                if(!csvString.equals(""))
                    csvList.add(csvString);
            }
        }
        return csvList;
    }

}
