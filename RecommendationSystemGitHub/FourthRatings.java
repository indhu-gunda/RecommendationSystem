
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings 
{
    public double getAverageByID(String id, int minimalRaters)
    {
        ArrayList<Double> ratings= new ArrayList<Double>();
        double total=0;
        for(Rater r: RaterDatabase.getRaters())
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
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria)
    {
       ArrayList<Rating> avgRatings = new ArrayList<Rating>();
       ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
       for(String m : movies)
       {
        double avg= getAverageByID(m,minimalRaters);
        if(avg !=0)
         avgRatings.add(new Rating(m,avg));
       }
       return avgRatings;
    }
    private double dotProduct(Rater me, Rater r)
    {
        ArrayList<String> movieIDBoth= new ArrayList<String>();
        ArrayList<String> movieMe= me.getItemsRated();
        ArrayList<String> movieR= r.getItemsRated();
        ArrayList<Double> meRatings=new ArrayList<Double>();
        ArrayList<Double> rRatings=new ArrayList<Double>();
        double dotproduct= 0;
        for(String id : movieR)
        {
            if(movieMe.contains(id))
            {
                movieIDBoth.add(id);
            }
        }
        for(String s : movieIDBoth)
        {
            meRatings.add(me.getRating(s)-5);
            rRatings.add(r.getRating(s)-5);
        }
        for(int i=0;i<meRatings.size();i++)
        {
            double d= meRatings.get(i)*rRatings.get(i);
            dotproduct+=d;
        }
        return dotproduct;
    }
    public ArrayList<Rating> getSimilarities(String id)
    {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters())
        {
            if(!id.equals(r.getID()))
            {
                double d= dotProduct(me,r);
                if(d>0)
                    list.add(new Rating(r.getID(),d));
            }
        }
        Collections.sort(list,Collections.reverseOrder());
        return list;
    }
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters)
    {
       ArrayList<Rating> list = getSimilarities(id);
       //System.out.println(list);
       ArrayList<Rating> ret = new ArrayList<Rating>();
       for(String movieID : MovieDatabase.filterBy(new TrueFilter()))
       {
          HashMap<Rating,Double> ratings= new HashMap<Rating,Double>();
          double total=0;
          int x=numSimilarRaters;
          if(numSimilarRaters>list.size())
            x=list.size();
          for(int k=0; k<x; k++)
          {
            Rater r= RaterDatabase.getRater(list.get(k).getItem());
            ArrayList<String> temp = r.getItemsRated();
            if( temp.contains(movieID) )
            {
                ratings.put(list.get(k),r.getRating(movieID));
            }
          }
   
          if(ratings.size()>=minimalRaters)
          {
            for(Rating r : ratings.keySet())
            {
                double d= r.getValue()*ratings.get(r);
                total+=d;
            }
            double avg= total/ratings.size();
            ret.add(new Rating(movieID,avg));
          }
          
       }
       Collections.sort(ret,Collections.reverseOrder());
       return ret;
    }
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria)
    {
       ArrayList<Rating> list = getSimilarities(id);
       ArrayList<Rating> ret = new ArrayList<Rating>();
       for(String movieID : MovieDatabase.filterBy(filterCriteria))
       {
          HashMap<Rating,Double> ratings= new HashMap<Rating,Double>();
          double total=0;
          for(int k=0; k<numSimilarRaters; k++)
          {
            Rater r= RaterDatabase.getRater(list.get(k).getItem());
            ArrayList<String> temp = r.getItemsRated();
            if( temp.contains(movieID) )
            {
                ratings.put(list.get(k),r.getRating(movieID));
            }
          }
          if(ratings.size()>=minimalRaters)
          {
            for(Rating r : ratings.keySet())
            {
                double d= r.getValue()*ratings.get(r);
                total+=d;
            }
            double avg= total/ratings.size();
            ret.add(new Rating(movieID,avg));
          }
       }
       Collections.sort(ret,Collections.reverseOrder());
       return ret;
    }
}



