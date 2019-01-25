package com.company;

import java.io.*;
import java.net.*;

public class BusSearch {

    public static void main(String[] args) {
        try {
            String text = getWebPageSource("https://www.communitytransit.org/busservice/schedules/");

            boolean done = false;
            do {
                showMenu();
                String city = readLine();
                switch (city.charAt(0)) {
                    case 'q':
                        done = true;
                        break;
                    default:
                        boolean found = Menu2.citySearch(city.charAt(0), text);
                        if(found) {
                            System.out.print("Please enter a route: ");
                            String route = readLine();
                            Menu2.routeSearch(route, text);
                        } else {
                            System.out.println("No destinations found.");
                        }
                        break;
                }

            } while (!done);
        } catch(Exception e) {
            System.out.println("No Connection");
        }
    }

    private static void showMenu() {
        System.out.println("************");
        System.out.println(" BUS SEARCH");
        System.out.println("************");
        System.out.print("Please enter a capital letter to search cities, 'q' to quit: ");
    }

    private static String readLine() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr, 1);
        String line = "";
        try {
            line = br.readLine();
        } catch (IOException e) {
            System.out.println("Error in SimpleIO.readLine: " +"IOException was thrown");
            System.exit(1);
        }
        return line;
    }

    public static String getWebPageSource(String webPage) throws Exception {
        //Just copied from given data
        URLConnection bc = new URL(webPage).openConnection();
        bc.setRequestProperty("user-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        BufferedReader in = new BufferedReader(new InputStreamReader(bc.getInputStream()));

        String inputLine = "";
        String text = "";
        while ((inputLine = in.readLine()) != null) {
            text += inputLine;
        }
        in.close();

        return text;
    }
}
