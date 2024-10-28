package com.healthy.repository;
import com.healthy.model.entity.Subscription;
import com.healthy.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import org.springframework.data.domain.Pageable;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    List<Subscription> findByUserId(Integer userId);
    List<Subscription> findByUser(User user, Pageable pageable);


    @Query(value = "SELECT quantity, consultdate FROM fn_List_subscription_report()", nativeQuery = true)
    List<Object[]> getSubscriptionReport();

}