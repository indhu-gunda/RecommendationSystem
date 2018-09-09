
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings 
{
    public static void main(String [ ] args)
    {
        FirstRatings fr= new FirstRatings();
        fr.testLoadMovies();
        fr.testLoadRaters();
    }
    public ArrayList<Movie> loadMovies(String filename)
    {
        FileResource csvData = new FileResource("data/"+ filename);
        CSVParser parser = csvData.getCSVParser();
        ArrayList<Movie> data= new ArrayList<Movie>();
        for (CSVRecord csvRecord : parser) 
        {
            Movie temp= new Movie(csvRecord.get("id"),csvRecord.get("title"),csvRecord.get("year"),csvRecord.get("genre"),csvRecord.get("director"),csvRecord.get("country"),csvRecord.get("poster"),Integer.parseInt(csvRecord.get("minutes")));
            data.add(temp);
        }
        return data;
    }
    
    public void testLoadMovies()
    {
        ArrayList<Movie> local=loadMovies("ratedmoviesfull.csv");
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        System.out.println(local.size());
        int comedyCount=0;
        int minCount=0;
        int max=0;
        //System.out.println(local.get(0).getGenres());
        for(Movie info : local)
        {
            //System.out.println(info);
            if(info.getGenres().indexOf("Comedy")!=-1)
                comedyCount++;
            if(info.getMinutes() > 150)
                minCount++;
            String [] temp= info.getDirector().split(",");
            for(String s : temp)
            {
               if(! map.containsKey(s))
                    map.put(s,1);
               else
                    map.put(s,map.get(s)+1);
            }   
        } 
        System.out.println("Number of Comedies: " + comedyCount);
        System.out.println("Number over 150 min: " + minCount);
        for(Integer i: map.values())
        {
            if(i>max)
                max=i;
        }
        System.out.println("progress");
        System.out.println(max);
        for(String s: map.keySet())
        {
            if(map.get(s)==max)
                System.out.println(s);
        }
       
        
    }
    public ArrayList<EfficientRater> loadRaters(String filename)
    {
        FileResource csvData = new FileResource("data/"+ filename);
        CSVParser parser = csvData.getCSVParser();
        ArrayList<EfficientRater> data= new ArrayList<EfficientRater>();
        ArrayList<String> id= new ArrayList<String>();
        boolean counter= true;
        for (CSVRecord csvRecord : parser) 
        {
            /*if(counter)
            {
               Rater temp= new Rater(csvRecord.get("rater_id"));
               temp.addRating(csvRecord.get("movie_id"),Double.parseDouble(csvRecord.get("rating")));
               data.add(temp);
               id.add(temp.getID());
               counter=false;
            }*/
            String raterID= csvRecord.get("rater_id");
            if(id.contains(raterID))
            {
              EfficientRater r=data.get(id.indexOf(raterID));
              r.addRating(csvRecord.get("movie_id"),Double.parseDouble(csvRecord.get("rating")));
            }
            else
            {
              EfficientRater temp= new EfficientRater(csvRecord.get("rater_id"));
              temp.addRating(csvRecord.get("movie_id"),Double.parseDouble(csvRecord.get("rating")));
              data.add(temp);
              id.add(temp.getID());
            }
            }
        
        //System.out.println("hello");
        return data;
    }
    public void testLoadRaters()
    {
        ArrayList<EfficientRater> local=loadRaters("ratings.csv");
        System.out.println(local.size());
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        HashMap<String,Integer> movie = new HashMap<String,Integer>();
        double max= 0;
        /*System.out.println(local.get(0).numRatings());
        for(Rater r : local)
        {
            if(! map.containsKey(r.getID()))
                map.put(r.getID(),r.numRatings());
        }*/
        //System.out.println(map.size());
        for(EfficientRater r : local)
        {
           //System.out.println(r.getID()+" "+ r.numRatings());
           ArrayList<String> temp= r.getItemsRated();
           for(String s : temp)
           {
             //System.out.println(s+" "+r.getRating(s));
             if(!movie.containsKey(s))
             {
                 movie.put(s,1);
             }
             else
             {
                 movie.put(s,movie.get(s)+1);
             }
           }
          map.put(r.getID(),r.numRatings());
          
        }
        System.out.println("193 "+map.get("193"));
        for(Integer i : map.values())
        {
            if(i>max)
            max=i;
        }
        System.out.println("max "+max);
        for(String s : map.keySet())
        {
            if(map.get(s)==max)
                System.out.println("rater "+s);
        }
        
        System.out.println("1798709 " + movie.get("1798709"));
        System.out.println(movie.size());
    }
}