package com.htp.controller.hibernate;

import com.htp.domain.Adress;
import com.htp.domain.HAdress;
import com.htp.repository.hibernate.impl.HibernateAdressImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/hibernate_adress")
public class AdressHibernateController {
  @Autowired private HibernateAdressImpl hibernateAdressImpl;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<HAdress>> getAdreses() {
    return new ResponseEntity<>(hibernateAdressImpl.findAll(), HttpStatus.OK);
  }

  @ApiOperation(value = "Get adreses from server by id")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Success. Congratulations!"),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 401, message = "Unauthorized"),
    @ApiResponse(code = 404, message = "Not found"),
    @ApiResponse(code = 500, message = "Server error, something wrong")
  })
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<HAdress> getAdressById(@ApiParam("Adreses Path Id") @PathVariable Long id) {
    HAdress adress = hibernateAdressImpl.findById(id);
    return new ResponseEntity<>(adress, HttpStatus.OK);
  }

  @PostMapping
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<HAdress> createAdreses(@RequestBody Adress request) {
    HAdress hAdress = new HAdress();

    hAdress.setAdressId(request.getAdressId());
    hAdress.setCountry(request.getCountry());
    hAdress.setCity(request.getCity());
    hAdress.setStreet(request.getStreet());
    hAdress.setHouseNumber(request.getHouseNumber());
    hAdress.setFloor(request.getFloor());
    hAdress.setApartmentNumber(request.getApartmentNumber());
    hAdress.setAdressBlock(request.getAdressBlock());
    hAdress.setUserAdress(request.isUserAdress());

    HAdress adress = hibernateAdressImpl.create(hAdress);

    return new ResponseEntity<>(adress, HttpStatus.OK);
  }

  @ApiOperation(value = "Update Adress by ID")
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
  public ResponseEntity<HAdress> updateAdreses(
      @PathVariable("id") Long id, @RequestBody Adress request) {
    HAdress hAdress = hibernateAdressImpl.findById(id);
    hAdress.setAdressId(request.getAdressId());
    hAdress.setCountry(request.getCountry());
    hAdress.setCity(request.getCity());
    hAdress.setStreet(request.getStreet());
    hAdress.setHouseNumber(request.getHouseNumber());
    hAdress.setFloor(request.getFloor());
    hAdress.setApartmentNumber(request.getApartmentNumber());
    hAdress.setAdressBlock(request.getAdressBlock());
    hAdress.setUserAdress(request.isUserAdress());

    return new ResponseEntity<>(hAdress, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Long> deleteAdreses(@PathVariable("id") Long id) {
    hibernateAdressImpl.delete(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}
