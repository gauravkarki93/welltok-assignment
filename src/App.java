import Factory.GetChannelFactory;
import Models.Channel;

public class App {
    static GetChannelFactory factory = new GetChannelFactory();
    
    public static void main(String[] args) throws Exception {                
        int contacts = Integer.parseInt(args[0]);     
        String channels = args[1];
        System.out.println("total campaign cost :"+getTotalCampaignCost(contacts, channels));                  
        //test();
    }

    //Use this method to run test cases
    public static void test(){
        try {
            //Testing edge case
            try{
                System.out.println("Test 0");                 
                System.out.println("total campaign cost: "+getTotalCampaignCost(22000, ""));
            }
            catch(Exception e){
                System.out.println(e);
            }
            
            //Testing edge case
            try {
                System.out.println("Test 1"); 
                System.out.println("total campaign cost: "+getTotalCampaignCost(-1, "EM")); 
            } catch (Exception e) {
                System.out.println(e);
            }

            //Only base fees
            System.out.println("Test 2");
            System.out.println("total campaign cost: "+getTotalCampaignCost(5000, "EM|SMS"));   

            //Higher costs
            System.out.println("Test 3");
            System.out.println("total campaign cost: "+getTotalCampaignCost(22000, "EM|SMS"));    

            System.out.println("Test 4");
            System.out.println("total campaign cost: "+getTotalCampaignCost(15000, "EM|SMS|DM")); 

            System.out.println("Test 5");
            System.out.println("total campaign cost: "+getTotalCampaignCost(55001, "DM|SMS")); 
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }

     /*
    Input : Integer num of contacts, and string channel ex:(22000, "EM|SMS")
    Output: Total campaign cost    
    */
    public static double getTotalCampaignCost(int contacts, String channels) throws Exception{   
        System.out.println("Total Contacts : "+contacts);
        System.out.println("Channels : "+channels);
        if(contacts <= 0 || channels.isEmpty())        
            throw new Exception("Invalid input parameters");
        
        double totalAmount = 0.0;
        try {           
            for(String ch_type : channels.split("\\|")){
                int no_of_contacts = contacts;                
                Channel channel = factory.getChannel(ch_type);
                totalAmount += channel.findTotalCost(no_of_contacts);
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalAmount;
    }
}
