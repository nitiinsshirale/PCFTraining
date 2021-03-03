package io.pivotal.pal.tracker;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    public TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @RequestMapping(value="/time-entries",method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<TimeEntry> create (@RequestBody TimeEntry timeEntry) {
        HttpHeaders httpreshed = new HttpHeaders();
       // HttpEntity<TimeEntry> entity = new HttpEntity<TimeEntry>(timeEntry);
        TimeEntry timeEntry1 = timeEntryRepository.create(timeEntry);
        return new ResponseEntity<TimeEntry>(timeEntry1, httpreshed, HttpStatus.CREATED);
    }

    @RequestMapping(value="/time-entries/{id}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<TimeEntry> read (@PathVariable(value="id") long id) {
        HttpHeaders httpreshed = new HttpHeaders();
        TimeEntry timeEntry1 = timeEntryRepository.find(id);
        if (timeEntry1 == null){
            return new ResponseEntity<TimeEntry>(httpreshed, HttpStatus.NOT_FOUND);

        }else {
            return new ResponseEntity<TimeEntry>(timeEntry1, httpreshed, HttpStatus.OK);
        }
    }

    @RequestMapping(value="/time-entries",method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<TimeEntry>> list () {
        HttpHeaders httpreshed = new HttpHeaders();
        List<TimeEntry> listentry = timeEntryRepository.list();
        return new ResponseEntity<List<TimeEntry>>(listentry, httpreshed, HttpStatus.OK);

    }

    @RequestMapping(value="/time-entries/{id}",method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<TimeEntry> update (@PathVariable(value="id") long id, @RequestBody TimeEntry timeEntryToUpdate) {
        HttpHeaders httpreshed = new HttpHeaders();
        TimeEntry timeEntry1 = timeEntryRepository.update(id,timeEntryToUpdate);
        if(timeEntry1 == null)
        {
            return new ResponseEntity<TimeEntry>(timeEntry1, httpreshed, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<TimeEntry>(timeEntry1, httpreshed, HttpStatus.OK);
        }
    }

    @RequestMapping(value="/time-entries/{id}",method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Void> delete (@PathVariable(value="id") long id) {
        HttpHeaders httpreshed = new HttpHeaders();
        timeEntryRepository.delete(id);
        return new ResponseEntity<Void>(httpreshed, HttpStatus.NO_CONTENT);
    }







    }










