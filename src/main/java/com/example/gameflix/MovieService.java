package com.example.gameflix;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final List<Movie> movies = new ArrayList<>();

    public MovieService() {
        // Seed with a few basic movies so the tests have something realistic to work with.
        movies.add(new Movie(1L, "The Matrix", "Sci-Fi"));
        movies.add(new Movie(2L, "Inception", "Sci-Fi"));
        movies.add(new Movie(3L, "The Dark Knight", "Action"));
    }

    public List<Movie> getAllMovies() {
        // Returning an unmodifiable list so calling code can't accidentally change our internal list.
        return Collections.unmodifiableList(movies);
    }

    public Movie getMovieById(Long id) {
        Optional<Movie> match = movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst();
        return match.orElse(null);
    }

    public void addMovie(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("movie cannot be null");
        }
        movies.add(movie);
    }
}
