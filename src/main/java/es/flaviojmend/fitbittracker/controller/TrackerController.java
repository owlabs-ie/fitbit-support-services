package es.flaviojmend.fitbittracker.controller;

import es.flaviojmend.fitbittracker.persistence.entity.User;
import es.flaviojmend.fitbittracker.request.TrackingRequest;
import es.flaviojmend.fitbittracker.service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/track")
public class TrackerController {

    @Autowired
    TrackerService trackerService;

    @RequestMapping(method= RequestMethod.POST)
    public Boolean registerRequest(@RequestBody TrackingRequest trackingRequest) {
        trackerService.updateUser(trackingRequest);
        return true;
    }

    @RequestMapping(method= RequestMethod.GET)
    public Iterable<User> listUsers() {
        return trackerService.listUsers();
    }


    @RequestMapping(method= RequestMethod.GET, value = "/count")
    public Long countUsers() {
        return trackerService.countUsers();
    }
}