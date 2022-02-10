
public class DirectorsFilter implements Filter {
    
    private String myDirector;
    
    public DirectorsFilter(String director){
        myDirector = director;
    }
    
    public boolean satisfies(String id){
        if(myDirector.contains(MovieDatabase.getDirector(id))){
            return true;
        }
        return false;
    }
}
