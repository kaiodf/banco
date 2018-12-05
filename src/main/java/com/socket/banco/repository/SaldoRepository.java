package com.socket.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.socket.banco.model.Saldo;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Long>{

	@Query("SELECT s FROM Saldo s WHERE LOWER(s.cardNumber) = LOWER(:cardNumber)")
	Saldo consultar(@Param(value = "cardNumber") String cardNumber);
	
}
