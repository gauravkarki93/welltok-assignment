package Models;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.io.FileInputStream;
import java.sql.*;

public abstract class Channel {
    double baseFee;
    LinkedHashMap<int[], Double> rateMap;    

    //Set base fee
    abstract void getBaseFee();    
    
    //Creates connection to local postgres db
    public static Connection createNewConnection(){
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            props.load(new FileInputStream("db.properties"));
            String url = props.getProperty("dburl");                                 
            conn = DriverManager.getConnection(url, props);            
        } catch (Exception e) {      
            e.printStackTrace();
        }        
        return conn;
    }

    //Populate rates from DB into a Map
    public void getRatesFromDB(String channelType, Connection connection){
        String columnName = "";
        PreparedStatement st = null;
        ResultSet rs = null;

        switch (channelType) {
            case "EM":
                columnName = "Email";
                break;
            case "SMS":
                columnName = "SMS";
                 break;
            case "DM":
                columnName = "Direct_Mail";
                break;
            default:
                break;
        }
        
        try {
            st = connection.prepareStatement("SELECT * FROM campaign_rates");        
            rs = st.executeQuery();
            while (rs.next())
            {   
                String[] split = rs.getString(1).split(",");                
                int lowerLimit = Integer.parseInt(split[0].substring(1));                
                int upperLimit = split[1].length() - 1 == 0 ? Integer.MAX_VALUE : Integer.parseInt(split[1].substring(0, split[1].length() - 1));
                int range[] = {lowerLimit, upperLimit};
                double rate = Double.parseDouble(rs.getString(columnName));      
                this.rateMap.put(range, rate);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            //Close result set and prepared statements
            try {
                if(connection != null)
                    connection.close();
                if(rs != null)
                    rs.close();
                if(st != null)
                    st.close();
            } catch (SQLException e) {                
                e.printStackTrace();
            }            
        }
    }

    //Calculates total cost from a single channel (would be called from sub-objects)
    public double findTotalCost(int contacts){
        double cost = baseFee; //starts at base cost   
        for(Map.Entry<int[], Double> entry : rateMap.entrySet()){
            if(contacts <= 0)
                break;
            int range[] = entry.getKey();
            double rate = entry.getValue();
            // System.out.println("range: "+range[0]+","+range[1]);     
            // System.out.println("rate: "+rate);     
            if(rate == 0.0){
                contacts -= range[1];
                continue;
            }
            int multiplier = contacts >= range[1] - range[0] + 1 ? range[1] - range[0] + 1 : contacts;
            //System.out.println("multiplier: "+multiplier);
            cost += rate * multiplier;
            contacts -= multiplier;
            //System.out.println("cost :"+cost+"| left:"+contacts);
        }
        return cost;
    }    
}
