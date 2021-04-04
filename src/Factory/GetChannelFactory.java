package Factory;

import Models.*;

public class GetChannelFactory {
    public Channel getChannel(String planType){  
        if(planType == null){  
         return null;  
        }  
        if(planType.equalsIgnoreCase("EM")) {  
              return new Email();  
            }   
        else if(planType.equalsIgnoreCase("SMS")){  
              return new SMS();  
          }   
        else if(planType.equalsIgnoreCase("DM")) {  
              return new DirectMail();  
        }  
    return null;  
  }  
}
