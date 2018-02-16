package es.flaviojmend.fitbittracker.service;

import es.flaviojmend.fitbittracker.persistence.entity.User;
import es.flaviojmend.fitbittracker.persistence.repo.UserRepository;
import es.flaviojmend.fitbittracker.request.TrackingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TrackerService {


    @Autowired
    private UserRepository userRepository;

    public void saveOrUpdateUser(String userId) {
        User user = userRepository.findOne(userId);
        if(user == null) {
            user = new User();
            user.setUserId(userId);
            user.setDateCreated(new Date());
        }
        user.setDateLastAccessed(new Date());
        userRepository.save(user);
    }

    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

    public Long countUsers() {
        return userRepository.count();
    }

}
