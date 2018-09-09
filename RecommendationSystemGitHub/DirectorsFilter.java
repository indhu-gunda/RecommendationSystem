
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class DirectorsFilter implements Filter
{
    private String[] myDirectors;
	
	public DirectorsFilter(String directors) 
	{
		myDirectors = directors.split(",");
	}
	
	@Override
	public boolean satisfies(String id) 
	{
	    for(String s: myDirectors)
	    {
	        String director= MovieDatabase.getDirector(id);
	        if(director.contains(s))
	           return true;
	    }
	    return false;
	}
}
