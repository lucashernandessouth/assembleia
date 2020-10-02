package com.hernandes.assembleia.repositories;

import com.hernandes.assembleia.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM Sessao obj WHERE obj.pauta.id = :pauta")
    List<Sessao> findByPauta(@Param("pauta") Integer idPauta);

}
