package com.social.media.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne
//    @JoinColumn(name="social_profile_id")
    @OneToOne(mappedBy = "socialUser", cascade = CascadeType.ALL)
    private SocialProfile socialProfile;

    @OneToMany(mappedBy="socialUser")
    private List<Post> posts =  new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<SocialGroup> socialGroups = new HashSet<>();

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    public void setSocialProfile(SocialProfile socialProfile){
        socialProfile.setSocialUser(this);
        this.socialProfile = socialProfile;
    }
}
