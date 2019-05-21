package com.htp.controller;

import com.htp.controller.reqest.SearchCriteria;
import com.htp.domain.Role;
import com.htp.repository.RoleDao;
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
@RequestMapping(value = "/rest/roles")
public class RoleController {
  @Autowired private RoleDao roleDao;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Role>> getRoles() {
    return new ResponseEntity<>(roleDao.findAll(), HttpStatus.OK);
  }

  @ApiOperation(value = "Get Roles from server by id")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Success. Congratulations!"),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 401, message = "Unauthorized"),
    @ApiResponse(code = 404, message = "Not found"),
    @ApiResponse(code = 500, message = "Server error, something wrong")
  })
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Role> getRoleById(
      @ApiParam("Role Path Id") @PathVariable Long id) {
    Role role = roleDao.findById(id);
    return new ResponseEntity<>(role, HttpStatus.OK);
  }

  @PostMapping
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Role> createRole(@RequestBody Role request) {
    Role role = new Role();

    role.setUserId(request.getUserId());
    role.setUserRole(request.getUserRole());

    Role savedRoles = roleDao.create(role);

    return new ResponseEntity<>(savedRoles, HttpStatus.OK);
  }

  @ApiOperation(value = "Update Telephones by ID")
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
  public ResponseEntity<Role> updateRole(
      @PathVariable("id") Long id, @RequestBody Role request) {
    Role role = roleDao.findById(id);
      role.setUserId(request.getUserId());
      role.setUserRole(request.getUserRole());

    return new ResponseEntity<>(role, HttpStatus.OK);
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
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Long> deleteRole(@PathVariable("id") Long id) {
    roleDao.delete(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}
