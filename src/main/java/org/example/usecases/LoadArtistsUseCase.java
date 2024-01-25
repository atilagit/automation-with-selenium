package org.example.usecases;

import org.example.datasources.ArtistDataSource;
import org.example.entities.Artist;

import java.util.List;

public class LoadArtistsUseCase {

    ArtistDataSource artistDataSource;

    public LoadArtistsUseCase(ArtistDataSource artistDataSource) {
        this.artistDataSource = artistDataSource;
    }

    public List<Artist> execute() {
         return artistDataSource.loadArtists();
    }
}