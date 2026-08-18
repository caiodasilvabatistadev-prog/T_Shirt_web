package com.cadastro.t_shirt_web.controller;

import com.cadastro.t_shirt_web.dto.ErrorResponseDTO;
import com.cadastro.t_shirt_web.dto.ProductRequestDTO;
import com.cadastro.t_shirt_web.dto.ProductResponseDTO;
import com.cadastro.t_shirt_web.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Products",
        description = "Operations related to product management"
)
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREATE
    @Operation(
            summary = "Create a new product",
            description = "Creates a new product and returns the created resource."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Product created successfully",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ProductResponseDTO.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid product data",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )
            )
    })
    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(
            @Valid @RequestBody ProductRequestDTO dto
    ) {

        ProductResponseDTO product = productService.create(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(product);
    }

    // GET ALL PRODUCTS - SEARCH + FILTER + PAGINATION
    @Operation(
            summary = "List products",
            description = """
                    Returns products with optional search by name,
                    optional filtering by category and pagination.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Products retrieved successfully"
            )
    })
    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAll(

            @Parameter(
                    description = "Search products by name",
                    example = "Camiseta"
            )
            @RequestParam(required = false)
            String name,

            @Parameter(
                    description = "Filter products by category ID",
                    example = "1"
            )
            @RequestParam(required = false)
            Long categoryId,

            @Parameter(
                    description = "Page number. Starts at 0.",
                    example = "0"
            )
            @RequestParam(defaultValue = "0")
            int page,

            @Parameter(
                    description = "Number of products returned per page",
                    example = "10"
            )
            @RequestParam(defaultValue = "10")
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        Page<ProductResponseDTO> products =
                productService.findAll(
                        name,
                        categoryId,
                        pageable
                );

        return ResponseEntity.ok(products);
    }

    // GET BY ID
    @Operation(
            summary = "Find product by ID",
            description = "Returns a product using its unique identifier."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(

            @Parameter(
                    description = "Unique identifier of the product",
                    required = true,
                    example = "1"
            )
            @PathVariable Long id
    ) {

        ProductResponseDTO product =
                productService.findById(id);

        return ResponseEntity.ok(product);
    }

    // UPDATE PRODUCT
    @Operation(
            summary = "Update a product",
            description = "Updates an existing product using its unique identifier."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid product data",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(

            @PathVariable Long id,

            @Valid @RequestBody ProductRequestDTO dto
    ) {

        ProductResponseDTO updatedProduct =
                productService.update(id, dto);

        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE PRODUCT
    @Operation(
            summary = "Delete a product",
            description = "Deletes an existing product using its unique identifier."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Product deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(

            @Parameter(
                    description = "Unique identifier of the product",
                    required = true,
                    example = "1"
            )
            @PathVariable Long id
    ) {

        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}