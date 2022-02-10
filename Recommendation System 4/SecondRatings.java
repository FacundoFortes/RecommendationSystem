
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile,String ratingsfile){
        FirstRatings first = new FirstRatings();
        myMovies = first.loadMovies(moviefile);
        myRaters = first.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id,int minimalRaters){
        double avg = 0;
        int howMany = 0;
        for(Rater r : myRaters){
            ArrayList<String> array = r.getItemsRated();
            if(array.contains(id)){
                howMany += 1;
            }
        }
        if(howMany >= minimalRaters){
            for(Rater r : myRaters){
               ArrayList<String> array = r.getItemsRated();
               if(array.contains(id)){
                  avg += r.getRating(id);
               }
           }
        }
        else{
            return 0.0;
        }
        avg = avg / howMany;
        return avg;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for(Movie m : myMovies){
            double avg = getAverageByID(m.getID(),minimalRaters);
            if(avg != 0.0){
                Rating r = new Rating(m.getID(),avg);
                ratings.add(r);
            }
        }
        return ratings;
    }
    
    public String getTitle(String id){
        String notfound = "This id wasn't found";
        String answer = "";
        for(Movie m : myMovies){
            String title = m.getID();
            if(title.equals(id)){
                answer = m.getTitle();
            }
        }
        if(! answer.equals("")){
            return answer;
        }
        else{
            return notfound;
        }
    }
    
    public String getID(String title){
        String notfound = "NO SUCH TITLE";
        String answer = "";
        for (Movie m : myMovies){
            String id = m.getTitle();
            if(id.equals(title)){
                answer = m.getID();
            }   
        }
        if(! answer.equals("")){
            return answer;
        }   
        else{
            return notfound;
        }   
    }
}