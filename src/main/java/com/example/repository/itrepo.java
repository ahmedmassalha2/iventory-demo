package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.models.Item;

@Repository
public interface itrepo extends CrudRepository<Item, Long> {
	List<Item> findByName(String name);
}
