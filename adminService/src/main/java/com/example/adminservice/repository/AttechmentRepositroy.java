package com.example.adminservice.repository;

import com.example.adminservice.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttechmentRepositroy extends JpaRepository<Attachment,Long> {
}
