package com.hernandes.assembleia.repositories;

import com.hernandes.assembleia.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Voto obj WHERE obj.id.pauta.id = :pauta AND obj.id.associado.id = :associado")
    List<Voto> findByPautaAndAssociado(@Param("pauta") Integer idPauta, @Param("associado") Integer idAssociado);
}
