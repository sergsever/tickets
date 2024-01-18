
package org.slvov;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Ticket
{
private String origin;
private String origin_name;
private String destination;
private String destination_name;
private String departure_date;
private String departure_time;
private String arrival_date;
private String arrival_time;
private String carrier;
private int stops;
private long price;
public String get_origin(){
    return origin;
}
public String get_destination(){return destination;}
public String get_carrier(){return carrier;}
public long get_price(){return price;}
 public void set_origin(String origin){
    this.origin = origin;
 }

 public long flytime()
 {
     LocalTime departure = LocalTime.parse(departure_time, DateTimeFormatter.ofPattern("[HH:mm]" + "[hh:mm]" + "[H:mm]" + "[h:mm]"));
     LocalTime arrival = LocalTime.parse(arrival_time, DateTimeFormatter.ofPattern("[H:mm]" + "[hh:mm]" + "[h:mm]"));
     long duration = Duration.between(departure, arrival).toMinutes();
     return duration;
 }





}

