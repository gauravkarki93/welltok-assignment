package Models;

import java.util.LinkedHashMap;

public class DirectMail extends Channel{

    public DirectMail(){
        this.getBaseFee();
        this.rateMap = new LinkedHashMap<int[], Double>(); //initialize map
        getRatesFromDB("DM", createNewConnection());  //populate map from db
    }

    @Override
    void getBaseFee() {
        baseFee = 8000;
    }
  
}
