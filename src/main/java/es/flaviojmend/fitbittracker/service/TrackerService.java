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

    public void saveOrUpdateUser(String userId, String app) {
        User user = userRepository.findOne(userId);
        if(user == null) {
            user = new User();
            user.setUserId(userId);
            user.setDateCreated(new Date());
        }
        user.setApp(app);
        user.setDateLastAccessed(new Date());
        userRepository.save(user);
    }

    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

    public Iterable<User> listUsersByApp(String app) {
        return userRepository.findAllByApp(app);
    }

    public Long countUsers() {
        return userRepository.count();
    }

    public Long countUsersByApp(String app) {
        return new Long(userRepository.findAllByApp(app).size());
    }

}
