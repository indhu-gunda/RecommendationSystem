
/**
 * Write a description of RecommenderRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class RecommendationRunner implements Recommender
{
    private ArrayList<String> moviesf= new ArrayList<String>();
    @Override
    public ArrayList<String> getItemsToRate()
    {
        /*ArrayList<String> movies = MovieDatabase.filterBy(new YearAfterFilter(2013));
        for(int i=0;i<10;i++)
        {
            moviesf.add(movies.get(i));
        }*/
        moviesf.add("1375666");
        moviesf.add("2381249");
        moviesf.add("816692");
        moviesf.add("1457767");
        moviesf.add("2820852");
        moviesf.add("3659388");
        moviesf.add("332280");
        moviesf.add("2267998");
        moviesf.add("2084970");
        moviesf.add("2245084");
        moviesf.add("1659337");
        moviesf.add("1392170");
        return moviesf;
        
    }
    @Override
    public void printRecommendationsFor(String webRaterID)
    {
       getItemsToRate();
       Rater user=RaterDatabase.getRater(webRaterID);
       FourthRatings fr = new FourthRatings();
       RaterDatabase.initialize("ratings.csv");
       MovieDatabase.initialize("ratedmoviesfull.csv");
       //System.out.println("number of movies "+ MovieDatabase.size());
       //System.out.println("number of raters "+ RaterDatabase.size());
       ArrayList <Rating> simRating = fr.getSimilarRatings(webRaterID,30,5);
       ArrayList <Rating> toRemove= new ArrayList<Rating>();
       System.out.println("Below are the top picks for you: ");
       int counter=0;
       for(Rating ra : simRating)
       {
           if(moviesf.contains(ra.getItem()))
           {
               counter++;
           }
       }
       if(counter==simRating.size())
       {
           simRating.clear();
       }
       if(simRating.size()>15)
       {
           System.out.println("<table><tr><th>Rank</th><th>Movie</th><th>Year</th><th>Genre</th></tr>");
           System.out.println("<style>table, th, td {border: 1px solid black;border-collapse: collapse;padding: 15px}</style>");
           int x=1;
           for(int i=1;i<=15;i++)
           {
               Movie m=MovieDatabase.getMovie(simRating.get(i).getItem());
               if(! moviesf.contains(m.getID()))
               {
                 System.out.println("<tr><td>"+x+"</td>"); 
                 System.out.println("<td>"+m.getTitle()+"</td>");
                 System.out.println("<td>"+m.getYear()+"</td>");
                 System.out.println("<td>"+m.getGenres()+"</td></tr>");
                 x++;
               }
           }
           System.out.println("</table>");
       }
       else if(simRating.size()>0)
       {
           System.out.println("<table><tr><th>Rank</th><th>Movie</th> <th>Year</th><th>Genre</th></tr>");
           System.out.println("<style>table, th, td {border: 1px solid black;border-collapse: collapse;padding: 15px}</style>");
           int i=1;
           for(Rating r: simRating)
           {
               Movie mm=MovieDatabase.getMovie(r.getItem());
               if(! moviesf.contains(mm.getID()))
               {
                 System.out.println("<tr><td>"+i+"</td>"); 
                 System.out.println("<td>"+mm.getTitle()+"</td>");
                 System.out.println("<td>"+mm.getYear()+"</td>");
                 System.out.println("<td>"+mm.getGenres()+"</td></tr>");
                 i++;
               }
           }
           System.out.println("</table>");
       }
       else
       {
           System.out.println("No recommendations could be made");
       }
   
    
    }

}
