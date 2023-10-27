package com.example.stock.persist;

import com.example.stock.persist.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUsername(String username); //아이디 기준
    boolean existsByUsername(String username);  //존재 여부 파악
}
