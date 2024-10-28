package com.healthy.mapper;

import com.healthy.dto.*;
import com.healthy.model.entity.*;
import jakarta.persistence.Column;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component

public class ProfileMapper {
    private final ModelMapper modelMapper;
    private final PlanMapper planMapper;

    public ProfileMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.planMapper = new PlanMapper(modelMapper);
    }

    public ProfileDTO toProfileDTO(Profile profile) {
        ProfileDTO profileDTO = modelMapper.map(profile, ProfileDTO.class);
        profileDTO.setUserName(profile.getUserName());
        profileDTO.setFirstName(profile.getFirstName());
        profileDTO.setLastName(profile.getLastName());
        //SI QUIERES QUE SE MUESTRE O NO EL PASSWORD QUITALO LAS //, ES RECOMENDABLE DE QUE SE PERMITA TENER UNA OPCION DE RECUPERACIÓN DE CONTRASEÑA PERO SI TU GRUPO QUIERE DEJALO ASI
        profileDTO.setEmail(profile.getEmail());
        profileDTO.setPassword(profile.getUser().getPassword());
        profileDTO.setHeight(profile.getHeight());
        profileDTO.setWeight(profile.getWeight());
        profileDTO.setAge(profile.getAge());
        profileDTO.setGender(profile.getGender());
        profileDTO.setHealthConditions(profile.getHealthConditions());

        profileDTO.setPlans(profile.getPlans() != null ?
                profile.getPlans().stream().map(planMapper::toPlanDTO).toList() : new ArrayList<>());

        profileDTO.setResources(profile.getProfileResources() != null ?
                profile.getProfileResources().stream().map(this::toProfileResourceDTO).toList() : new ArrayList<>());

        profileDTO.setSubPlans(profile.getSubPlans() != null ?
                profile.getSubPlans().stream().map(this::toProfileSubscriptionDTO).toList() : new ArrayList<>());

        return profileDTO;
    }


    public Profile toEntity(ProfileCreateDTO profileCreateDTO) {
        return modelMapper.map(profileCreateDTO, Profile.class);
    }
    public ProfileCreateDTO toCreateUpdateDTO(Profile profile) {
        return modelMapper.map(profile, ProfileCreateDTO.class);
    }

    private ProfileResourceDetailsDTO toProfileResourceDTO(ProfileResource profileResource) {
        ProfileResourceDetailsDTO profileResourceDTO = modelMapper.map(profileResource, ProfileResourceDetailsDTO.class);

        //DE PROFILE RESOURCE
        profileResourceDTO.set_active(profileResource.is_active());
        profileResourceDTO.setAccess_expiration(profileResource.getAccess_expiration());

        //DE RESOURCE EXPERT
        profileResourceDTO.setExpertFirstName(profileResource.getResource().getExpert().getFirstName());
        profileResourceDTO.setExpertLastName(profileResource.getResource().getExpert().getLastName());
        profileResourceDTO.setExpertise(profileResource.getResource().getExpert().getExpertise());

        //DE RESOURCE SUBPLAN
        profileResourceDTO.setSubPlanName(profileResource.getResource().getSubPlan().getName());

        // DE RESOURCE
        profileResourceDTO.setResourceTitle(profileResource.getResource().getTitle());
        profileResourceDTO.setDescription(profileResource.getResource().getDescription());
        profileResourceDTO.setResourceType(profileResource.getResource().getResourceType());
        profileResourceDTO.setContent(profileResource.getResource().getContent());

        profileResourceDTO.setUserName(profileResource.getProfile().getUserName());

        return profileResourceDTO;
    }
    private ProfileSubscriptionDTO toProfileSubscriptionDTO(Subscription subscription) {
        ProfileSubscriptionDTO profileSubscriptionDTO = modelMapper.map(subscription, ProfileSubscriptionDTO.class);

        profileSubscriptionDTO.setStartAt(subscription.getStartAt());
        profileSubscriptionDTO.setEndAt(subscription.getEndAt());
        profileSubscriptionDTO.setPaymentStatus(subscription.getPaymentStatus());
        profileSubscriptionDTO.setSubscriptionStatus(subscription.getSubscriptionStatus());


        // PARA SUBPLAN
        profileSubscriptionDTO.setSubPlanName(subscription.getSubPlan().getName());
        profileSubscriptionDTO.setSubPlanDescription(subscription.getSubPlan().getDescription());
        profileSubscriptionDTO.setSubPlanPrice(subscription.getSubPlan().getPrice());
        profileSubscriptionDTO.setDurationDays(subscription.getSubPlan().getDurationDays());

        return profileSubscriptionDTO;
    }

    public UserProfileDTO toUserProfileDTO(Profile profile) {
        UserProfileDTO dto = new UserProfileDTO();
        dto.setUsername(profile.getUser().getUserName());
        dto.setAge(profile.getAge());
        dto.setGender(profile.getGender());
        dto.setHeight(profile.getHeight());
        dto.setWeight(profile.getWeight());
        dto.setHealthConditions(profile.getHealthConditions());
        return dto;
    }


}