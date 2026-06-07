package com.acc.external_api_ms.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;

import java.util.List;

@Service
@Slf4j
public class CountryService {
    @Value("${api.countryApi.baseUrl}")
    private String baseUrl;
    private final RestTemplate restTemplate;

    public CountryService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public JsonNode getAllCountries(){
        String finalUrl = baseUrl + "/all?fields=name,flags,cca2";

        JsonNode response = restTemplate.getForObject(finalUrl, JsonNode.class);
        if (response == null || response.isEmpty()) {
            log.info("No countries returned from Country API");
            return null;
        }
        return response;
    }

    public JsonNode getCountryByName(String countryName){
        String finalUrl = baseUrl + "/name/" + countryName;
        JsonNode response = restTemplate.getForObject(finalUrl, JsonNode.class);
        if (response == null || response.isEmpty()) {
            log.info("No countries with name {} returned from Country API", countryName);
            return null;
        }
        return response;
    }

    public JsonNode getCountryByFullName(String countryName, boolean fullText){
        String finalUrl = baseUrl + "/name/" + countryName +"?fullText=" + fullText;
        JsonNode response = restTemplate.getForObject(finalUrl, JsonNode.class);
        if (response == null || response.isEmpty()) {
            log.info("No countries with full name {} returned from Country API", countryName);
            return null;
        }
        return response;
    }

    public JsonNode getCountryByCode(String code){
        String finalUrl = baseUrl + "/alpha/" + code;
        JsonNode response = restTemplate.getForObject(finalUrl, JsonNode.class);
        if (response == null || response.isEmpty()) {
            log.info("No countries with code {} returned from Country API", code);
            return null;
        }
        return response;
    }

    public JsonNode getCountryByCodes(List<String> codes){
        String finalUrl = baseUrl + "/alpha?codes=" + String.join(",",codes);
        JsonNode response = restTemplate.getForObject(finalUrl, JsonNode.class);
        if (response == null || response.isEmpty()) {
            log.info("No countries with codes {} returned from Country API", codes);
            return null;
        }
        return response;
    }


    public JsonNode getCountryByCurrency(String currency){
        String finalUrl = baseUrl + "/currency/" + currency;
        JsonNode response = restTemplate.getForObject(finalUrl, JsonNode.class);
        if (response == null || response.isEmpty()) {
            log.info("No countries with currency {} returned from Country API", currency);
            return null;
        }
        return response;
    }

    public JsonNode getCountryByLanguage(String language){
        String finalUrl = baseUrl + "/lang/" + language;
        JsonNode response = restTemplate.getForObject(finalUrl, JsonNode.class);
        if (response == null || response.isEmpty()) {
            log.info("No countries with language {} returned from Country API", language);
            return null;
        }
        return response;
    }

    public JsonNode getCountryByCapitalCity(String capital){
        String finalUrl = baseUrl + "/capital/" + capital;
        JsonNode response = restTemplate.getForObject(finalUrl, JsonNode.class);
        if (response == null || response.isEmpty()) {
            log.info("No countries with capital {} returned from Country API", capital);
            return null;
        }
        return response;
    }

    public JsonNode getCountryByRegion(String region){
        String finalUrl = baseUrl + "/region/" + region;
        JsonNode response = restTemplate.getForObject(finalUrl, JsonNode.class);
        if (response == null || response.isEmpty()) {
            log.info("No countries with region {} returned from Country API", region);
            return null;
        }
        return response;
    }

    public JsonNode getCountryBySubregion(String subregion){
        String finalUrl = baseUrl + "/subregion/" + subregion;
        JsonNode response = restTemplate.getForObject(finalUrl, JsonNode.class);
        if (response == null || response.isEmpty()) {
            log.info("No countries with subregion {} returned from Country API", subregion);
            return null;
        }
        return response;
    }

    public JsonNode getCountryByDemonym(String demonym){
        String finalUrl = baseUrl + "/demonym/" + demonym;
        JsonNode response = restTemplate.getForObject(finalUrl, JsonNode.class);
        if (response == null || response.isEmpty()) {
            log.info("No countries with demonym {} returned from Country API", demonym);
            return null;
        }
        return response;
    }

    public JsonNode getCountryByTranslation(String translation){
        String finalUrl = baseUrl + "/translation/" + translation;
        JsonNode response = restTemplate.getForObject(finalUrl, JsonNode.class);
        if (response == null || response.isEmpty()) {
            log.info("No countries with translation {} returned from Country API", translation);
            return null;
        }
        return response;
    }

    public JsonNode getAllIndependentCountries(boolean status, List<String> fields){
        String finalUrl = baseUrl + "/independent?status=" + status +"&fields=" + String.join(",",fields);
        JsonNode response = restTemplate.getForObject(finalUrl, JsonNode.class);
        if (response == null || response.isEmpty()) {
            log.info("No independent countries returned from Country API");
            return null;
        }
        return response;
    }

}
