package com.ccmsd.starters.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ccmsd.starters.vo.Note;

@Component
public interface NoteRepository extends JpaRepository<Note, Long>
{

}