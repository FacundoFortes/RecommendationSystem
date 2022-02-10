
import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings third  = new ThirdRatings("ratings_short.csv");
        System.out.println("There are " + third.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies");
        ArrayList<Rating> ratings = third.getAverageRatings(1);
        System.out.println("There are " + ratings.size() + 
        " movies with that amount of raters");
        Collections.sort(ratings);
        for(Rating r : ratings){
            String title = MovieDatabase.getTitle(r.getItem());
            System.out.println(r.getValue() + " " + title);
        }
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings third  = new ThirdRatings("ratings_short.csv");
        System.out.println("There are " + third.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies");
        Filter f = new YearAfterFilter(2000);
        ArrayList<Rating> ratings = third.getAverageRatingsByFilter(1,f);
        System.out.println("There are " + ratings.size() + 
        " movies with that amount of raters");
        Collections.sort(ratings);
        for(Rating r : ratings){
            String title = MovieDatabase.getTitle(r.getItem());
            int year = MovieDatabase.getYear(r.getItem());
            System.out.println(r.getValue() + " " + year+ " " + title);
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings third  = new ThirdRatings("ratings_short.csv");
        System.out.println("There are " + third.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies");
        Filter f = new GenreFilter("Crime");
        ArrayList<Rating> ratings = third.getAverageRatingsByFilter(1,f);
        System.out.println("There are " + ratings.size() + 
        " movies with that amount of raters");
        Collections.sort(ratings);
        for(Rating r : ratings){
            String title = MovieDatabase.getTitle(r.getItem());
            System.out.println(r.getValue() + " " + title);
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings third  = new ThirdRatings("ratings_short.csv");
        System.out.println("There are " + third.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies");
        Filter f = new MinutesFilter(110,170);
        ArrayList<Rating> ratings = third.getAverageRatingsByFilter(1,f);
        System.out.println("There are " + ratings.size() + 
        " movies with that amount of raters");
        Collections.sort(ratings);
        for(Rating r : ratings){
            String title = MovieDatabase.getTitle(r.getItem());
            int minutes = MovieDatabase.getMinutes(r.getItem());
            System.out.println(r.getValue() + " Time: " + minutes 
                               + " " + title);
        }
    }
    
    public void printAverageRatingsByDirectors(){
        ThirdRatings third  = new ThirdRatings("ratings_short.csv");
        System.out.println("There are " + third.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies");
        Filter f = new DirectorsFilter
                                 ("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> ratings = third.getAverageRatingsByFilter(1,f);
        System.out.println("There are " + ratings.size() + 
        " movies with that amount of raters");
        Collections.sort(ratings);
        for(Rating r : ratings){
            String title = MovieDatabase.getTitle(r.getItem());
            System.out.println(r.getValue() + " " + title);
            System.out.println(MovieDatabase.getDirector(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings third  = new ThirdRatings("ratings_short.csv");
        System.out.println("There are " + third.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies");
        GenreFilter f1 = new GenreFilter("Romance");
        YearAfterFilter f2 = new YearAfterFilter(1980);
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> ratings = third.getAverageRatingsByFilter(1,f);
        System.out.println("There are " + ratings.size() + 
        " movies with that amount of raters");
        Collections.sort(ratings);
        for(Rating r : ratings){
            String title = MovieDatabase.getTitle(r.getItem());
            int year = MovieDatabase.getYear(r.getItem());
            System.out.println(r.getValue() + " " + year + " " + title);
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings third  = new ThirdRatings("ratings_short.csv");
        System.out.println("There are " + third.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies");
        DirectorsFilter f1 = new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
        MinutesFilter f2 = new MinutesFilter(30,170);
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> ratings = third.getAverageRatingsByFilter(1,f);
        System.out.println("There are " + ratings.size() + 
        " movies with that amount of raters");
        Collections.sort(ratings);
        for(Rating r : ratings){
            String title = MovieDatabase.getTitle(r.getItem());
            int min = MovieDatabase.getMinutes(r.getItem());
            System.out.println(r.getValue() + " Time: " + min + " " + title);
            System.out.println(MovieDatabase.getDirector(r.getItem()));
        }
    }
}
