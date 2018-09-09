
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {
  public static void main(String [ ] args)
  {
      MovieRunnerWithFilters mr = new MovieRunnerWithFilters();
      //System.out.println("all");
      //mr.printAverageRatings();
      //System.out.println("year");
      //mr.printAverageRatingsByYear();
      //System.out.println("genre");
      //mr.printAverageRatingsByGenre();
      //System.out.println("minutes");
      //mr.printAverageRatingsByMinutes();
      //System.out.println("director");
      //mr.printAverageRatingsByDirectors();
      //System.out.println("year & genre");
      //mr.printAverageRatingsByYearAfterAndGenre();
      System.out.println("director & minutes");
      mr.printAverageRatingsByDirectorsAndMinutes();
  }
  public void printAverageRatings()
  {
    ThirdRatings tr = new ThirdRatings("ratings.csv");
    System.out.println("number of raters "+ tr.getRaterSize());
    MovieDatabase.initialize("ratedmoviesfull.csv");
    System.out.println("number of movies "+ MovieDatabase.size());
    ArrayList <Rating> avgRating = tr.getAverageRatings(35);
    Collections.sort(avgRating);
    System.out.println("found "+avgRating.size()+" movies");
    for(Rating r : avgRating)
    {
        System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
    }
    System.out.println("done");
  }
  public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria)
  {
    ArrayList<Rating> avgRatings = new ArrayList<Rating>();
    ThirdRatings tr= new ThirdRatings("ratings.csv");
    ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
    for(String m : movies)
    {
     double avg= tr.getAverageByID(m,minimalRaters);
     if(avg !=0)
       avgRatings.add(new Rating(m,avg));
    }
    return avgRatings;
  }
  public void printAverageRatingsByYear()
  {
    ThirdRatings tr = new ThirdRatings("ratings.csv");
    System.out.println("number of raters "+ tr.getRaterSize());
    MovieDatabase.initialize("ratedmoviesfull.csv");
    System.out.println("number of movies "+ MovieDatabase.size());
    ArrayList<Rating> avgRatings= getAverageRatingsByFilter(20,new YearAfterFilter(2000));
    Collections.sort(avgRatings);
    if(avgRatings.size()!=1)
        System.out.println("found "+avgRatings.size()+" movies");
    else
        System.out.println("found 1 movie");
    for(Rating r : avgRatings)
    {
       Movie m=MovieDatabase.getMovie(r.getItem());
       System.out.println(r.getValue()+ " "+m.getYear()+" "+m.getTitle());
    }
  }
  public void printAverageRatingsByGenre()
  {
    ThirdRatings tr = new ThirdRatings("ratings.csv");
    System.out.println("number of raters "+ tr.getRaterSize());
    MovieDatabase.initialize("ratedmoviesfull.csv");
    System.out.println("number of movies "+ MovieDatabase.size());
    ArrayList<Rating> avgRatings= getAverageRatingsByFilter(20,new GenreFilter("Comedy"));
    Collections.sort(avgRatings);
    if(avgRatings.size()!=1)
        System.out.println("found "+avgRatings.size()+" movies");
    else
        System.out.println("found 1 movie");
    for(Rating r : avgRatings)
    {
       Movie m=MovieDatabase.getMovie(r.getItem());
       System.out.println(r.getValue()+ " "+m.getTitle());
       System.out.println("   "+m.getGenres());
    } 
  }
  public void printAverageRatingsByMinutes()
  {
    ThirdRatings tr = new ThirdRatings("ratings.csv");
    System.out.println("number of raters "+ tr.getRaterSize());
    MovieDatabase.initialize("ratedmoviesfull.csv");
    System.out.println("number of movies "+ MovieDatabase.size());
    ArrayList<Rating> avgRatings= getAverageRatingsByFilter(5,new MinutesFilter(105,135));
    Collections.sort(avgRatings);
    if(avgRatings.size()!=1)
        System.out.println("found "+avgRatings.size()+" movies");
    else
        System.out.println("found 1 movie");
    for(Rating r : avgRatings)
    {
       Movie m=MovieDatabase.getMovie(r.getItem());
       System.out.println(r.getValue()+ "  Time: "+ m.getMinutes()+" "+m.getTitle());
    } 
      
  }
  public void printAverageRatingsByDirectors()
  {
    ThirdRatings tr = new ThirdRatings("ratings.csv");
    System.out.println("number of raters "+ tr.getRaterSize());
    MovieDatabase.initialize("ratedmoviesfull.csv");
    System.out.println("number of movies "+ MovieDatabase.size());
    ArrayList<Rating> avgRatings= getAverageRatingsByFilter(4,new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
    Collections.sort(avgRatings);
    if(avgRatings.size()!=1)
        System.out.println("found "+avgRatings.size()+" movies");
    else
        System.out.println("found 1 movie");
    for(Rating r : avgRatings)
    {
       Movie m=MovieDatabase.getMovie(r.getItem());
       System.out.println(r.getValue()+ " "+m.getTitle());
       System.out.println("   "+m.getDirector());
    } 
  }
  public void printAverageRatingsByYearAfterAndGenre()
  {
    ThirdRatings tr = new ThirdRatings("ratings.csv");
    System.out.println("number of raters "+ tr.getRaterSize());
    MovieDatabase.initialize("ratedmoviesfull.csv");
    System.out.println("number of movies "+ MovieDatabase.size());
    AllFilters f = new AllFilters();
    f.addFilter(new YearAfterFilter(1990));
    f.addFilter(new GenreFilter("Drama"));
    ArrayList<Rating> avgRatings= getAverageRatingsByFilter(8,f);
    Collections.sort(avgRatings);
    if(avgRatings.size()!=1)
        System.out.println("found "+avgRatings.size()+" movies");
    else
        System.out.println("found 1 movie");
    /*for(Rating r : avgRatings)
    {
       Movie m=MovieDatabase.getMovie(r.getItem());
       System.out.println(r.getValue()+ " "+m.getYear()+" "+m.getTitle());
       System.out.println("   "+ m.getGenres());
    }*/
  }
  public void printAverageRatingsByDirectorsAndMinutes()
  {
    ThirdRatings tr = new ThirdRatings("ratings.csv");
    System.out.println("number of raters "+ tr.getRaterSize());
    MovieDatabase.initialize("ratedmoviesfull.csv");
    System.out.println("number of movies "+ MovieDatabase.size());
    AllFilters f = new AllFilters();
    f.addFilter(new MinutesFilter(90,180));
    f.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
    ArrayList<Rating> avgRatings= getAverageRatingsByFilter(3,f);
    Collections.sort(avgRatings);
    if(avgRatings.size()!=1)
        System.out.println("found "+avgRatings.size()+" movies");
    else
        System.out.println("found 1 movie");
    /*for(Rating r : avgRatings)
    {
       Movie m=MovieDatabase.getMovie(r.getItem());
       System.out.println(r.getValue()+ " Time: "+m.getMinutes()+" "+m.getTitle());
       System.out.println("   "+ m.getDirector());
    }*/
  }
}
