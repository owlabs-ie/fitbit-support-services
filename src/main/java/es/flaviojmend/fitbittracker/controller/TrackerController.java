package es.flaviojmend.fitbittracker.controller;

import es.flaviojmend.fitbittracker.persistence.entity.User;
import es.flaviojmend.fitbittracker.request.TrackingRequest;
import es.flaviojmend.fitbittracker.service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/track")
public class TrackerController {

    @Autowired
    TrackerService trackerService;

    @RequestMapping(method= RequestMethod.GET)
    public Iterable<User> listUsers(@RequestParam(value="from", required = false) String from, @RequestParam(value="to", required = false) String to) {
        return trackerService.listUsers(from,to);
    }

    @RequestMapping(method= RequestMethod.GET, value = "/{app}")
    public Iterable<User> listUsersByApp(@PathVariable String app,@RequestParam(value="from", required = false) String from, @RequestParam(value="to", required = false) String to) {
        return trackerService.listUsersByApp(app,from,to);
    }

    @RequestMapping(method= RequestMethod.GET, value = "/count")
    public Long countUsers(@RequestParam(value="from", required = false) String from, @RequestParam(value="to", required = false) String to) {
        return trackerService.countUsers(from, to);
    }

    @RequestMapping(method= RequestMethod.GET, value = "/{app}/count")
    public Long countUsersByApp(@PathVariable String app,@RequestParam(value="from", required = false) String from, @RequestParam(value="to", required = false) String to) {
        return trackerService.countUsersByApp(app,from,to);
    }

}