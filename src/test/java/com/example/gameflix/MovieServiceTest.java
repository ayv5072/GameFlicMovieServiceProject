package com.example.gameflix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Test
    void getAllMovies_ShouldReturnNonEmptyList() {
        List<Movie> movies = movieService.getAllMovies();

//sanity checks
        Assertions.assertNotNull(movies, "Movie list should never be null");
        Assertions.assertFalse(movies.isEmpty(), "Seed data should give us at least one movie");
    }

    @Test
    void getMovieById_ShouldReturnCorrectMovie() {
        Movie movie = movieService.getMovieById(1L);

        Assertions.assertNotNull(movie, "Expected to find a movie with id=1");
        Assertions.assertEquals(1L, movie.getId());
        Assertions.assertEquals("The Matrix", movie.getTitle());
    }

    @Test
    void addMovie_ShouldIncreaseTotalCount() {
        int beforeSize = movieService.getAllMovies().size();

        Movie newMovie = new Movie(42L, "Interstellar", "Sci-Fi");
        movieService.addMovie(newMovie);

        int afterSize = movieService.getAllMovies().size();
        Assertions.assertEquals(beforeSize + 1, afterSize, "Adding a movie should bump the list size by one");

        Movie fetched = movieService.getMovieById(42L);
        Assertions.assertNotNull(fetched, "Newly added movie should be retrievable by id");
        Assertions.assertEquals("Interstellar", fetched.getTitle());
    }
}
