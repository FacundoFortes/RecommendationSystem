
import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        FourthRatings fourth = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("There are " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies");
        ArrayList<Rating> ratings = fourth.getAverageRatings(1);
        System.out.println("There are " + ratings.size() + 
        " movies with that amount of raters");
        Collections.sort(ratings);
        for(Rating r : ratings){
            String title = MovieDatabase.getTitle(r.getItem());
            System.out.println(r.getValue() + " " + title);
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings fourth  = new FourthRatings();
        RaterDatabase.initialize("ratings_short.csv");
        System.out.println("There are " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies");
        GenreFilter f1 = new GenreFilter("Romance");
        YearAfterFilter f2 = new YearAfterFilter(1980);
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> ratings = fourth.getAverageRatingsByFilter(1,f);
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
    
    public void printSimilarRatings(){
        FourthRatings fourth  = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("There are " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies");
        ArrayList<Rating> list = fourth.getSimilarRatings("71",20,5);
        for(Rating r : list){
           String title = MovieDatabase.getTitle(r.getItem());
           System.out.println(title + "\t" + r.getValue());
        }
    }
    
    public void printSimilarRatingsByGenre(){
        FourthRatings fourth  = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("There are " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies");
        Filter f = new GenreFilter("Mystery");
        ArrayList<Rating> list = fourth.getSimilarRatingsByFilter("964",20,5,f);
        for(Rating r : list){
           String title = MovieDatabase.getTitle(r.getItem());
           String genre = MovieDatabase.getGenres(r.getItem());
           System.out.println(title + "\t" + r.getValue());
           System.out.println(genre);
        }
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings fourth  = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("There are " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies");
        Filter f = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> list = fourth.getSimilarRatingsByFilter("120",10,2,f);
        for(Rating r : list){
           String title = MovieDatabase.getTitle(r.getItem());
           String directors = MovieDatabase.getDirector(r.getItem());
           System.out.println(title + "\t" + r.getValue());
           System.out.println(directors);
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fourth  = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("There are " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies");
        Filter f1 = new GenreFilter("Drama");
        Filter f2 = new MinutesFilter(80,160);
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> list = fourth.getSimilarRatingsByFilter("168",10,3,f);
        for(Rating r : list){
           String title = MovieDatabase.getTitle(r.getItem());
           String genre = MovieDatabase.getGenres(r.getItem());
           int minutes = MovieDatabase.getMinutes(r.getItem());
           System.out.println(title + "\t" + minutes + " min "+ "\t" + 
                                                     r.getValue());
           System.out.println(genre);
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fourth  = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("There are " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("There are " + MovieDatabase.size() + " movies");
        Filter f1 = new YearAfterFilter(1975);
        Filter f2 = new MinutesFilter(70,200);
        AllFilters f = new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> list = fourth.getSimilarRatingsByFilter("314",10,5,f);
        for(Rating r : list){
           String title = MovieDatabase.getTitle(r.getItem());
           int year = MovieDatabase.getYear(r.getItem());
           int minutes = MovieDatabase.getMinutes(r.getItem());
           System.out.println(title + "\t" + year + "\t" + minutes + " min " 
                                                   + "\t" + r.getValue());
           
        }
    }
}
