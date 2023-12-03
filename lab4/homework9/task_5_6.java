package lab4.homework9;

    import java.util.Arrays; 
 
public class task_5_6 { 
 
    public static void main(String[] args) { 
        Satellite[] satellites = new Satellite[]{new Satellite("Moon"), new Satellite("Phobos"), new Satellite("Deimos")}; 
 
        Planet[] planets = new Planet[]{new Planet("Earth", satellites)}; 
 
        Star star = new Star("Sun"); 
 
        SolarSystem solarSystem = new SolarSystem(star, planets); 
 
        System.out.println("Solar system:"); 
 
        solarSystem.printStarName(); 
        solarSystem.printPlanetsCount(); 
        solarSystem.addPlanet(new Planet("Mars", new Satellite[]{new Satellite("Phobos"), new Satellite("Deimos")})); 
        solarSystem.printPlanetsCount(); 
    } 
 
 
    static class SolarSystem { 
        private Star star; 
        private Planet[] planets; 
 
        public SolarSystem(Star star, Planet[] planets) { 
            this.star = star; 
            this.planets = planets; 
        } 
 
        public Star getStar() { 
            return star; 
        } 
 
        public Planet[] getPlanets() { 
            return planets; 
        } 
 
        public void printStarName() { 
            System.out.println("Star name: " + star.getName()); 
        } 
 
        public void printPlanetsCount() { 
            System.out.println("Planets count: " + planets.length); 
        } 
 
        public void addPlanet(Planet planet) { 
            this.planets = Arrays.copyOf(planets, planets.length + 1); 
            this.planets[planets.length - 1] = planet; 
        } 
    } 
 
    static class Planet { 
        private String name; 
        private Satellite[] satellites; 
 
        public Planet(String name, Satellite[] satellites) { 
            this.name = name; 
            this.satellites = satellites; 
        } 
 
        public String getName() { 
            return name; 
        } 
 
        public Satellite[] getSatellites() { 
            return satellites; 
        } 
    } 
 
    static class Star { 
        private String name; 
 
        public Star(String name) { 
            this.name = name; 
        } 
 
        public String getName() { 
            return name; 
        } 
    } 
 
    static class Satellite { 
        private String name; 
 
        public Satellite(String name) { 
            this.name = name; 
        } 
 
        public String getName() { 
            return name; 
        } 
    } 
 
 
}
    
