package org.example.dao;

import org.example.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestDAO extends JpaRepository<Request, Integer> {
}
