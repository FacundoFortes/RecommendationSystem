
import java.util.*;

public class FourthRatings {
    private double getAverageByID(String id,int minimalRaters){
        double avg = 0;
        int howMany = 0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        for(Rater r : myRaters){//each
            ArrayList<String> array = r.getItemsRated();
            if(array.contains(id)){
                howMany += 1;
            }
        }
        if(howMany >= minimalRaters){
            for(Rater r : myRaters){//each
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
        for(String s : movies){//each
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
        for(String s : movies){//each
            Movie m = MovieDatabase.getMovie(s);
            double avg = getAverageByID(m.getID(),minimalRaters);
            if(avg != 0.0){
                Rating r = new Rating(m.getID(),avg);
                answer.add(r);
            }
        }
        return answer;
    }
    
    private double dotProduct(Rater me,Rater r){
        ArrayList<String> r1 = me.getItemsRated();
        ArrayList<String> r2 = r.getItemsRated();
        double answer = 0;
        for(String s : r1){
            for(String s2 : r2){
                if(s2.equals(s)){
                    double rating1 = me.getRating(s) - 5;
                    double rating2 = r.getRating(s) - 5;
                    answer += rating1 * rating2;
                }
            }
        }
        return answer;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()){
            if(r != me){
                double product = dotProduct(me,r);
                if(product > 0){
                    Rating rat = new Rating(r.getID(),product);
                    list.add(rat);
                }
            }
        }
        Collections.sort(list,Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id,int numSimilarRaters,
                                         int minimalRaters){
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        if(numSimilarRaters > list.size()){
            numSimilarRaters = list.size();
        }
        for(String movie : MovieDatabase.filterBy(new TrueFilter())){
            int nRatings = 0;
            double sum = 0.0;
            for(int i=0;i<numSimilarRaters;i++){
                Rating r = list.get(i);
                String raterID = r.getItem();
                Rater simRater = RaterDatabase.getRater(raterID);
                if(simRater.hasRating(movie)){
                    nRatings+=1;
                    double score = simRater.getRating(movie);
                    double weight = r.getValue() * score;
                    sum += weight;
                }
            }
            if(nRatings >= minimalRaters) {
                double weightedAverage = sum / nRatings;
                ret.add(new Rating(movie,weightedAverage));
            }
        }
        Collections.sort(ret,Collections.reverseOrder());
        return ret;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id,
                             int numSimilarRaters,int minimalRaters,Filter f){
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        for(String movie : MovieDatabase.filterBy(f)){
            int nRatings = 0;
            double sum = 0;
            for(int i=0;i<numSimilarRaters;i++){
                Rating r = list.get(i);
                String raterID = r.getItem();
                Rater simRater = RaterDatabase.getRater(raterID);
                if(simRater.hasRating(movie)) {
                    nRatings+=1;
                    double score = simRater.getRating(movie);
                    double weight = r.getValue() * score;
                    sum += weight;
                }
            }
            if(nRatings >= minimalRaters) {
                double weightedAverage = sum / nRatings;
                ret.add(new Rating(movie,weightedAverage));
            }
        }
        Collections.sort(ret,Collections.reverseOrder());
        return ret;
    }
}
