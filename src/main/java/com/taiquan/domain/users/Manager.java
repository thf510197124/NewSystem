package com.taiquan.domain.users;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue("2")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Manager extends User {
    @OneToMany(fetch = FetchType.EAGER,targetEntity = User.class,mappedBy = "manager")
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
