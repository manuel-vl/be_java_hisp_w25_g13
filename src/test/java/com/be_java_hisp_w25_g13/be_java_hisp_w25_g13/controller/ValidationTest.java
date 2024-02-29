package com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.controller;


import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.PostDTO;
import com.be_java_hisp_w25_g13.be_java_hisp_w25_g13.dto.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ValidationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createPostNotPositiveUserId() throws Exception{
        PostDTO postDto = new PostDTO(0, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"Arepa", "Comida", "Quesudas", "Rosa", "ifiefbeifb"),
                1, 13D);


        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                .contentType(MediaType.APPLICATION_JSON).content(serializePost(
                        postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("userId: El id debe ser mayor a cero"));
    }
    @Test
    void createPostNullDate() throws Exception{
        PostDTO postDto = new PostDTO(1, null,
                new ProductDTO(1,"Arepa", "Comida", "Quesudas", "Rosa", "ifiefbeifb"),
                1, 13D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("date: La fecha no puede estar vacía"));
    }
    @Test
    void createPostNullCategory() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"Arepa", "Comida", "Quesudas", "Rosa", "ifiefbeifb"),
                null, 13D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("category: El campo no puede estar vacío"));
    }
    @Test
    void createPostNullPrice() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"Arepa", "Comida", "Quesudas", "Rosa", "ifiefbeifb"),
                1, null);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("price: El campo no puede estar vacío"));
    }
    @Test
    void createPostPriceExceed() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"Arepa", "Comida", "Quesudas", "Rosa", "ifiefbeifb"),
                1, 10000001D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("price: El precio máximo por producto es de 10.000.000"));
    }

    @Test
    void createPostNullProductId() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(null,"Arepa", "Comida", "Quesudas", "Rosa", "ifiefbeifb"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.productId: El id no puede estar vacío"));
    }
    @Test
    void createPostNotPositiveProductId() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(0,"Arepa", "Comida", "Quesudas", "Rosa", "ifiefbeifb"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.productId: El id debe ser mayor a cero"));
    }
    @Test
    void createPostBlankProductName() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,null, "Comida", "Quesudas", "Rosa", "ifiefbeifb"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.productName: El campo no puede estar vacío"));
    }
    @Test
    void createPostProductNameEspecialCharacters() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"$", "Comida", "Quesudas", "Rosa", "ifiefbeifb"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.productName: El campo no puede poseer caracteres especiales"));
    }
    @Test
    void createPostProductNameSizeExceed() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"uwebfwfbwoefbwufevwefwbfwlivgwovwbvwuvgwwuvuv", "Comida", "Quesudas", "Rosa", "ifiefbeifb"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.productName: La longitud no puede superar los 40 caracteres"));
    }
    //Type
    @Test
    void createPostEmptyType() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"Arepa", null, "Quesudas", "Rosa", "ifiefbeifb"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.type: El campo no puede estar vacío"));
    }
    @Test
    void createPostTypeSpecialCharacters() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"Arepa", "$", "Quesudas", "Rosa", "ifiefbeifb"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.type: El campo no puede poseer caracteres especiales"));
    }
    @Test
    void createPostTypeSizeExceed() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"Arepa", "asdfghjqeertyuiy", "Quesudas", "Rosa", "ifiefbeifb"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.type: La longitud no puede superar los 15 caracteres"));
    }
    //Brand
    @Test
    void createPostEmptyBrand() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"Arepa", "Arepa", null, "Rosa", "ifiefbeifb"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.brand: El campo no puede estar vacío"));
    }
    @Test
    void createPostBrandSpecialCharacters() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"Arepa", "Arepa", "#", "Rosa", "ifiefbeifb"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.brand: El campo no puede poseer caracteres especiales"));
    }
    @Test
    void createPostBrandSizeExceed() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"Arepa", "Arepa", "dhfhdncjdweidndfjencejjfjdjdeedc", "Rosa", "ifiefbeifb"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.brand: La longitud no puede superar los 25 caracteres"));
    }
    //Color
    @Test
    void createPostEmptyColor() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"Arepa", "Arepa", "Quesudas", null, "ifiefbeifb"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.color: El campo no puede estar vacío"));
    }
    @Test
    void createPostColorSpecialCharacters() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"Arepa", "Arepa", "Quesudas", "#", "ifiefbeifb"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.color: El campo no puede poseer caracteres especiales"));
    }
    @Test
    void createPostColorSizeExceed() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"Arepa", "Arepa", "Quesudas", "fwnfiewnfiwfbwiefbwufvwuf", "ifiefbeifb"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.color: La longitud no puede superar los 15 caracteres"));
    }
    //Notes
    @Test
    void createPostNotesSpecialCharacters() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"Arepa", "Arepa", "Quesudas", "rosa", "#"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.notes: El campo no puede poseer caracteres especiales"));
    }
    @Test
    void createPostNotesSizeExceed() throws Exception{
        PostDTO postDto = new PostDTO(1, LocalDate.parse("2024-01-03"),
                new ProductDTO(1,"Arepa", "Arepa", "Quesudas", "rosa", "ifiefbeifbwifwhifwfowifgwoufgwufwfwvfwuvgwivwhvhewviheoivhewifhewiofhewoifeufgeufgeoivbeoifgeoufgeufgeuwfgwufgwufgwiufgwiufg"),
                1, 12D);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON).content(serializePost(postDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.messages").value("product.notes: La longitud no puede superar los 80 caracteres"));
    }
    private String serializePost(PostDTO postDto) throws JsonProcessingException{
        ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).writer();

        return ow.writeValueAsString(postDto);
    }
}
