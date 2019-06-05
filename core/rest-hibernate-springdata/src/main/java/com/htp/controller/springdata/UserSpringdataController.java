package com.htp.controller.springdata;

import com.htp.controller.reqest.SearchCriteria;
import com.htp.domain.HRole;
import com.htp.domain.HUser;
import com.htp.domain.User;
import com.htp.repository.springdata.SpringDataRoleRepository;
import com.htp.repository.springdata.SpringDataUserRepository;
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
@RequestMapping(value = "/rest/sprigData_users")
public class UserSpringdataController {
  @Autowired private SpringDataUserRepository springDataUserRepository;

  @Autowired private SpringDataRoleRepository springDataRoleRepository;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<HUser>> getUsers() {

    return new ResponseEntity<>(springDataUserRepository.findAll(), HttpStatus.OK);
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
  public ResponseEntity<HUser> getUserById(@ApiParam("User Id") @PathVariable Long id) {
    return new ResponseEntity<>(springDataUserRepository.findByUserId(id), HttpStatus.OK);
  }

  @PostMapping
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<HUser> createUser(@RequestBody User request) {
    HUser hUser = new HUser();
    hUser.setLogin(request.getLogin());
    hUser.setPassword(request.getPassword());
    hUser.setUserName(request.getUserName());
    hUser.setUserSurname(request.getUserSurname());
    hUser.setUserNumber(request.getUserNumber());
    hUser.setRegisterDate(request.getRegisterDate());
    hUser.setBlock(request.getBlock());

    HUser savedUser = springDataUserRepository.save(hUser);
    springDataRoleRepository.save(new HRole(savedUser, "ROLE_USER"));

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
  public ResponseEntity<HUser> updateUser(@PathVariable("id") Long id, @RequestBody User request) {
    HUser hUser = springDataUserRepository.findByUserId(id);
    hUser.setLogin(request.getLogin());
    hUser.setPassword(request.getPassword());
    hUser.setUserName(request.getUserName());
    hUser.setUserSurname(request.getUserSurname());
    hUser.setUserNumber(request.getUserNumber());
    hUser.setRegisterDate(request.getRegisterDate());
    hUser.setBlock(request.getBlock());

    return new ResponseEntity<>(hUser, HttpStatus.OK);
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
  public ResponseEntity<List<HUser>> searchUsers(@ApiIgnore @ModelAttribute SearchCriteria search) {
    List<HUser> searchResult = springDataUserRepository.findAllByUserNameLike(search.getQuery());
    return new ResponseEntity<>(searchResult, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Long> deleteUser(@PathVariable("id") Long id) {
    springDataUserRepository.delete(springDataUserRepository.findByUserId(id));
    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}
