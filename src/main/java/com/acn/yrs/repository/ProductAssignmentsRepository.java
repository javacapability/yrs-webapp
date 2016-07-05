package com.acn.yrs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.acn.yrs.models.ProductAssignment;

@RepositoryRestResource(path="productassignments")
public interface ProductAssignmentsRepository extends PagingAndSortingRepository<ProductAssignment, Integer>{


}
