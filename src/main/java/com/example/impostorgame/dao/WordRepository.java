package com.example.impostorgame.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.impostorgame.model.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long>{

}
