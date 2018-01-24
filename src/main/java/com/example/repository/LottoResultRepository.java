package com.example.repository;


import com.example.model.LottoResultModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface LottoResultRepository extends CrudRepository<LottoResultModel, Long> {

}