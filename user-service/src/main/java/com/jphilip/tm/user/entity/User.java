package com.jphilip.tm.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.*;

@Entity
@Table(name = "users")
@Data
@Builder
public class User implements Comparable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "Boolean")
    private Boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "team_lead_id")
    private User teamLead;

    @OneToMany(mappedBy = "teamLead")
    private Set<User> teamMembers;

    @Override
    public int compareTo(User o) {
        int nameComparison = this.name.compareTo(o.name);
        if (nameComparison != 0) {
            return nameComparison;
        }
        return this.email.compareTo(o.email);
    }


    /*
     *
     *  Helper method/s
     *
     */

    public void addRole(Role role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
    }

    public void addTeamMember(User member) {
        if (teamMembers == null) {
            teamMembers = new TreeSet<>();
        }
        if (!teamMembers.contains(member)) {
            teamMembers.add(member);
            member.setTeamLead(this);
        }
    }
}
