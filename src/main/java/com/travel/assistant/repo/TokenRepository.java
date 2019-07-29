package com.travel.assistant.repo;

import java.util.Optional;

import com.travel.assistant.entities.VerificationToken;

public interface TokenRepository extends BaseRepository<VerificationToken>{
	
	public Optional<VerificationToken> findByToken(String token);
}
