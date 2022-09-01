package com.statestreet.students.service;

import com.statestreet.students.entity.one_to_one_bidirect.AddressEntity;
import com.statestreet.students.entity.one_to_one_bidirect.UserEntity;
import com.statestreet.students.model.Address;
import com.statestreet.students.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addUser(User user) {

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getName());

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(user.getAddress().getCity());
        addressEntity.setStreet(user.getAddress().getStreet());

        userEntity.setAddress(addressEntity);
        em.persist(userEntity);

        System.out.println("User added: " + userEntity.getId());
    }

    public User findUser(long id) {
        User result = new User();

        UserEntity entity = em.find(UserEntity.class, id);
        result.setName(entity.getUserName());

        Address address = new Address();
        address.setCity(entity.getAddress().getCity());
        address.setStreet(entity.getAddress().getStreet());

        result.setAddress(address);

        /*
        AddressEntity entity = em.find(AddressEntity.class, id);

        Address address = new Address();
        address.setCity(entity.getCity());
        address.setStreet(entity.getStreet());

        result.setName(entity.getUser().getUserName());
        result.setAddress(address);
         */

        return result;
    }

    @Transactional
    public void updateUser(long id, User user) {
        UserEntity entity = em.find(UserEntity.class, id);
        entity.setUserName(user.getName());

        entity.getAddress().setStreet(user.getAddress().getStreet());
        entity.getAddress().setCity(user.getAddress().getCity());

        UserEntity systemUser = em.find(UserEntity.class, 0L);

        // This will be ignored as address is NOT owning side!
        entity.getAddress().setUser(systemUser);

        em.persist(entity);

        System.out.println("User updated: " + entity.getId());
    }

    @Transactional
    public void updateUser2(long id, User user) {
        AddressEntity address = em.find(AddressEntity.class, id);

        address.setStreet(user.getAddress().getStreet());
        address.setCity(user.getAddress().getCity());

        AddressEntity system = em.find(AddressEntity.class, 0l);

        // This will take effect as user is the owning side - FK will be set to 0!
        address.getUser().setAddress(system);

        em.persist(address);

        System.out.println("Address updated: " + address.getId());
    }
}
