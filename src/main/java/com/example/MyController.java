package com.example;

import com.gmfinancial.gmpcp.consumingwebservice.wsdl.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Tag(name = "Tutorial", description = "Tutorial management APIs")
public class MyController {

    private final CountryClient countryClient;
    private final NumberClient numberClient;

    public MyController(CountryClient countryClient, NumberClient numberClient) {
        this.countryClient = countryClient;
        this.numberClient = numberClient;
    }

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Details of All the Participants",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content)
    })

    @GetMapping(value = "/add/{num1}/{num2}", produces = APPLICATION_JSON_VALUE)
    public NumberToWordsResponse getTime(@Parameter(description = "Unique number1", example = "1", required = true) @NotBlank @PathVariable int num1,
                               @Parameter(description = "Unique number2", example = "2", required = true) @PathVariable int num2) {
        return numberClient.numberToWords();
        //return countryClient.add(num1, num2);
    }


    @PostMapping(value = "/add")
    public ResponseEntity<AddResponse> getTime(@Validated @RequestBody Add add){
        AddResponse response = new AddResponse();
        response.setAddResult(add.getIntA() + add.getIntB());
        return new  ResponseEntity<AddResponse>(response,HttpStatus.OK);
    }

    @PostMapping(value = "/test")
    public ResponseEntity<DTO> testValidation(@Valid @RequestBody DTO dto){
        return ResponseEntity.ok(dto);
    }

//https://medium.com/@prabha.shankaran/creating-openapi-definitions-for-apis-using-springdoc-4c7a5c80a7cf
}
