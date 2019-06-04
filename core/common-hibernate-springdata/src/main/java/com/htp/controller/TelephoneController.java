package com.htp.controller;


import com.htp.controller.reqest.SearchCriteria;
import com.htp.domain.Telephones;
import com.htp.repository.TelephonesDao;
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
@RequestMapping(value = "/rest/telephones")
public class TelephoneController {
    @Autowired
    private TelephonesDao telephonesDao;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Telephones>> getTelephones() {
        return new ResponseEntity<>(telephonesDao.findAll(), HttpStatus.OK);
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
    public ResponseEntity<Telephones> getTelephoneById(@ApiParam("Telephones Path Id") @PathVariable Long id) {
        Telephones telephones = telephonesDao.findById(id);
        return new ResponseEntity<>(telephones, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Telephones> createTelephone(@RequestBody Telephones request) {
        Telephones telephones = new Telephones();


        telephones.setTelName(request.getTelName());
        telephones.setTelSurname(request.getTelSurname());
        telephones.setTelNumber(request.getTelNumber());
        telephones.setCreationDate(request.getCreationDate());
        telephones.setTelBlock(request.getTelBlock());
        telephones.setUserId(request.getUserId());

        Telephones savedTelephones = telephonesDao.create(telephones);

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
    public ResponseEntity<Telephones> updateTelephones(
            @PathVariable("id") Long id, @RequestBody Telephones request) {
        Telephones telephones = telephonesDao.findById(id);
        telephones.setTelName(request.getTelName());
        telephones.setTelSurname(request.getTelSurname());
        telephones.setTelNumber(request.getTelNumber());
        telephones.setCreationDate(request.getCreationDate());
        telephones.setTelBlock(request.getTelBlock());
        telephones.setUserId(request.getUserId());

        return new ResponseEntity<>(telephones, HttpStatus.OK);
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
    public ResponseEntity<List<Telephones>> searchTelephonesByName(@ApiIgnore @ModelAttribute SearchCriteria search) {
        List<Telephones> searchResult =
                telephonesDao.searchByNameAndSurname(search.getQuery(), search.getLimit(), search.getOffset());
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

    @GetMapping("/search_by_telephone")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Telephones>> searchTelephonesByNumber(@ApiIgnore @ModelAttribute SearchCriteria search) {
        List<Telephones> searchResult =
                telephonesDao.searchByNumber(search.getQuery(), search.getLimit(), search.getOffset());
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteTelephones(@PathVariable("id") Long id) {
        telephonesDao.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}