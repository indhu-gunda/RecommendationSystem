
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter
{
    private int minMin;
    private int maxMin;
    
    public MinutesFilter(int min,int max) 
    {
        minMin = min;
        maxMin = max;
    }
    
    @Override
    public boolean satisfies(String id) 
    {
        int min=MovieDatabase.getMinutes(id);
        return ( min>= minMin && min<= maxMin);

    }

}
