
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class SecondRatings 
{
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() 
    {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    public SecondRatings(String moviefile, String ratingsfile)
    {
        FirstRatings fr = new FirstRatings();
        myMovies= fr.loadMovies(moviefile);
        myRaters= fr.loadRaters(ratingsfile);
        
    }
    public int getMovieSize()
    {
        return myMovies.size();
    }
    public int getRaterSize()
    {
        return myRaters.size();
    }
    private double getAverageByID(String id, int minimalRaters)
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
        for(Movie m : myMovies)
        {
            double avg= getAverageByID(m.getID(),minimalRaters);
            if(avg !=0)
                avgRatings.add(new Rating(m.getID(),avg));
        }
        return avgRatings;
    }
    public String getTitle(String id)
    {
        for (Movie m : myMovies)
        {
            if(m.getID().equals(id))
            {
                return m.getTitle();
            }
        }
        return "Movie ID not found";
    }
    public String getID (String title)
    {
        for (Movie m : myMovies)
        {
            if(m.getTitle().equals(title))
            {
                return m.getID();
            }
        }
        return "Movie title not found";
    }
}
