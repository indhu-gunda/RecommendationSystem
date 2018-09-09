
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() 
    {
        // default constructor
        this("ratings.csv");
    }
    public ThirdRatings(String ratingsfile)
    {
        FirstRatings fr = new FirstRatings();
        myRaters= fr.loadRaters(ratingsfile);
        
    }
    public int getRaterSize()
    {
        return myRaters.size();
    }
    public double getAverageByID(String id, int minimalRaters)
    {
        ArrayList<Double> ratings= new ArrayList<Double>();
        double total=0;
        for(EfficientRater r: myRaters)
        {
            ArrayList<String> temp = r.getItemsRated();
            if( temp.contains(id) )
            {
                ratings.add(r.getRating(id));
            }
        }
        if(ratings.size()>=minimalRaters)
        {
            for(double d : ratings)
            {
                total+=d;
            }
            double avg= total/ratings.size();
            return avg;
        }
        return 0.0;
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String m : movies)
        {
            double avg= getAverageByID(m,minimalRaters);
            if(avg !=0)
                avgRatings.add(new Rating(m,avg));
        }
        return avgRatings;
    }
}
