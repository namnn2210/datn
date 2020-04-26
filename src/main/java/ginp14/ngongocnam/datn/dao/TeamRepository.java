package ginp14.ngongocnam.datn.dao;

import ginp14.project3.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    Team findById(int id);
    Page<Team> findAll(Pageable pageable);
}
