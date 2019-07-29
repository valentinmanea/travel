package com.travel.assistant.repo;

import java.util.Optional;

import com.travel.assistant.entities.Role;

public interface RoleRepo extends BaseRepository<Role>{
	
	public Optional<Role> findFirstByName(String string);

}
