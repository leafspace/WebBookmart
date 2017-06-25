package cn.cslg.WebBookmart.model;

import java.util.LinkedList;

public class RecycleBin {
    public LinkedList<Classification> classifications;

    public RecycleBin(){
        classifications = new LinkedList<Classification>();
    }

    public int searchClassification(String classificationName){
        for(int i = 0; i < classifications.size(); ++i){
            if(classificationName.equals(classifications.get(i).getName())){
                return i;
            }
        }
        return -1;
    }
}
