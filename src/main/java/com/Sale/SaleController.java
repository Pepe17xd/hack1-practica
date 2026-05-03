package com.Sale;

public class SaleController {
  @PostMapping
  public ResponseEntity<?> create(@RequestBody Sale sale,
                               Authentication authentication) {

  User user = (User) authentication.getPrincipal();

  return ResponseEntity.status(201)
            .body(saleService.createSale(sale, user));
  }
}
