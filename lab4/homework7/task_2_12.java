package lab4.homework7;

    import java.util.Arrays; 
import java.util.Calendar; 
import java.util.Date; 
 
public class task_2_12 { 
 
    public static void main(String[] args) { 
        Calendar calendar = Calendar.getInstance(); 
        calendar.set(Calendar.HOUR_OF_DAY, 12); 
 
        Airline airline1 = new Airline("1", "Boeing 737", calendar.getTime(), "Kyiv", new String[]{"Monday", "Tuesday",}); 
        calendar.set(Calendar.HOUR_OF_DAY, 14); 
        Airline airline2 = new Airline("2", "Airbus A320", calendar.getTime(), "Warsaw", new String[]{"Monday", "Wednesday", "Friday"}); 
        calendar.set(Calendar.HOUR_OF_DAY, 16); 
        Airline airline3 = new Airline("3", "Boeing 737", calendar.getTime(), "Kyiv", new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"}); 
        calendar.set(Calendar.HOUR_OF_DAY, 18); 
        Airline airline4 = new Airline("4", "Airbus A320", calendar.getTime(), "Kyiv", new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}); 
 
        Airline[] airlines = new Airline[]{airline1, airline2, airline3, airline4}; 
 
        System.out.println(Arrays.toString(findAirlinesByDestination(airlines, "Kyiv"))); 
        System.out.println(Arrays.toString(findAirlinesByDayOfWeek(airlines, "Monday"))); 
        System.out.println(Arrays.toString(findAirlinesByDayOfWeekAndDepartureTime(airlines, "Monday", new Date()))); 
    } 
 
    static Airline[] findAirlinesByDestination(Airline[] airlines, String destination) { 
        Airline[] result = new Airline[airlines.length]; 
        int p = 0; 
        for (Airline airline : airlines) { 
            if (airline.getDestination().equals(destination)) { 
                result[p] = airline; 
                p++; 
            } 
        } 
        return result; 
    } 
 
    static Airline[] findAirlinesByDayOfWeek(Airline[] airlines, String dayOfWeek) { 
        Airline[] result = new Airline[airlines.length]; 
        int p = 0; 
        for (Airline airline : airlines) { 
            for (String day : airline.getDaysOfWeek()) { 
                if (day.equals(dayOfWeek)) { 
                    result[p] = airline; 
                    p++; 
                } 
            } 
        } 
        return result; 
    } 
 
    static Airline[] findAirlinesByDayOfWeekAndDepartureTime(Airline[] airlines, String dayOfWeek, Date departureTime) { 
        Airline[] result = new Airline[airlines.length]; 
        int p = 0; 
        for (Airline airline : airlines) { 
            for (String day : airline.getDaysOfWeek()) { 
                if (day.equals(dayOfWeek) && airline.getDepartureTime().after(departureTime)) { 
                    result[p] = airline; 
                    p++; 
                } 
            } 
        } 
        return result; 
    } 
 
 
    static public class Airline { 
 
        private String raceNumber; 
        private String planeType; 
        private Date departureTime; 
 
        private String destination; 
        private String[] daysOfWeek; 
 
        public Airline(String raceNumber, String planeType, Date departureTime, String destination, String[] daysOfWeek) { 
            this.raceNumber = raceNumber; 
            this.planeType = planeType; 
            this.departureTime = departureTime; 
            this.destination = destination; 
            this.daysOfWeek = daysOfWeek; 
        } 
 
        public String getRaceNumber() { 
            return raceNumber; 
        } 
 
        public String getPlaneType() { 
            return planeType; 
        } 
 
        public Date getDepartureTime() { 
            return departureTime; 
        } 
 
        public String getDestination() { 
            return destination; 
        } 
 
        public String[] getDaysOfWeek() { 
            return daysOfWeek; 
        } 
 
        @Override 
        public String toString() { 
            return "Airline{" + 
                    "raceNumber='" + raceNumber + '\'' + 
                    '}'; 
        } 
    }
    
}
