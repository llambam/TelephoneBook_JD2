package com.htp.controller.springdata;

import com.htp.domain.HRole;
import com.htp.domain.Role;
import com.htp.repository.springdata.SpringDataRoleRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/sprigData_roles")
public class RoleSpringdataController {
  @Autowired private SpringDataRoleRepository springDataRoleRepository;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<HRole>> getRoles() {

    return new ResponseEntity<>(springDataRoleRepository.findAll(), HttpStatus.OK);
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
  public ResponseEntity<HRole> getUserRoleByRoleId(@ApiParam("Role Id") @PathVariable Long id) {
    return new ResponseEntity<>(springDataRoleRepository.findByUserRoleId(id), HttpStatus.OK);
  }

  @ApiOperation(value = "Update role by roleID")
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
  public ResponseEntity<HRole> updateRole(@PathVariable("id") Long id, @RequestBody Role request) {
    HRole hRole = springDataRoleRepository.findByUserRoleId(id);
    hRole.setUserRole(request.getUserRole());
    return new ResponseEntity<>(hRole, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Long> deleteRole(@PathVariable("id") Long id) {
    springDataRoleRepository.delete(springDataRoleRepository.findByUserRoleId(id));
    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}
