package com.healthy.service;

import com.healthy.dto.SubscriptionCreateUpdateDTO;
import com.healthy.dto.SubscriptionDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminSubscriptionService {
    List<SubscriptionDetailsDTO> getAll();
    Page<SubscriptionDetailsDTO> paginate(Pageable pageable);
    SubscriptionDetailsDTO findById(Integer id);
    SubscriptionDetailsDTO create(SubscriptionCreateUpdateDTO subscriptionCreateUpdateDTO);
    SubscriptionDetailsDTO update(Integer id,SubscriptionCreateUpdateDTO subscriptionCreateUpdateDTO);
    void delete(Integer id);
}
