package Models;

import java.util.LinkedHashMap;

public class SMS extends Channel{

    public SMS(){
        this.getBaseFee();
        rateMap = new LinkedHashMap<int[], Double>(); //initialize map
        getRatesFromDB("SMS", createNewConnection());  //populate map from db
    }

    @Override
    void getBaseFee() {
        baseFee = 1800;
    }
 
}
