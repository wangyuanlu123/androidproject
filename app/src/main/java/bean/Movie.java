package bean;

import java.io.Serializable;
import java.util.ArrayList;


public class Movie {
    private String error;
    private MovieDate result;

    public MovieDate getResult() {
        return result;
    }

    public void setResult(MovieDate result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public class MovieDate{

        private ArrayList<Movies> movie;

        public ArrayList<Movies> getMovie() {
            return movie;
        }

        public void setMovie(ArrayList<Movies> movie) {
            this.movie = movie;
        }
    }
    public class Movies implements Serializable {
        private String movie_picture;
        private String movie_name;
        private String movie_director;
        private String movie_starring;

        public String getMovie_picture() {
            return movie_picture;
        }

        public void setMovie_picture(String movie_picture) {
            this.movie_picture = movie_picture;
        }

        public String getMovie_name() {
            return movie_name;
        }

        public void setMovie_name(String movie_name) {
            this.movie_name = movie_name;
        }

        public String getMovie_director() {
            return movie_director;
        }

        public void setMovie_director(String movie_director) {
            this.movie_director = movie_director;
        }

        public String getMovie_starring() {
            return movie_starring;
        }

        public void setMovie_starring(String movie_starring) {
            this.movie_starring = movie_starring;
        }


    }
}

