package ca.effenti.tutorial.hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

import static java.util.Objects.*;

@Entity
@Table(name="SONG")
public class Song {

    @Id()
    @GeneratedValue
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "release_date")
    private LocalDate releaseDate;

    public Song() {
    }

    public Song(String title, LocalDate releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(id, song.id) &&
                Objects.equals(title, song.title) &&
                Objects.equals(releaseDate, song.releaseDate);
    }

    @Override
    public int hashCode() {
        return hash(id, title, releaseDate);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
