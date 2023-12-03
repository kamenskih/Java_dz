package lab4.homework9;


    import java.util.Arrays; 
 
public class task_6_7 { 
 
    public static void main(String[] args) { 
        Ammunition ammunition1 = new Ammunition("Helmet", 100, 1); 
        Ammunition ammunition2 = new Ammunition("Gloves", 50, 0.5); 
        Ammunition ammunition3 = new Ammunition("Jacket", 200, 2); 
        Ammunition ammunition4 = new Ammunition("Boots", 150, 1.5); 
        Ammunition ammunition5 = new Ammunition("Pants", 100, 1); 
        Ammunition ammunition6 = new Ammunition("Glasses", 50, 0.5); 
 
        MotorcycleRider rider = new MotorcycleRider(new Ammunition[]{ammunition1, ammunition2, ammunition3, ammunition4, ammunition5, ammunition6}); 
 
        System.out.println("Rider's ammunition total price: " + rider.countTotalPrice()); 
        System.out.println("Rider's ammunition: "  + Arrays.toString(rider.getAmmunitions())); 
        System.out.println("Rider's ammunition sorted by weight: "); 
        rider.sortAmmunitionByWeight(); 
        System.out.println(Arrays.toString(rider.getAmmunitions())); 
 
        System.out.println("Rider's ammunition with price between 50 and 150: "); 
        System.out.println(Arrays.toString(rider.findAmmunitionByPrice(50, 150))); 
    } 
 
    static class MotorcycleRider { 
        private Ammunition[] ammunitions; 
 
        public MotorcycleRider(Ammunition[] ammunitions) { 
            this.ammunitions = ammunitions; 
        } 
 
        public Ammunition[] getAmmunitions() { 
            return ammunitions; 
        } 
 
        public void addAmmunition(Ammunition ammunition) { 
            this.ammunitions = Arrays.copyOf(ammunitions, ammunitions.length + 1); 
            this.ammunitions[ammunitions.length - 1] = ammunition; 
        } 
 
        public double countTotalPrice() { 
            double totalPrice = 0; 
            for (Ammunition ammunition : ammunitions) { 
                totalPrice += ammunition.getPrice(); 
            } 
            return totalPrice; 
        } 
 
        public void sortAmmunitionByWeight() { 
            Arrays.sort(ammunitions, (o1, o2) -> (int) (o1.getWeight() - o2.getWeight())); 
        } 
 
        public Ammunition[] findAmmunitionByPrice(double minPrice, double maxPrice) { 
            Ammunition[] result = new Ammunition[ammunitions.length]; 
            int p = 0; 
            for (Ammunition ammunition : ammunitions) { 
                if (ammunition.getPrice() >= minPrice && ammunition.getPrice() <= maxPrice) { 
                    result[p] = ammunition; 
                    p++; 
                } 
            } 
            return result; 
        } 
    } 
 
 
    static class Ammunition { 
 
        private String name; 
        private double price; 
        private double weight; 
 
        public Ammunition(String name, double price, double weight) { 
            this.name = name; 
            this.price = price; 
            this.weight = weight; 
        } 
 
        public String getName() { 
            return name; 
        } 
 
        public double getPrice() { 
            return price; 
        } 
 
        public double getWeight() { 
            return weight; 
        } 
 
        @Override 
        public String toString() { 
            return "Ammunition{" + 
                    "name='" + name + '\'' + 
                    '}'; 
        } 
    } 
}
    
