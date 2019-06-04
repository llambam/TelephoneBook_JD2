package com.htp.controller;


import com.htp.repository.FavoriteDao;
import com.htp.domain.Favorite;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteDao favoriteDao;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Favorite>> getFavorites() {
        return new ResponseEntity<>(favoriteDao.findAll(), HttpStatus.OK);
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
    public ResponseEntity<Favorite> getFavoriteById(
            @ApiParam("Role Path Id") @PathVariable Long id) {
        Favorite favorite = favoriteDao.findById(id);
        return new ResponseEntity<>(favorite, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Favorite> createFavorite(@RequestBody Favorite request) {
        Favorite favorite = new Favorite();

        favorite.setUserId(request.getUserId());
        favorite.setTelId(request.getTelId());
        favorite.setFavoriteBlock(request.getFavoriteBlock());

        Favorite savedFavorites = favoriteDao.create(favorite);

        return new ResponseEntity<>(savedFavorites, HttpStatus.OK);
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
    public ResponseEntity<Favorite> updateFavorite(
            @PathVariable("id") Long id, @RequestBody Favorite request) {
        Favorite favorite = favoriteDao.findById(id);
        favorite.setUserId(request.getUserId());
        favorite.setTelId(request.getTelId());
        favorite.setFavoriteBlock(request.getFavoriteBlock());

        return new ResponseEntity<>(favorite, HttpStatus.OK);
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
    public ResponseEntity<Long> deleteFavorite(@PathVariable("id") Long id) {
        favoriteDao.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
