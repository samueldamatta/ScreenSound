package com.ScreenSound.ScreenSound.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ScreenSound.ScreenSound.Models.Artista;
import com.ScreenSound.ScreenSound.Models.Musica;

public interface ArtistaRepository extends JpaRepository<Artista, Long>{
    Optional<Artista> findByNomeContainingIgnoreCase(String nome);
    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a.nome ILIKE %:nome")
    List<Musica> buscaMusicasPorArtista(String nome);
}
