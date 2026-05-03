package com.Sale;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public Sale createSale(Sale sale, User user) {

        if (user.getRole() == Role.ROLE_BRANCH) {

            if (!sale.getBranch().equals(user.getBranch())) {
                throw new RuntimeException("403 - No puedes vender en otra sucursal");
            }
        }

        sale.setCreatedBy(user.getUsername());
        return saleRepository.save(sale);
    }
}
