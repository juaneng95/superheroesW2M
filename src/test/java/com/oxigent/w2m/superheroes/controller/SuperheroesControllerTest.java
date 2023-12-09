package com.oxigent.w2m.superheroes.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.oxigent.w2m.superheroes.exception.ApiExceptionHandler;
import com.oxigent.w2m.superheroes.model.SuperheroDto;
import com.oxigent.w2m.superheroes.model.SuperheroEntityRest;
import com.oxigent.w2m.superheroes.service.SuperheroesService;
import java.nio.charset.StandardCharsets;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {SuperheroesService.class})
class SuperheroesControllerTest {

  private MockMvc mockMvc;

  @MockBean SuperheroesService shService;

  final EasyRandom easyRandom =
      new EasyRandom(
          new EasyRandomParameters()
              .objectPoolSize(1)
              .randomizationDepth(1)
              .stringLengthRange(5, 25)
              .collectionSizeRange(1, 1)
              .scanClasspathForConcreteTypes(true)
              .overrideDefaultInitialization(false)
              .ignoreRandomizationErrors(false));

  // MediaType needed to testing.
  final MediaType APPLICATION_JSON_UTF8 =
      new MediaType(
          MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          StandardCharsets.UTF_8);

  // Random objects.
  final SuperheroEntityRest superheroRest = easyRandom.nextObject(SuperheroEntityRest.class);
  final SuperheroDto superheroDto = easyRandom.nextObject(SuperheroDto.class);

  final String pathGetAllSuperheroes = "/superhero/list";
  final String pathGetSuperheroById = "/superhero/{id}";
  final String pathGetAllSuperheroesByPattern = "/superhero/list/{pattern}";
  final String pathCreateSuperhero = "/superhero/create";
  final String pathUpdateSuperheroById = "/superhero/update/{id}";
  final String pathDeleteSuperheroById = "/superhero/delete/{id}";

  @BeforeEach
  void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(new SuperheroesController(shService))
            .setControllerAdvice(new ApiExceptionHandler())
            .build();
  }

  @Test
  void getAllSuperheroes_OK() throws Exception {
    mockMvc
        .perform(
            get(pathGetAllSuperheroes)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void getSuperheroById_OK() throws Exception {
    mockMvc
        .perform(
            get(pathGetSuperheroById, superheroDto.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void getSuperheroById_NotFoundSuperheroException() throws Exception {
    mockMvc
        .perform(
            get(pathGetSuperheroById, "")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  void getAllSuperheroesByPattern_OK() throws Exception {
    mockMvc
        .perform(
            get(pathGetAllSuperheroesByPattern, "man")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void createSuperhero_OK() throws Exception {
    // Mandatory patterns
    superheroRest.setVigor(100);
    superheroRest.setMind(100);
    superheroRest.setEndurance(100);
    superheroRest.setEndurance(100);
    superheroRest.setStrength(100);
    superheroRest.setDexterity(100);
    superheroRest.setIntelligence(100);
    superheroRest.setSpeed(100);

    mockMvc
        .perform(
            post(pathCreateSuperhero)
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8)
                .content(requestBodyToString(superheroRest))) // Adding requestBody in json format
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  void updateSuperheroById_OK() throws Exception {
    // Mandatory patterns
    superheroRest.setVigor(100);
    superheroRest.setMind(100);
    superheroRest.setEndurance(100);
    superheroRest.setEndurance(100);
    superheroRest.setStrength(100);
    superheroRest.setDexterity(100);
    superheroRest.setIntelligence(100);
    superheroRest.setSpeed(100);

    mockMvc
        .perform(
            put(pathUpdateSuperheroById, superheroDto.getId())
                .contentType(APPLICATION_JSON_UTF8)
                .accept(APPLICATION_JSON_UTF8)
                .content(requestBodyToString(superheroRest))) // Adding requestBody in json format
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void deleteSuperheroById_OK() throws Exception {
    mockMvc
        .perform(
            delete(pathDeleteSuperheroById, any(Long.class))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNoContent());
  }

  private String requestBodyToString(Object requestBody) throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    return ow.writeValueAsString(requestBody);
  }
}
