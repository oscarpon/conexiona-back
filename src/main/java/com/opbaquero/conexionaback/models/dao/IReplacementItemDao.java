package com.opbaquero.conexionaback.models.dao;

import com.opbaquero.conexionaback.models.entity.ReplacementItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IReplacementItemDao extends JpaRepository<ReplacementItem, UUID> {
}
