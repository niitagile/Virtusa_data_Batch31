package com.cityclassifiedandsearch.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cityclassifiedandsearch.bean.ConfirmationToken;
import java.util.*;

@Repository("confirmationTokenRepository")

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}