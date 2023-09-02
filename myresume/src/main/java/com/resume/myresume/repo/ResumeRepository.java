package com.resume.myresume.repo;

import com.resume.myresume.data.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findByName(String name);
}

