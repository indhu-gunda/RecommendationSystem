
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter
{
    private String myGenre;
    
    public GenreFilter(String genre) 
    {
        myGenre = genre;
    }
    
    @Override
    public boolean satisfies(String id) 
    {
        String s= MovieDatabase.getGenres(id);
        return (s.indexOf(myGenre)!=-1);
    }

}
