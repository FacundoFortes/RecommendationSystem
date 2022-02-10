
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings first = new FirstRatings();
        myRaters = first.loadRaters(ratingsfile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String s : movies){
            Movie m = MovieDatabase.getMovie(s);
            double avg = getAverageByID(m.getID(),minimalRaters);
            if(avg != 0.0){
                Rating r = new Rating(m.getID(),avg);
                ratings.add(r);
            }
        }
        return ratings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,
                                                       Filter filterCriteria){
        ArrayList<Rating> answer = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String s : movies){
            Movie m = MovieDatabase.getMovie(s);
            double avg = getAverageByID(m.getID(),minimalRaters);
            if(avg != 0.0){
                Rating r = new Rating(m.getID(),avg);
                answer.add(r);
            }
        }
        return answer;
    }
}
