package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.UserGroup;

@RepositoryRestResource(path="usergroups", exported=false)
public interface UserGroupsRepository extends PagingAndSortingRepository<UserGroup, Integer>{


}
