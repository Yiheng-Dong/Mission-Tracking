package com.example.groupie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Integer> {
    public List<Mission> findByUserid(int userid);
}
