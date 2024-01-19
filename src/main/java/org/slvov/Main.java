package org.slvov;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;




//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
//        System.out.println("ars: " + args.length + " " + args[0]);
        /*Was data file provided?*/
        if (args.length == 0) {
//            System.out.println("usage: tickets tickets.json");
            System.exit(1);
        } else {
            try {
//                System.out.println("reading: " + args[0]);

                String json = new String(Files.readAllBytes(Path.of(args[0])));
//                System.out.println("json: " + json);

                Gson gson = new Gson();
                Type listType = new TypeToken<List<Ticket>>(){}.getType();
                List<Ticket> ltickets = gson.fromJson(json, listType);
                /* Список билетов Владивосток - Тель-Авив. */
                List<Ticket> vtvtickets = (List<Ticket>) ltickets.stream().filter(t ->{return  t.get_origin().equals("VVO") & t.get_destination().equals("TLV");}).toList();
//                long duration = ltickets.get(0).flytime();
                System.out.println("ltickets: " + vtvtickets.size());
                /* Группировка по перевозчикам для определения минимальной продолжительности полета для каждого. */
                Map<String, List<Ticket>> grouped = vtvtickets.stream().collect(Collectors.groupingBy(Ticket::get_carrier));
                /*Минимальная продолжительность полета*/
                System.out.println("The best duration of the flight by carriers: ");
                for(Map.Entry<String, List<Ticket>> carrier : grouped.entrySet())
                {
                    Collections.sort(carrier.getValue(), new FlytimeComparator());
                    String result = String.format("%d hours, %d minutes", carrier.getValue().get(0).flytime()/60, carrier.getValue().get(0).flytime() % 60);


                    System.out.println("carrier: " + carrier.getKey() + " - " + result);
                }
                /* Разница между средней ценой и медианой. */
                List<Ticket> tvtickets = new ArrayList<Ticket>(vtvtickets);
                Collections.sort(tvtickets, new PriceComparator());
//				int medianIndex = tvtickets.size()/2 + 1;
                long median = tvtickets.size() % 2 == 1? tvtickets.get(tvtickets.size()/2).get_price() : (tvtickets.get(tvtickets.size()/2).get_price() + tvtickets.get(tvtickets.size()/2 - 1).get_price())/2;
                long middle = tvtickets.stream().filter(t -> t.get_price() > 0).mapToLong(t -> { return t.get_price();}).sum() /tvtickets.size();
                System.out.println("difference between the middle price and median price: " + (middle - median));


            } catch (Exception ex) {
                System.out.println("Exception: " + ex);
                System.exit(2);
            }


        }
    }
}

