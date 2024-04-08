package com.sparklecow.curso.repositories;

import com.sparklecow.curso.entities.academy.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}
