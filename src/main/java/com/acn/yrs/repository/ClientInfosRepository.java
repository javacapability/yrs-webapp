package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.ClientInfo;

@RepositoryRestResource(path="clientinfos")
public interface ClientInfosRepository extends PagingAndSortingRepository<ClientInfo, Integer>{


}
