package com.htp.controller;

import com.htp.controller.reqest.SearchCriteria;
import com.htp.domain.Role;
import com.htp.domain.User;
import com.htp.repository.RoleDao;
import com.htp.repository.UserDao;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/users")
public class UserController {
  @Autowired private UserDao userDao;

  @Autowired private RoleDao roleDao;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<User>> getUsers() {
    return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
  }

  @ApiOperation(value = "Get user from server by id")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Success. Congratulations!"),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 401, message = "Unauthorized"),
    @ApiResponse(code = 404, message = "Not found"),
    @ApiResponse(code = 500, message = "Server error, something wrong")
  })
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<User> getUserById(@ApiParam("User Path Id") @PathVariable Long id) {
    User user = userDao.findById(id);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PostMapping
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<User> createUser(@RequestBody User request) {
    User user = new User();
    // userID and user_block is empty - will be generated by DB
    // why in example we use user without log and pass?(UserReqest)
    user.setLogin(request.getLogin());
    user.setPassword(request.getPassword());
    user.setUserName(request.getUserName());
    user.setUserSurname(request.getUserSurname());
    user.setUserNumber(request.getUserNumber());
    user.setRegisterDate(request.getRegisterDate());
    user.setBlock(request.getBlock());

    User savedUser = userDao.create(user);
    roleDao.create(new Role(savedUser.getUserId(), savedUser.getUserId(), "ROLE_USER"));

    return new ResponseEntity<>(savedUser, HttpStatus.OK);
  }

  @ApiOperation(value = "Update user by userID")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Success. Congratulations!"),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 401, message = "Unauthorized"),
    @ApiResponse(code = 404, message = "Not found"),
    @ApiResponse(code = 500, message = "Server error, something wrong")
  })
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "X-Auth-Token",
        value = "token",
        required = true,
        dataType = "string",
        paramType = "header")
  })
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User request) {
    User user = userDao.findById(id);
    user.setLogin(request.getLogin());
    user.setPassword(request.getPassword());
    user.setUserName(request.getUserName());
    user.setUserSurname(request.getUserSurname());
    user.setUserNumber(request.getUserNumber());
    user.setRegisterDate(request.getRegisterDate());
    user.setBlock(request.getBlock());

    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @ApiOperation(value = "Search user by query")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Success. Congratulations!"),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 401, message = "Unauthorized"),
    @ApiResponse(code = 404, message = "Not found"),
    @ApiResponse(code = 500, message = "Server error, something wrong")
  })
  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "limit",
        value = "limit of users",
        required = true,
        dataType = "int",
        paramType = "query"),
    @ApiImplicitParam(
        name = "offset",
        value = "start node of users",
        required = true,
        dataType = "int",
        paramType = "query"),
    @ApiImplicitParam(
        name = "query",
        value = "searchByNameAndSurname query",
        required = true,
        dataType = "string",
        paramType = "query"),
    @ApiImplicitParam(
        name = "X-Auth-Token",
        value = "token",
        required = true,
        dataType = "string",
        paramType = "header")
  })
  @GetMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<User>> searchUsers(@ApiIgnore @ModelAttribute SearchCriteria search) {
    List<User> searchResult =
        userDao.search(search.getQuery(), search.getLimit(), search.getOffset());
    return new ResponseEntity<>(searchResult, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Long> deleteUser(@PathVariable("id") Long id) {
    userDao.delete(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}
