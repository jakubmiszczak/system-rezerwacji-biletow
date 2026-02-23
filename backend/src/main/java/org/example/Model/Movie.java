package org.example.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String director;
    private Integer durationMinutes;
    private Integer releaseYear;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Screening> screenings = new ArrayList<>();

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }

    public void addScreening(Screening screening) {
        if (!screenings.contains(screening)) {
            screenings.add(screening);
            if (screening.getMovie() != this) {
                screening.setMovie(this);
            }
        }
    }

    public void removeScreening(Screening screening) {
        if (screenings.contains(screening)) {
            screenings.remove(screening);
            if (screening.getMovie() == this) {
                screening.setMovie(null);
            }
        }
    }

    public void addActor(Actor actor) {
        if (!actors.contains(actor)) {
            actors.add(actor);
            actor.getMovies().add(this);
        }
    }

    public void removeActor(Actor actor) {
        if (actors.remove(actor)) {
            actor.getMovies().remove(this);
        }
    }

    public void addGenre(Genre genre) {
        if (!genres.contains(genre)) {
            genres.add(genre);
            genre.getMovies().add(this);
        }
    }

    public void removeGenre(Genre genre) {
        if (genres.remove(genre)) {
            genre.getMovies().remove(this);
        }
    }
}