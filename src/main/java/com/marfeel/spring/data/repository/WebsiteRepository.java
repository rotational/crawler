package com.marfeel.spring.data.repository;

import com.marfeel.spring.data.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  WebsiteRepository extends JpaRepository<Website, Long> {

}
