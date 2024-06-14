package com.javaguides.Banking.App.repository;

import com.javaguides.Banking.App.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository<EntityName, PrimaryKey Type> crude opperations using Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


}
