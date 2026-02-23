package org.example.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "genres")
    private List<Movie> movies = new ArrayList<>();

    public Genre() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie) {
        if (!movies.contains(movie)) {
            movies.add(movie);
            movie.getGenres().add(this);
        }
    }

    public void removeMovie(Movie movie) {
        if (movies.remove(movie)) {
            movie.getGenres().remove(this);
        }
    }
}