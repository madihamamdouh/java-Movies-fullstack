package dev.madiham.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public List<Movie> allMovies(){
    return movieRepository.findAll();
    }
    public Optional<Movie> singleMovie(String imdbId){
        return movieRepository.findMovieByImdbId(imdbId);

    }
    public Optional<Movie> createMovie(Movie movie){
        if (movie.getImdbId() == null || movieRepository.findMovieByImdbId(movie.getImdbId()).isPresent()) {
            return Optional.empty();
        }
        movie.setId(null);
        movie.setReviewIds(new ArrayList<>());
        return Optional.of(movieRepository.insert(movie));
    }
}
