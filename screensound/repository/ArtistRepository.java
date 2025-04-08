package com.freesong.screensound.repository;

import com.freesong.screensound.model.Artist;
import com.freesong.screensound.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Optional<Artist> findByNameContainingIgnoreCase(String name);

    @Query("SELECT s FROM Artist a JOIN a.songs s WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Song> searchSongsByArtist(String name);
}
