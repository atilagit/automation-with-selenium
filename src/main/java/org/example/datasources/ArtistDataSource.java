package org.example.datasources;

import org.example.entities.Artist;

import java.util.List;

public interface ArtistDataSource {
    List<Artist> loadArtists();
}
