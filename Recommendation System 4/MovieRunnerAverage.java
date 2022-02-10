
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings second  = new SecondRatings("ratedmovies_short.csv",
                                                  "ratings_short.csv");
        System.out.println("There are " + second.getMovieSize() + " movies");
        System.out.println("There are " + second.getRaterSize() + " raters");
        ArrayList<Rating> ratings = second.getAverageRatings(3);
        Collections.sort(ratings);
        for(Rating r : ratings){
            String title = second.getTitle(r.getItem());
            System.out.println(r.getValue() + " " + title);
        }
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings second = new SecondRatings("ratedmovies_short.csv",
                                                  "ratings_short.csv");
        ArrayList<Rating> ratings = second.getAverageRatings(3);
        Collections.sort(ratings);
        for(Rating r : ratings){
            String id = second.getID("The Godfather");
            if(id.equals(r.getItem())){
                System.out.println("The rating for this movie is " +
                                    r.getValue()); 
            }
        }
    }
}
