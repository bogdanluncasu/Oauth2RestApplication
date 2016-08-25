package controller;

import entity.User;
import exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;


@RestController
@RequestMapping(value="/users",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/add",method= RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<User>> addUser(@RequestBody User user) throws UserException {
        System.out.println(user.getUsername());
        userService.addUser(user);
        Resource<User> resource=new Resource<>(user);
//        resource.add(linkTo(methodOn(User.class).championshipCountry(c.getCountry())).withSelfRel());
        return new ResponseEntity(resource, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public HttpEntity<Resource<User>> getUserById(@PathVariable Integer id){
        User user=userService.getUserById(id);
        return new ResponseEntity(new Resource<User>(user),HttpStatus.OK);

    }






}
