package com.htp.controller.hibernate;

import com.htp.domain.HFavorite;
import com.htp.repository.hibernate.impl.HibernateFavoriteImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/hibernate_favorites")
public class FavoriteHibernateController {
  @Autowired private HibernateFavoriteImpl hibernateFavoriteImpl;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<HFavorite>> getFavorites() {
    return new ResponseEntity<>(hibernateFavoriteImpl.findAll(), HttpStatus.OK);
  }

  @ApiOperation(value = "Get favorites from server by id")
  @ApiResponses({
    @ApiResponse(code = 200, message = "Success. Congratulations!"),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 401, message = "Unauthorized"),
    @ApiResponse(code = 404, message = "Not found"),
    @ApiResponse(code = 500, message = "Server error, something wrong")
  })
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<HFavorite> getFavoritesById(
      @ApiParam("Telephones Path Id") @PathVariable Long id) {
    HFavorite favorite = hibernateFavoriteImpl.findById(id);
    return new ResponseEntity<>(favorite, HttpStatus.OK);
  }

  @PostMapping
  @Transactional
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<HFavorite> createFavorites(@RequestBody HFavorite request) {
    HFavorite hFavorite = new HFavorite();

    hFavorite.setFavoriteId(request.getFavoriteId());
    hFavorite.setFavoriteBlock(request.getFavoriteBlock());
    hFavorite.setTelephones(request.getTelephones());
    hFavorite.setUser(request.getUser());
    HFavorite favorite = hibernateFavoriteImpl.create(hFavorite);

    return new ResponseEntity<>(favorite, HttpStatus.OK);
  }

  @ApiOperation(value = "Update favorites by ID")
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
  public ResponseEntity<HFavorite> updateFavorites(
      @PathVariable("id") Long id, @RequestBody HFavorite request) {
    HFavorite hFavorite = hibernateFavoriteImpl.findById(id);

    hFavorite.setFavoriteId(request.getFavoriteId());
    hFavorite.setFavoriteBlock(request.getFavoriteBlock());
    hFavorite.setTelephones(request.getTelephones());
    hFavorite.setUser(request.getUser());

    return new ResponseEntity<>(hFavorite, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Long> deleteFavorites(@PathVariable("id") Long id) {
    hibernateFavoriteImpl.delete(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}
