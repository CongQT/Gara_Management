package com.example.gara_management.payload.response;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewerInfoResponse {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("user_id")
  private Integer userId;

  @JsonProperty("name")
  private String name;

  @JsonProperty("email")
  private String email;

  @JsonProperty("phone")
  private String phone;

  @JsonProperty("address")
  private String address;


  @JsonProperty("current_job")
  private String currentJob;

  @JsonIgnore
  private String occupationalGroups;

  @JsonGetter("occupational_group_ids")
  public List<Integer> getOccupationalGroupIds() {
    return Arrays.stream(StringUtils.split(StringUtils
            .defaultString(occupationalGroups), ","))
        .mapToInt(Integer::parseInt)
        .boxed()
        .toList();
  }

  @JsonSetter("occupational_group_ids")
  public void setOccupationalGroupIds(List<Integer> occupationalGroupIds) {
    this.occupationalGroups = occupationalGroupIds.stream()
        .distinct()
        .map(String::valueOf)
        .collect(Collectors.joining(","));
  }

  @JsonProperty("avatar")
  private String avatar;

  @JsonProperty("self_introduction")
  private String selfIntroduction;

  @JsonProperty("birthday")
  private LocalDate birthday;

  @JsonProperty("citizen_identity_card_front")
  private String citizenIdentityCardFront;

  @JsonProperty("citizen_identity_card_front_url")
  private String citizenIdentityCardFrontUrl;

  @JsonProperty("citizen_identity_card_back")
  private String citizenIdentityCardBack;

  @JsonProperty("citizen_identity_card_back_url")
  private String citizenIdentityCardBackUrl;

  @JsonProperty("education_level")
  private String educationLevel;

  @JsonProperty("portfolio")
  private String portfolio;

  @JsonProperty("portfolio_url")
  private String portfolioUrl;

}