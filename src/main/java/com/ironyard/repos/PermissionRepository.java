package com.ironyard.repos;

import com.ironyard.data.Permission;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by reevamerchant on 11/19/16.
 */
public interface PermissionRepository extends PagingAndSortingRepository<Permission, Long>{
}
