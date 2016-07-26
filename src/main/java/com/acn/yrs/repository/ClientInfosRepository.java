package com.acn.yrs.repository;

import java.util.Date;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.ClientInfo;

@RepositoryRestResource(path="clientinfos", exported=false)
public interface ClientInfosRepository extends PagingAndSortingRepository<ClientInfo, Integer>{

	public ClientInfo findClientByClientNameAndBirthday(String clientName, Date birthday);
}
