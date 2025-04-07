package com.freesong.screensound.principal;

import com.freesong.screensound.model.Artist;
import com.freesong.screensound.model.Song;
import com.freesong.screensound.model.TypeArtist;
import com.freesong.screensound.repository.ArtistRepository;
import com.freesong.screensound.service.ArtistSearchDeepSeekService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private final ArtistRepository repository;
    private final ArtistSearchDeepSeekService deepSeekService;

    public Principal(ArtistRepository respository,ArtistSearchDeepSeekService deepSeekService){
        this.repository = respository;
        this.deepSeekService = deepSeekService;
    }
    public void showMenu() {

        int option = -1;

        while(option != 0){
            var menu = """
                    ***** SCREEN SOUND MUSIC *****
                    
                    1 - Cadastrar artistas
                    2 - Cadastrar músicas
                    3 - Listar músicas
                    4 - Buscar músicas por artistas
                    5 - Pesquisar dados sobre um artista
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            option = Integer.valueOf(sc.nextLine());

            switch (option){
                case 1:
                    registerArtists();
                    break;
                case 2:
                    registerSongs();
                    break;
                case 3:
                    listSongs();
                    break;
                case 4:
                    searchSongsByArtists();
                    break;
                case 5:
                    searchArtistData();
                case 0:
                    System.out.println("Closing application...");
                    break;
                default:
                    System.out.println("Invalid option");
            }

        }
    }

    private void registerArtists() {
        var registerNew = "S";

        while(registerNew.equalsIgnoreCase("s")) {

            System.out.println("Informe o nome do artista:");
            var name = sc.nextLine();
            System.out.println("Informe o tipo desse artista (solo, dupla ou banda): ");
            var type = sc.nextLine();

            TypeArtist typeArtist = TypeArtist.valueOf(type.toUpperCase());
            Artist artist = new Artist(name, typeArtist);

            repository.save(artist);

            System.out.println("Cadastrar outr artista? (S/N)");
            registerNew=sc.nextLine();
        }
    }

    private void registerSongs() {
        System.out.println("Cadastrar música de que artista? ");
        var name = sc.nextLine();
        Optional<Artist> artist = repository.findByNameContainingIgnoreCase(name);

        if(artist.isPresent()){
            System.out.println("Informe o título da música: ");
            var songName = sc.nextLine();
            Song song = new Song(songName);
            song.setArtist(artist.get());
            artist.get().getSongs().add(song);
            repository.save(artist.get());
        }else {
            System.out.println("Artista não encontrado!");
        }
    }

    private void listSongs() {
        List<Artist> artists = repository.findAll();
        artists.forEach(a -> a.getSongs().forEach(System.out::println));
    }

    private void searchSongsByArtists() {
        System.out.println("Buscar músicas de qual artista? ");
        var name = sc.nextLine();
        List<Song> songs = repository.searchSongsByArtist(name);
        songs.forEach(System.out::println);
    }

    private void searchArtistData() {
        System.out.println("Pesquisar dados de qual artista? ");
        var name = sc.nextLine();
        Optional<Artist> artist = repository.findByNameContainingIgnoreCase(name);

        if (artist.isPresent()) {
            System.out.println("Artista encontrado no banco de dados: " + artist.get());
            System.out.println("Buscando informações adicionais com DeepSeek...");
            List<String> artistData = deepSeekService.searchArtistData(name);
            artistData.forEach(System.out::println);
        } else {
            System.out.println("Artista não encontrado no banco de dados. Buscando informações com DeepSeek...");
            List<String> artistData = deepSeekService.searchArtistData(name);
            artistData.forEach(System.out::println);
        }
    }
}
