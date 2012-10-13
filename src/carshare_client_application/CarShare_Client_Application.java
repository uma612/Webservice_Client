/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carshare_client_application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
                    String allReservation = getAllReservation();
                    System.out.println("Reservartions done in the application are"+allReservation);
                    break;
                case "2":
                    String memberReservation=getMemberReservation();
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

    private static String getAllReservation() {
        car.services.CarShareWS service = new car.services.CarShareWS();
        car.services.CarShare port = service.getCarSharePort();
        return port.getAllReservation();
    }

    private static String getMemberReservation() {
        car.services.CarShareWS service = new car.services.CarShareWS();
        car.services.CarShare port = service.getCarSharePort();
        return port.getMemberReservation();
    }
}
