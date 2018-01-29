package ca.effenti.tutorial.hibernate;

import java.util.Objects;

public class AuthorStatReport {
    private String name;
    private Long nbSongs;

    public AuthorStatReport(String name, Long nbSongs) {
        this.name = name;
        this.nbSongs = nbSongs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNbSongs() {
        return nbSongs;
    }

    public void setNbSongs(Long nbSongs) {
        this.nbSongs = nbSongs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorStatReport that = (AuthorStatReport) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(nbSongs, that.nbSongs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, nbSongs);
    }

    @Override
    public String toString() {
        return "AuthorStatReport{" +
                "name='" + name + '\'' +
                ", nbSongs=" + nbSongs +
                '}';
    }
}
