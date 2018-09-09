
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings 
{
  public static void main(String [ ] args)
  {
      MovieRunnerSimilarRatings mr = new MovieRunnerSimilarRatings();
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
      //System.out.println("director & minutes");
      //mr.printAverageRatingsByDirectorsAndMinutes();
      //mr.printSimilarRatings();
      //mr.printSimilarRatingsByGenre();
      mr.printSimilarRatingsByDirector();
      //mr.printSimilarRatingsByGenreAndMinutes();
      //mr.printSimilarRatingsByYearAfterAndMinutes();
  }  
   public void printAverageRatings()
  {
    FourthRatings fr = new FourthRatings();
    RaterDatabase.initialize("ratings.csv");
    MovieDatabase.initialize("ratedmoviesfull.csv");
    System.out.println("number of movies "+ MovieDatabase.size());
    System.out.println("number of raters "+ RaterDatabase.size());
    ArrayList <Rating> avgRating = fr.getAverageRatings(35);
    Collections.sort(avgRating);
    System.out.println("found "+avgRating.size()+" movies");
    for(Rating r : avgRating)
    {
        System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
    }
    System.out.println("done");
  }
    public void printAverageRatingsByYearAfterAndGenre()
  {
    FourthRatings fr = new FourthRatings();
    RaterDatabase.initialize("ratings.csv");
    System.out.println("number of raters "+ RaterDatabase.size());
    MovieDatabase.initialize("ratedmoviesfull.csv");
    System.out.println("number of movies "+ MovieDatabase.size());
    AllFilters f = new AllFilters();
    f.addFilter(new YearAfterFilter(1990));
    f.addFilter(new GenreFilter("Drama"));
    ArrayList<Rating> avgRatings= fr.getAverageRatingsByFilter(8,f);
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
    FourthRatings fr = new FourthRatings();
    RaterDatabase.initialize("ratings.csv");
    System.out.println("number of raters "+ RaterDatabase.size());
    MovieDatabase.initialize("ratedmoviesfull.csv");
    System.out.println("number of movies "+ MovieDatabase.size());
    AllFilters f = new AllFilters();
    f.addFilter(new MinutesFilter(90,180));
    f.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
    ArrayList<Rating> avgRatings= fr.getAverageRatingsByFilter(3,f);
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
  public void printSimilarRatings()
  {
    FourthRatings fr = new FourthRatings();
    RaterDatabase.initialize("ratings.csv");
    MovieDatabase.initialize("ratedmoviesfull.csv");
    System.out.println("number of movies "+ MovieDatabase.size());
    System.out.println("number of raters "+ RaterDatabase.size());
    ArrayList <Rating> simRating = fr.getSimilarRatings("71",20,5);
    System.out.println("found "+simRating.size()+" movies");
    for(Rating r : simRating)
    {
        System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
    }
    System.out.println("done");
  }
  public void printSimilarRatingsByGenre()
  {
    FourthRatings fr = new FourthRatings();
    RaterDatabase.initialize("ratings.csv");
    MovieDatabase.initialize("ratedmoviesfull.csv");
    System.out.println("number of movies "+ MovieDatabase.size());
    System.out.println("number of raters "+ RaterDatabase.size());
    ArrayList <Rating> simRating = fr.getSimilarRatingsByFilter("964",20,5,new GenreFilter("Mystery"));
    System.out.println("found "+simRating.size()+" movies");
    for(Rating r : simRating)
    {
        Movie m=MovieDatabase.getMovie(r.getItem());
        System.out.println(r.getValue() + " " + m.getTitle());
        System.out.println("   "+ m.getGenres());
    }
    System.out.println("done");
  }
  public void printSimilarRatingsByDirector()
  {
    FourthRatings fr = new FourthRatings();
    RaterDatabase.initialize("ratings.csv");
    MovieDatabase.initialize("ratedmoviesfull.csv");
    System.out.println("number of movies "+ MovieDatabase.size());
    System.out.println("number of raters "+ RaterDatabase.size());
    ArrayList <Rating> simRating = fr.getSimilarRatingsByFilter("120",10,2,new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
    System.out.println("found "+simRating.size()+" movies");
    for(Rating r : simRating)
    {
        Movie m=MovieDatabase.getMovie(r.getItem());
        System.out.println(r.getValue() + " " + m.getTitle());
        System.out.println("   "+ m.getDirector());
    }
    System.out.println("done");
  }
  public void printSimilarRatingsByGenreAndMinutes()
  {
    FourthRatings fr = new FourthRatings();
    RaterDatabase.initialize("ratings.csv");
    MovieDatabase.initialize("ratedmoviesfull.csv");
    System.out.println("number of movies "+ MovieDatabase.size());
    System.out.println("number of raters "+ RaterDatabase.size());
    AllFilters f = new AllFilters();
    f.addFilter(new MinutesFilter(80,160));
    f.addFilter(new GenreFilter("Drama"));
    ArrayList <Rating> simRating = fr.getSimilarRatingsByFilter("168",10,3,f);
    System.out.println("found "+simRating.size()+" movies");
    for(Rating r : simRating)
    {
        Movie m=MovieDatabase.getMovie(r.getItem());
        System.out.println(r.getValue() + " " + m.getTitle());
        System.out.println("   Time:"+ m.getMinutes()+" "+ m.getGenres());
    }
    System.out.println("done");
  }
  public void printSimilarRatingsByYearAfterAndMinutes()
  {
    FourthRatings fr = new FourthRatings();
    RaterDatabase.initialize("ratings.csv");
    MovieDatabase.initialize("ratedmoviesfull.csv");
    System.out.println("number of movies "+ MovieDatabase.size());
    System.out.println("number of raters "+ RaterDatabase.size());
    AllFilters f = new AllFilters();
    f.addFilter(new MinutesFilter(70,200));
    f.addFilter(new YearAfterFilter(1975));
    ArrayList <Rating> simRating = fr.getSimilarRatingsByFilter("314",10,5,f);
    System.out.println("found "+simRating.size()+" movies");
    for(Rating r : simRating)
    {
        Movie m=MovieDatabase.getMovie(r.getItem());
        System.out.println(r.getValue() + " " + m.getTitle());
        System.out.println("   "+ m.getYear()+" "+ m.getGenres());
    }
    System.out.println("done");
  }
}
