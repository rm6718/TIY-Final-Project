package com.ironyard.repos;

import com.ironyard.data.Goal;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by reevamerchant on 11/7/16.
 */
public interface GoalRepository extends PagingAndSortingRepository<Goal, Long> {
}
