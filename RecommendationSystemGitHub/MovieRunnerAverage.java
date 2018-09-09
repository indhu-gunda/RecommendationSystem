
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage 
{
  public static void main(String [ ] args)
  {
      MovieRunnerAverage mra = new MovieRunnerAverage();
      mra.printAverageRatings();
      mra.getAverageRatingOneMovie();
  }
  public void printAverageRatings()
  {
    SecondRatings sr = new SecondRatings("ratedmoviesfull.csv","ratings.csv");
    System.out.println("number of movies "+ sr.getMovieSize());
    System.out.println("number of raters "+ sr.getRaterSize());
    ArrayList <Rating> avgRating = sr.getAverageRatings(12);
    Collections.sort(avgRating);
    for(Rating r : avgRating)
    {
        System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));
    }
    System.out.println("done");
  }
  public void getAverageRatingOneMovie()
  {
    SecondRatings sr = new SecondRatings("ratedmoviesfull.csv","ratings.csv");
    ArrayList <Rating> avgRating = sr.getAverageRatings(3);
    for(Rating r : avgRating)
    {
        if(sr.getTitle(r.getItem()).equals("Vacation"))
        {
           System.out.println("movie rating: " + r.getValue()); 
        }
    }
    
   
  }
}
