package com.grocery.config;

import com.grocery.entity.Category;
import com.grocery.entity.Product;
import com.grocery.entity.Role;
import com.grocery.entity.User;
import com.grocery.repository.CategoryRepository;
import com.grocery.repository.ProductRepository;
import com.grocery.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CategoryRepository categoryRepository,
                           ProductRepository productRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Seed Users
        if (userRepository.count() == 0) {
            // Seed Admin User
            User admin = User.builder()
                    .name("Admin User")
                    .email("admin@grocery.com")
                    .password(passwordEncoder.encode("admin"))
                    .phone("+91 99999 88888")
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);

            // Seed Regular User
            User customer = User.builder()
                    .name("Alex Mercer")
                    .email("alex@grocery.com")
                    .password(passwordEncoder.encode("password"))
                    .phone("+91 98765 43210")
                    .role(Role.USER)
                    .build();
            userRepository.save(customer);

            System.out.println("Seeded default users (admin@grocery.com / admin, alex@grocery.com / password)");
        }

        // 2. Seed Categories
        if (categoryRepository.count() == 0) {
            List<Category> categories = List.of(
                    Category.builder()
                            .id("fruits-veg")
                            .name("Fruits & Vegetables")
                            .image("https://images.unsplash.com/photo-1610348725531-843dff163e2c?auto=format&fit=crop&q=80&w=200")
                            .count(24)
                            .build(),
                    Category.builder()
                            .id("dairy-bread")
                            .name("Dairy, Bread & Eggs")
                            .image("https://images.unsplash.com/photo-1563636619-e9143da7973b?auto=format&fit=crop&q=80&w=200")
                            .count(18)
                            .build(),
                    Category.builder()
                            .id("snacks-munchies")
                            .name("Snacks & Munchies")
                            .image("https://images.unsplash.com/photo-1566478989037-eec170784d0b?auto=format&fit=crop&q=80&w=200")
                            .count(32)
                            .build(),
                    Category.builder()
                            .id("beverages")
                            .name("Beverages")
                            .image("https://images.unsplash.com/photo-1622483767028-3f66f32aef97?auto=format&fit=crop&q=80&w=200")
                            .count(15)
                            .build(),
                    Category.builder()
                            .id("bakery-biscuits")
                            .name("Bakery & Biscuits")
                            .image("https://images.unsplash.com/photo-1509440159596-0249088772ff?auto=format&fit=crop&q=80&w=200")
                            .count(12)
                            .build(),
                    Category.builder()
                            .id("cooking-essentials")
                            .name("Cooking Essentials")
                            .image("https://images.unsplash.com/photo-1474979266404-7eaacbcd87c5?auto=format&fit=crop&q=80&w=200")
                            .count(20)
                            .build()
            );
            categoryRepository.saveAll(categories);
            System.out.println("Seeded default categories");
        }

        // 3. Seed Products
        if (productRepository.count() == 0) {
            List<Product> products = List.of(
                    Product.builder()
                            .id("1")
                            .name("Fresh Guava")
                            .category("fruits-veg")
                            .price(34.0)
                            .oldPrice(39.0)
                            .discount(12.0)
                            .weight("500 g")
                            .rating(4.5)
                            .reviewsCount(342)
                            .image("https://images.unsplash.com/photo-1534482421-64566f976cfa?auto=format&fit=crop&q=80&w=600")
                            .images(new ArrayList<>(List.of("https://images.unsplash.com/photo-1534482421-64566f976cfa?auto=format&fit=crop&q=80&w=600")))
                            .inStock(true)
                            .description("Sweet, pinkish/white pulp guavas, perfect for a refreshing snack or salad.")
                            .eta("9-12 mins")
                            .build(),
                    Product.builder()
                            .id("2")
                            .name("Fresh Green Grapes")
                            .category("fruits-veg")
                            .price(58.0)
                            .oldPrice(72.0)
                            .discount(19.0)
                            .weight("500 g")
                            .rating(4.6)
                            .reviewsCount(512)
                            .image("https://images.unsplash.com/photo-1537640538966-79f369143f8f?auto=format&fit=crop&q=80&w=600")
                            .images(new ArrayList<>(List.of("https://images.unsplash.com/photo-1537640538966-79f369143f8f?auto=format&fit=crop&q=80&w=600")))
                            .inStock(true)
                            .description("Sweet and juicy seedless green grapes. Loaded with vitamins.")
                            .eta("10 mins")
                            .build(),
                    Product.builder()
                            .id("3")
                            .name("Organic Red Onion")
                            .category("fruits-veg")
                            .price(44.0)
                            .oldPrice(49.0)
                            .discount(10.0)
                            .weight("1 kg")
                            .rating(4.2)
                            .reviewsCount(1240)
                            .image("https://images.unsplash.com/photo-1508747702-f520acf9b3be?auto=format&fit=crop&q=80&w=600")
                            .images(new ArrayList<>(List.of("https://images.unsplash.com/photo-1508747702-f520acf9b3be?auto=format&fit=crop&q=80&w=600")))
                            .inStock(true)
                            .description("Crisp and pungent red onions sourced from organic certified farms.")
                            .eta("11 mins")
                            .build(),
                    Product.builder()
                            .id("4")
                            .name("Organic Banana (Robusta)")
                            .category("fruits-veg")
                            .price(35.0)
                            .oldPrice(39.0)
                            .discount(10.0)
                            .weight("6 pcs")
                            .rating(4.8)
                            .reviewsCount(2315)
                            .image("https://images.unsplash.com/photo-1571771894821-ce9b6c11b08e?auto=format&fit=crop&q=80&w=600")
                            .images(new ArrayList<>(List.of("https://images.unsplash.com/photo-1571771894821-ce9b6c11b08e?auto=format&fit=crop&q=80&w=600")))
                            .inStock(true)
                            .description("Robusta bananas, freshly harvested, chemical-free.")
                            .eta("8 mins")
                            .build(),
                    Product.builder()
                            .id("5")
                            .name("Amul Table Butter")
                            .category("dairy-bread")
                            .price(56.0)
                            .oldPrice(59.0)
                            .discount(5.0)
                            .weight("100 g")
                            .rating(4.9)
                            .reviewsCount(4890)
                            .image("https://images.unsplash.com/photo-1589985270826-4b7bb135bc9d?auto=format&fit=crop&q=80&w=600")
                            .images(new ArrayList<>(List.of("https://images.unsplash.com/photo-1589985270826-4b7bb135bc9d?auto=format&fit=crop&q=80&w=600")))
                            .inStock(true)
                            .description("Classic salted table butter from Amul. Utterly butterly delicious!")
                            .eta("8 mins")
                            .build(),
                    Product.builder()
                            .id("6")
                            .name("Premium Toned Milk")
                            .category("dairy-bread")
                            .price(66.0)
                            .oldPrice(68.0)
                            .discount(3.0)
                            .weight("1 L")
                            .rating(4.7)
                            .reviewsCount(3822)
                            .image("https://images.unsplash.com/photo-1563636619-e9143da7973b?auto=format&fit=crop&q=80&w=600")
                            .images(new ArrayList<>(List.of("https://images.unsplash.com/photo-1563636619-e9143da7973b?auto=format&fit=crop&q=80&w=600")))
                            .inStock(true)
                            .description("Pasteurized toned milk, rich in calcium and essential vitamins.")
                            .eta("9 mins")
                            .build(),
                    Product.builder()
                            .id("7")
                            .name("Fresh Egg Tray (White)")
                            .category("dairy-bread")
                            .price(48.0)
                            .oldPrice(55.0)
                            .discount(12.0)
                            .weight("6 pcs")
                            .rating(4.6)
                            .reviewsCount(1540)
                            .image("https://images.unsplash.com/photo-1516448424440-9dbca97779c1?auto=format&fit=crop&q=80&w=600")
                            .images(new ArrayList<>(List.of("https://images.unsplash.com/photo-1516448424440-9dbca97779c1?auto=format&fit=crop&q=80&w=600")))
                            .inStock(true)
                            .description("High-quality table eggs, farm-fresh and source of high protein.")
                            .eta("9 mins")
                            .build(),
                    Product.builder()
                            .id("8")
                            .name("Whole Wheat Brown Bread")
                            .category("dairy-bread")
                            .price(45.0)
                            .oldPrice(50.0)
                            .discount(10.0)
                            .weight("400 g")
                            .rating(4.4)
                            .reviewsCount(920)
                            .image("https://images.unsplash.com/photo-1509440159596-0249088772ff?auto=format&fit=crop&q=80&w=600")
                            .images(new ArrayList<>(List.of("https://images.unsplash.com/photo-1509440159596-0249088772ff?auto=format&fit=crop&q=80&w=600")))
                            .inStock(true)
                            .description("Soft and healthy whole wheat bread slices. Baked fresh daily.")
                            .eta("10 mins")
                            .build(),
                    Product.builder()
                            .id("9")
                            .name("India Magic Masala Chips")
                            .category("snacks-munchies")
                            .price(20.0)
                            .oldPrice(20.0)
                            .discount(0.0)
                            .weight("50 g")
                            .rating(4.8)
                            .reviewsCount(8900)
                            .image("https://images.unsplash.com/photo-1566478989037-eec170784d0b?auto=format&fit=crop&q=80&w=600")
                            .images(new ArrayList<>(List.of("https://images.unsplash.com/photo-1566478989037-eec170784d0b?auto=format&fit=crop&q=80&w=600")))
                            .inStock(true)
                            .description("Spiced potato chips with classic Indian flavors.")
                            .eta("8 mins")
                            .build(),
                    Product.builder()
                            .id("10")
                            .name("Coca Cola Can")
                            .category("beverages")
                            .price(38.0)
                            .oldPrice(40.0)
                            .discount(5.0)
                            .weight("330 ml")
                            .rating(4.7)
                            .reviewsCount(4210)
                            .image("https://images.unsplash.com/photo-1622483767028-3f66f32aef97?auto=format&fit=crop&q=80&w=600")
                            .images(new ArrayList<>(List.of("https://images.unsplash.com/photo-1622483767028-3f66f32aef97?auto=format&fit=crop&q=80&w=600")))
                            .inStock(true)
                            .description("Carbonated cold soft drink. Serve chilled.")
                            .eta("9 mins")
                            .build()
            );
            productRepository.saveAll(products);
            System.out.println("Seeded default products");
        }
    }
}
