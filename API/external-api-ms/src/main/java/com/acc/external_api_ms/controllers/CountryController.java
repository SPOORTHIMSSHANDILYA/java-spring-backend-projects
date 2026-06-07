package com.acc.external_api_ms.controllers;

import com.acc.common_lib.models.Response;
import com.acc.external_api_ms.services.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/country")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public Response getAllCountries(){
        return new Response(HttpStatus.OK,"Success",countryService.getAllCountries(),"Successfully retrieved all countries");
    }

    @GetMapping("/searchByName/{name}")
    public Response getCountriesByName(@PathVariable String name){
        return new Response(HttpStatus.OK,"Success",countryService.getCountryByName(name),"Successfully retrieved country by name");
    }

    @GetMapping("/searchByFullName/{name}")
    public Response getCountriesByFullName(@PathVariable String name,@RequestParam boolean fullText){
        return new Response(HttpStatus.OK,"Success",countryService.getCountryByFullName(name, fullText),"Successfully retrieved country by full name");
    }

    @GetMapping("/searchByCode/{code}")
    public Response getCountriesByCode(@PathVariable String code){
        return new Response(HttpStatus.OK,"Success",countryService.getCountryByCode(code),"Successfully retrieved country by code");
    }

    @GetMapping("/searchByCodes")
    public Response getCountriesByCodes(@RequestParam List<String> codes){
        return new Response(HttpStatus.OK,"Success",countryService.getCountryByCodes(codes),"Successfully retrieved country by codes");
    }

    @GetMapping("/searchByLanguage/{language}")
    public Response getCountriesByLanguage(@PathVariable String language){
        return new Response(HttpStatus.OK,"Success",countryService.getCountryByLanguage(language),"Successfully retrieved country by language");
    }

    @GetMapping("/searchByCity/{city}")
    public Response getCountryByCapitalCity(@PathVariable String city){
        return new Response(HttpStatus.OK,"Success",countryService.getCountryByCapitalCity(city),"Successfully retrieved country by city");
    }

    @GetMapping("/searchByRegion/{region}")
    public Response getCountriesByRegion(@PathVariable String region){
        return new Response(HttpStatus.OK,"Success",countryService.getCountryByRegion(region),"Successfully retrieved country by region");
    }

    @GetMapping("/searchBySubregion/{subregion}")
    public Response getCountryBySubregion(@PathVariable String subregion){
        return new Response(HttpStatus.OK,"Success",countryService.getCountryBySubregion(subregion),"Successfully retrieved country by subregion");
    }

    @GetMapping("/searchByDemonym/{demonym}")
    public Response getCountryByDemonym(@PathVariable String demonym){
        return new Response(HttpStatus.OK,"Success",countryService.getCountryByDemonym(demonym),"Successfully retrieved country by demonym");
    }

    @GetMapping("/searchByTranslation/{translation}")
    public Response getCountryByTranslation(@PathVariable String translation){
        return new Response(HttpStatus.OK,"Success",countryService.getCountryByTranslation(translation),"Successfully retrieved country by translation");
    }

    @GetMapping("/independentCountries/{status}")
    public Response getAllIndependentCountries(@PathVariable boolean status, @RequestParam List<String> fields){
        return new Response(HttpStatus.OK,"Success",countryService.getAllIndependentCountries(status, fields),"Successfully retrieved independent countries");
    }
}
