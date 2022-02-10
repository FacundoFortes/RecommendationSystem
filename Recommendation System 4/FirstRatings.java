
import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String fileName){
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        FileResource fr = new FileResource(fileName);
        for(CSVRecord record : fr.getCSVParser()){
            int min = Integer.parseInt(record.get("minutes"));
            Movie m = new Movie(record.get("id"),record.get("title"),
            record.get("year"),record.get("country"),
            record.get("genre"),record.get("director"),min,
            record.get("poster"));
            movieList.add(m);
        }
        return movieList;
    }
    
    public void testLoadMovies(){
        ArrayList<Movie> list = loadMovies("ratedmoviesfull.csv");
        System.out.println("There are " + list.size() + " movies");
        /*for(Movie m : list){
            System.out.println(m.getTitle());
        }*/
        int howMany = 0;
        for(Movie m : list){
            if(m.getGenres().contains("Comedy")){
                howMany += 1;
            }
        }
        System.out.println("There are " + howMany + 
                           " movies in that genre"); 
        howMany = 0;
        for(Movie m : list){
            if(m.getMinutes() > 150){
                howMany += 1;
            }
        }
        System.out.println("There are " + howMany + 
                           " movies greater than that length");
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        int max = 0;
        howMany = 0;
        for(Movie m : list){
            String directors = m.getDirector();
            String[] dir = directors.split("[,]",0);
            for(int k=0;k<dir.length;k++){
                String currDir = dir[k];
                if(! map.containsKey(currDir)){
                    map.put(currDir,1);
                }
                else{
                    int value = map.get(currDir);
                    map.put(currDir,value + 1);
                }
            }
        }
        for(String s : map.keySet()){
            int value = map.get(s);
            if(max < value){
                max = value;
            }
        }
        for(String s : map.keySet()){
            int value = map.get(s);
            if(value == max){
                howMany += 1;
            }
        }
        System.out.println("The maximum movies directed by one director is " 
        + max + " and there are " + howMany + 
        " directors that directed that many movies");
        System.out.println("These directors are: ");
        for(String s : map.keySet()){
            int value = map.get(s);
            if(value == max){
                System.out.println(s);
            }
        }
    }
    
    public ArrayList<Rater> loadRaters(String fileName){
        ArrayList<Rater> raterList = new ArrayList<Rater>();
        FileResource fr = new FileResource(fileName);
        String currID = "0";
        int currIndex = -1;
        for(CSVRecord record : fr.getCSVParser()){
            String idRater = record.get("rater_id");
            String idmovie = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            if(! currID.equals(idRater)){
                Rater rater = new EfficientRater(idRater);
                rater.addRating(idmovie,rating);
                raterList.add(rater);
                currID = idRater;
                currIndex += 1;
            }
            else if(currID.equals(idRater)){
                raterList.get(currIndex).addRating(idmovie,rating);
            }
        }
        return raterList;
    }
    
    public void testLoadRaters(){
        ArrayList<Rater> list = loadRaters("ratings.csv");
        System.out.println("There are " + list.size() + " raters");
        /*for(Rater r : list){
            System.out.println(r.getID() + " did " + r.numRatings() + 
            " ratings");
            ArrayList<String> ratings = r.getItemsRated();
            for(String s : ratings){
                System.out.println("For the movie " + s + 
                " the rater gave " + r.getRating(s) + " points");
            }
        }*/
        for(Rater r : list){
            if(r.getID().equals("193")){
                System.out.println("The rater " + r.getID() + " did " +
                                    r.numRatings() + " ratings");
            }
        }
        
        int max = 0;
        int howMany = 0;
        String raterMax = "";
        for(Rater r : list){
            if(r.numRatings() > max){
                max = r.numRatings();
            }
        }
        for(Rater r : list){
            if(r.numRatings() == max){
                howMany += 1;
                raterMax = raterMax + r.getID() + " ";
            }
        }
        System.out.println("There are " + howMany + " raters with " +
                           max + " ratings, and those are " + raterMax);
        howMany = 0;
        int howManyMovies = 0;
        for(Rater r : list){
            ArrayList<String> ratings = r.getItemsRated();
            for(String s : ratings){
                if(s.equals("1798709")){
                    howMany += 1;
                }
            }
        }
        System.out.println("This movie was rated by " + howMany + 
                           " raters ");
        ArrayList<String> manyMovies = new ArrayList<String>();
        for(Rater r : list){
            ArrayList<String> ratings = r.getItemsRated();
            for(String s : ratings){
                if(! manyMovies.contains(s)){
                    manyMovies.add(s);
                }
            }
        }
        System.out.println("There are " + manyMovies.size() + 
                           " movies rated");
    }
}
