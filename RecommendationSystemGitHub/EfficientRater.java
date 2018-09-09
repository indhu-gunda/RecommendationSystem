
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRatings;
    
    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }
    @Override
    public void addRating(String item, double rating) {
        myRatings.put(item,new Rating(item,rating));
    }
    @Override
    public boolean hasRating(String item) {
        if(myRatings.containsKey(item))
            return true;
        else
            return false;
    }
    @Override
    public String getID() {
        return myID;
    }
    @Override
    public double getRating(String item) {
        if(myRatings.containsKey(item))
          return myRatings.get(item).getValue();
      
        else 
            return -1;
    }
    @Override
    public int numRatings() {
        return myRatings.size();
    }
    @Override
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String s : myRatings.keySet()){
            list.add(myRatings.get(s).getItem());
        }
        
        return list;
    }
    
}
