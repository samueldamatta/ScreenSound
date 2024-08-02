package com.ScreenSound.ScreenSound.Principal;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import com.ScreenSound.ScreenSound.Models.Artista;
import com.ScreenSound.ScreenSound.Models.Musica;
import com.ScreenSound.ScreenSound.Repository.ArtistaRepository;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private final ArtistaRepository repositorio;

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu(){
        var escolha = -1;

        while (escolha != 0){
            var menu = ("""
                Screen Sound Músicas

                1- Cadastrar Artistas
                2- Cadastrar Músicas
                3- Listar Músicas
                4- Buscar Músicas por Artistas

                0- sair
                """);
        System.out.println(menu);
        escolha = leitura.nextInt();
        leitura.nextLine();

        switch (escolha) {
            case 1:
                cadastrarArtista();
                break;
            case 2:
                cadastrarMusica();
                break;
            case 3:
                listarMusica();
                break;
            case 4:
                buscarMusicaPorArtista();
                break;
            case 0:
                System.out.println("Encerrada Aplicação");
                break;
            default:
                System.out.println("Opção Inválida!");
        }
    }
    }

    private void buscarMusicaPorArtista() {
        System.out.println("Qual artista deseja procurar as músicas? ");
        var nome = leitura.nextLine();
        List<Musica> musicas = repositorio.buscaMusicasPorArtista(nome);
        musicas.forEach(System.out::println);
    }

    private void listarMusica() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(a -> a.getMusicas().forEach(System.out::println));
    }

    private void cadastrarMusica() {
        System.out.println("Cadastrar música de qual artista? ");
        var nome = leitura.nextLine();
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);
        if (artista.isPresent()){
            System.out.println("Qual o título da música que deseja cadastrar? ");
            var nomeMusica = leitura.nextLine();
            System.out.println("Digite o gênero da música: ");
            var generoMusica = leitura.nextLine();
            Musica musica = new Musica(nomeMusica, generoMusica);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            repositorio.save(artista.get());
        }else{
            System.out.println("Artista não encontrado");
        }
        
    }

    private void cadastrarArtista() {
        System.out.println("Informe o nome desse artista: ");
        var nome = leitura.nextLine();
        Artista artista = new Artista(nome);
        repositorio.save(artista);
    }
}