package Open;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.XML;

public class Processing {
   
    public String apiProcessing(String zipcode,String Category)
    {   
       
        String output="";
        try{
        
        URL link = new URL("http://api2.yp.com/listings/v1/coupons?format=XML&key=24c9b4p9qk&searchloc="+zipcode+"&term="+Category);
        HttpURLConnection conn = (HttpURLConnection)link.openConnection();
        conn.connect();
        InputStream in = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
      
        String line = null,outputXML = null;
        
         while ((line = br.readLine()) != null) {
//          System.out.println(line);
          outputXML+=line;
        }
        org.json.JSONObject jobj =  XML.toJSONObject(outputXML);
        
        outputXML = jobj.toString();
        System.out.println("Output ZML :"+outputXML);
        //search the string
        
        
        String businessName[]=new String[20];
        String City[]=new String[20];
        String couponURL[]=new String[20];
        String streetName[]=new String[20];
        String PhoneNumber[]=new String[20];
        String WebsiteURL[]=new String[20];
        String couponDescription[]=new String[20];
        
        int temp=0;
        
        while(outputXML.indexOf("businessName")!=-1){
             System.out.println("*******tempoutputXML\n");
        businessName[temp] = findValue("businessName", outputXML);
        City[temp] = findValue("city", outputXML);
        couponURL[temp] = findValue("couponURL", outputXML);
        streetName[temp] = findValue("street", outputXML);
        PhoneNumber[temp] = findValue("phone", outputXML);
        WebsiteURL[temp] = findValue("websiteURL", outputXML);
        couponDescription[temp] = findValue("couponDescription", outputXML);
        
        output =output+ "\n businessName    :"+businessName[temp]+"<br>\n"
                       +"City            :"+City[temp]+"<br>\n"
                       +"Coupon URL      :<a href="+couponURL[temp]+">Click here</a><br>\n"
                       +"Street Name     :"+streetName[temp]+"<br>\n"
                       +"Phone Number    :"+PhoneNumber[temp]+"<br>\n"
                       +"Website URL     :<a href="+WebsiteURL[temp]+">Click here</a><br>\n"
                       +"couponDescription:"+couponDescription[temp]+"<br><br>";
        //delete logic
//        int len;
//        len=outputXML.indexOf("longitude");
//        len=len+"longitude".length()+12;
//        outputXML=outputXML.substring(len, outputXML.length());
        outputXML=outputXML.replaceFirst("businessName", "");
        outputXML=outputXML.replaceFirst("city", "");
        outputXML=outputXML.replaceFirst("couponURL", "");
        outputXML=outputXML.replaceFirst("street", "");
        outputXML=outputXML.replaceFirst("phone", "");
        outputXML=outputXML.replaceFirst("websiteURL", "");
        outputXML=outputXML.replaceFirst("couponDescription", "");
        //increment
        System.out.println("Returant"+temp+"\n"+output+"\n**********");
        temp++;
        }
         
        }
        catch(Exception exp)
        {
            System.out.println(exp.getMessage());
        }
       return output;
        
    }
    
    
    public String findValue(String key,String outputXML)
    {
        String value="";
        int temp;
        temp=outputXML.indexOf(key);
        temp=temp+key.length()+3;
        
        while(outputXML.charAt(temp)!='\"')
        {
            value+=outputXML.charAt(temp++);
        }
        
        return value;
    }
    
}
