package com.htp.controller;

import com.htp.domain.Adress;
import com.htp.repository.AdressDao;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/adress")
public class AdressController extends Object {
  @Autowired private AdressDao adressDao;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Adress>> getAdreses() {
    return new ResponseEntity<>(adressDao.findAll(), HttpStatus.OK);
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
  public ResponseEntity<Adress> getAdressById(@ApiParam("Role Path Id") @PathVariable Long id) {
    Adress adress = adressDao.findById(id);
    return new ResponseEntity<>(adress, HttpStatus.OK);
  }

  @PostMapping
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Adress> createAdress(@RequestBody Adress request) {
    Adress adress = new Adress();

    adress.setCountry(request.getCountry());
    adress.setCity(request.getCity());
    adress.setStreet(request.getStreet());
    adress.setHouseNumber(request.getHouseNumber());
    adress.setFloor(request.getFloor());
    adress.setApartmentNumber(request.getApartmentNumber());
    adress.setUserId(request.getUserId());
    adress.setTelId(request.getTelId());
    adress.setAdressBlock(request.getAdressBlock());
    adress.setUserAdress(request.isUserAdress());

    Adress savedAdreses = adressDao.create(adress);

    return new ResponseEntity<>(savedAdreses, HttpStatus.OK);
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
  public ResponseEntity<Adress> updateAdress(
      @PathVariable("id") Long id, @RequestBody Adress request) {
    Adress adress = adressDao.findById(id);
    adress.setCountry(request.getCountry());
    adress.setCity(request.getCity());
    adress.setStreet(request.getStreet());
    adress.setHouseNumber(request.getHouseNumber());
    adress.setFloor(request.getFloor());
    adress.setApartmentNumber(request.getApartmentNumber());
    adress.setUserId(request.getUserId());
    adress.setTelId(request.getTelId());
    adress.setAdressBlock(request.getAdressBlock());
    adress.setUserAdress(request.isUserAdress());

    return new ResponseEntity<>(adress, HttpStatus.OK);
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
  public ResponseEntity<Long> deleteAdress(@PathVariable("id") Long id) {
    adressDao.delete(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}
