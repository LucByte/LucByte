
import java.util.HashMap;
import java.util.Map;

public class IdentityMap {
    private static IdentityMap instance = null;
    private Map<String, rent> bikeMap = new HashMap<>();
    //Creating new identity map
    private IdentityMap(){
        bikeMap = new HashMap<>();
    }
    //ensures only one instance at a time
    public static IdentityMap getInstance(){
        if(instance==null){
            instance = new IdentityMap();
        }
        return instance;
    }
    //insert mapping into map
    public void addRentedBike(String bikeID, rent rental){
        bikeMap.put(bikeID, rental);
    }
    //retrieve rental object from map
    public rent getRental(String bikeID){
        return bikeMap.get(bikeID);
    }
    //checking if map contains bicycle record being retrieved
    public boolean isBikeRented(String bikeID){
        return bikeMap.containsKey(bikeID);
    }
}
