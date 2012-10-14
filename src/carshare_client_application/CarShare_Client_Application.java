/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carshare_client_application;

import RESTClient.ReservationREST;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import service.CarShareWS;
import service.CarShareWS_Service;
import service.Reservation;

/**
 *
 * @author DELL
 */
public class CarShare_Client_Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Enter your choice");
        System.out.println("Press 1 to see all the reservations");
        System.out.println("Press 2 to see the member reservations");
        System.out.println("Press 3 to exit");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //int num=args.length;
        //int method=1;
        try{
        String num= bf.readLine();
            switch (num) {
                case "1":
                    System.out.println("Reservartions done in the application are");
                    String allReservation = getAllReservationFromWS();
                    System.out.println("Reservartions done in the application are"+allReservation);
                    String allReservationsFromREST = getAllReservationsFromREST();
                    System.out.println("All reservations from REST:\n" + allReservationsFromREST);
                    break;
                case "2":
                    String memberReservation=getMemberReservationFromWS();
                    System.out.println("Reservartions done in the application are"+memberReservation);
                    break;
                case "3":
                    System.exit(0);
            }
            
            
        }catch(IOException ex)
        {
           ex.printStackTrace(); 
        }
    }
    
    
    private static String getAllReservationsFromREST(){
        ReservationREST restClient = new ReservationREST();        
        String all = restClient.findAll_XML(String.class);
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser sp = factory.newSAXParser();
            XMLReader xr = sp.getXMLReader(); 
            

        }
         catch (Exception e) {
            e.printStackTrace();
          }
        
        
//        Iterator i = all.iterator();
//        String result = "";
//        while(i.hasNext())
//        {
//            Reservation item = (Reservation)i.next();
//            result += String.format("\n%1$2s - from: %2$2s - %3$2s", item.getMember().getFirstname(),item.getDateFrom(),item.getDateTo());
//        }
        return all;
    }

    private static String getAllReservationFromWS() {
        CarShareWS_Service service = new CarShareWS_Service();        
        CarShareWS port = service.getCarShareWSPort();
        List<Reservation> r = port.getAllReservation();
        Iterator i = r.iterator();
        String result = "";
        while(i.hasNext())
        {
            Reservation item = (Reservation) i.next();
            result += String.format("\n%1$2s - from: %2$2s - %3$2s", item.getMember().getFirstname(),item.getDateFrom(),item.getDateTo());
        }
        return result;
    }

    private static String getMemberReservationFromWS() {
        
        CarShareWS_Service service = new CarShareWS_Service();        
        CarShareWS port = service.getCarShareWSPort();
         List<Reservation> r = port.getMemberReservation("501");
        return "good member";
    }
    
}
