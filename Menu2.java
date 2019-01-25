package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu2 {

        public static boolean citySearch(char city, String text){

            Boolean found = false;
            //This pattern finds the cities and grabs relevant text
            Pattern ptr = Pattern.compile("<h3>(" + city + ".*?)</h3>(.*?)<h3>");
            Matcher match = ptr.matcher(text);

            while (match.find()) {
                found = true;
                System.out.println("DESTINATION: " + match.group(1));

                //This pattern prints out the bus routes under each city
                Pattern routes = Pattern.compile("<a.*?>(.*?)</a>");
                Matcher match2 = routes.matcher(match.group(0));

                while(match2.find()) {
                    System.out.println("ROUTE: " + match2.group(1));
                }

            }

            return found;

        }

        public static void routeSearch(String route, String text) {
            try {
                int rt = Integer.parseInt(route);

                //This pattern searches for the bus route, though it has to do this in the whole document.
                Pattern ptr = Pattern.compile("<strong><a href=\"(\\S*)\">" + route + "</a>");
                Matcher match = ptr.matcher(text);

                if(match.find()) {
                    String website = BusSearch.getWebPageSource("https://www.communitytransit.org/busservice" + match.group(1));

                    System.out.println("The website for your route is : https://www.communitytransit.org/busservice" + match.group(1));
                    System.out.println();

                    //This pattern finds the destinations
                    Pattern dest = Pattern.compile("Weekday<small>(.*?)</small>.*?<tr>(.*?)</tr>");
                    Matcher match2 = dest.matcher(website);

                    while(match2.find()) {
                        System.out.println("DESTINATION: " + match2.group(1));

                        //This Pattern find each stop
                        Pattern stop = Pattern.compile("(\\d)</strong>.*?<p>(.*?)</p>");
                        Matcher stops = stop.matcher(match2.group(0));

                        while(stops.find()) {
                            System.out.println("STOP: " + stops.group(1) + ", " + stops.group(2));
                        }

                        System.out.println("");

                    }

                }

            } catch(Exception e) {
                System.out.println("This is not a valid route.");
            }
        }
}
