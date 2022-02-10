
public class MinutesFilter implements Filter {
    
    private int min;
    private int max;
    
    public MinutesFilter(int minMin, int maxMin){
        min = minMin;
        max = maxMin;
    }
    
    public boolean satisfies(String id){
        if(MovieDatabase.getMinutes(id) >= min && 
           MovieDatabase.getMinutes(id) <= max){
           return true; 
        }
        return false;
    }
}
