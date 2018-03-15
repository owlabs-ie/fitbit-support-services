package es.flaviojmend.fitbittracker.service;

import es.flaviojmend.fitbittracker.persistence.entity.User;
import es.flaviojmend.fitbittracker.persistence.repo.UserRepository;
import es.flaviojmend.fitbittracker.request.TrackingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

    public Iterable<User> listUsers(String from, String to) {
        Stream<User> users = StreamSupport.stream(userRepository.findAll().spliterator(),false);

        return users.filter(u ->
                ((to == null || Long.parseLong(to) >= u.getDateLastAccessed().getTime()/1000L)
                        && (from ==null || Long.parseLong(from) <= u.getDateLastAccessed().getTime()/1000L))).collect(Collectors.toList());
    }

    public Iterable<User> listUsersByApp(String app, String from, String to) {
        Stream<User> users = StreamSupport.stream(userRepository.findAllByApp(app).spliterator(),false);

        return users.filter(u ->
                ((to == null || Long.parseLong(to) >= u.getDateLastAccessed().getTime()/1000L)
                        && (from ==null || Long.parseLong(from) <= u.getDateLastAccessed().getTime()/1000L))).collect(Collectors.toList());

    }

    public Long countUsers(String from, String to) {
        Stream<User> users = StreamSupport.stream(userRepository.findAll().spliterator(),false);

        return users.filter(u ->
            ((to == null || Long.parseLong(to) >= u.getDateLastAccessed().getTime()/1000L)
                    && (from ==null || Long.parseLong(from) <= u.getDateLastAccessed().getTime()/1000L))
        ).count();

    }

    public Long countUsersByApp(String app,String from, String to) {
        Stream<User> users = StreamSupport.stream(userRepository.findAllByApp(app).spliterator(),false);

        return users.filter(u ->
                ((to == null || Long.parseLong(to) >= u.getDateLastAccessed().getTime()/1000L)
                        && (from ==null || Long.parseLong(from) <= u.getDateLastAccessed().getTime()/1000L))).count();
    }

}
