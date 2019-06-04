package com.htp.controller;

import com.htp.controller.reqest.SearchCriteria;
import com.htp.domain.hibernate.HTelephones;
import com.htp.repository.hibernate.HibernateTelephonesDao;
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
@RequestMapping(value = "/rest/hibernate_telephones")
public class TelephoneHibernateController {
  @Autowired private HibernateTelephonesDao hibernateTelephonesDao;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<HTelephones>> getTelephones() {
    return new ResponseEntity<>(hibernateTelephonesDao.findAll(), HttpStatus.OK);
  }

  @ApiOperation(value = "Get telephones from server by id")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Success. Congratulations!"),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 401, message = "Unauthorized"),
    @ApiResponse(code = 404, message = "Not found"),
    @ApiResponse(code = 500, message = "Server error, something wrong")
  })
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<HTelephones> getTelephoneById(
      @ApiParam("Telephones Path Id") @PathVariable Long id) {
      HTelephones telephones = hibernateTelephonesDao.findById(id);
    return new ResponseEntity<>(telephones, HttpStatus.OK);
  }

  @PostMapping
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<HTelephones> createTelephone(@RequestBody HTelephones request) {
      HTelephones hTelephones = new HTelephones();

    hTelephones.setTelName(request.getTelName());
    hTelephones.setTelSurname(request.getTelSurname());
    hTelephones.setTelNumber(request.getTelNumber());
    hTelephones.setCreationDate(request.getCreationDate());
    hTelephones.setTelBlock(request.getTelBlock());
//    hTelephones.setUserId(request.getUserId());

      HTelephones savedTelephones = hibernateTelephonesDao.create(hTelephones);

    return new ResponseEntity<>(savedTelephones, HttpStatus.OK);
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
  public ResponseEntity<HTelephones> updateTelephones(
      @PathVariable("id") Long id, @RequestBody HTelephones request) {
      HTelephones hTelephones = hibernateTelephonesDao.findById(id);
    hTelephones.setTelName(request.getTelName());
    hTelephones.setTelSurname(request.getTelSurname());
    hTelephones.setTelNumber(request.getTelNumber());
    hTelephones.setCreationDate(request.getCreationDate());
    hTelephones.setTelBlock(request.getTelBlock());
//    hTelephones.setUserId(request.getUserId());

    return new ResponseEntity<>(hTelephones, HttpStatus.OK);
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
        value = "limit of telephones",
        required = true,
        dataType = "int",
        paramType = "query"),
    @ApiImplicitParam(
        name = "offset",
        value = "start node of telephones",
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
  @GetMapping("/search_by_name")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<HTelephones>> searchTelephonesByName(
      @ApiIgnore @ModelAttribute SearchCriteria search) {
    List<HTelephones> searchResult =
        hibernateTelephonesDao.search(
            search.getQuery(), search.getLimit(), search.getOffset());
    return new ResponseEntity<>(searchResult, HttpStatus.OK);
  }

  @GetMapping("/search_by_telephone")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<HTelephones>> searchTelephonesByNumber(
      @ApiIgnore @ModelAttribute SearchCriteria search) {
    List<HTelephones> searchResult =
        hibernateTelephonesDao.search( //ERROR!!!!!!!!!!!!!!!!!!!!!!
            search.getQuery(), search.getLimit(), search.getOffset());
    return new ResponseEntity<>(searchResult, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Long> deleteTelephones(@PathVariable("id") Long id) {
    hibernateTelephonesDao.delete(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}
