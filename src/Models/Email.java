package Models;
import java.util.LinkedHashMap;

public class Email extends Channel{

    public Email(){        
        this.getBaseFee();
        this.rateMap = new LinkedHashMap<int[], Double>(); //initialize map
        getRatesFromDB("EM", createNewConnection());  //populate map from db
    }

    @Override
    void getBaseFee() {
        baseFee = 1500;
    }
       
}
