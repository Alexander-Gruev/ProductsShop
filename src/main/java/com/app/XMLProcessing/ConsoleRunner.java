package com.app.XMLProcessing;

import com.app.XMLProcessing.models.DTOs.categoriesByProductsCount.CategoryInfoRootDto;
import com.app.XMLProcessing.models.DTOs.creation.CategoryCreateRootDto;
import com.app.XMLProcessing.models.DTOs.creation.ProductCreateRootDto;
import com.app.XMLProcessing.models.DTOs.creation.UserCreateRootDto;
import com.app.XMLProcessing.models.DTOs.productsInRange.ProductInRangeRootDto;
import com.app.XMLProcessing.models.DTOs.soldProducts.UserWithProductsSoldRootDto;
import com.app.XMLProcessing.models.DTOs.usersAndProducts.UserWithCountDto;
import com.app.XMLProcessing.services.CategoryService;
import com.app.XMLProcessing.services.ProductService;
import com.app.XMLProcessing.services.UserService;
import com.app.XMLProcessing.util.XMLParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    public static final String RESOURCE_FILE_PATH = "src/main/resources/files/";
    private static final String USERS_FILE_NAME = "users.xml";
    private static final String CATEGORIES_FILE_NAME = "categories.xml";
    private static final String PRODUCTS_FILE_NAME = "products.xml";
    private static final String ASK_USER_FOR_INPUT = "Please enter the exercise number (1-4) or \"end\" to stop the program:";
    private static final String STRING_TO_STOP_PROGRAM = "end";
    private static final String OUTPUT_FOLDER_PATH = "src/main/resources/files/out/";
    private static final String PRODUCTS_IN_RANGE_FILE_NAME = "products-in-range.xml";
    private static final String SOLD_PRODUCTS_FILE_NAME = "users-sold-products.xml";
    private static final String USERS_AND_PRODUCTS_FILE_NAME = "users-and-products.xml";
    private static final String CATEGORIES_BY_PRODUCTS_COUNT_FILE_NAME = "categories-by-products.xml";

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final XMLParser xmlParser;
    private final BufferedReader reader;

    public ConsoleRunner(UserService userService, ProductService productService, CategoryService categoryService, XMLParser xmlParser) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.xmlParser = xmlParser;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        System.out.println(ASK_USER_FOR_INPUT);
        String input = reader.readLine();

        while (!input.equalsIgnoreCase(STRING_TO_STOP_PROGRAM)) {
            int exerciseNum = Integer.parseInt(input);

            switch (exerciseNum) {
                case 1 -> productsInRange();
                case 2 -> soldProducts();
                case 3 -> categoriesByProductsCount();
                case 4 -> usersAndProducts();
            }

            System.out.println(ASK_USER_FOR_INPUT);
            input = reader.readLine();
        }

    }

    private void seedData() throws IOException, JAXBException {
        if (this.userService.getUsersCount() == 0) {
            UserCreateRootDto userCreateRootDto =
                    this.xmlParser.fromFile(RESOURCE_FILE_PATH + USERS_FILE_NAME, UserCreateRootDto.class);

            this.userService.seedUsers(userCreateRootDto.getUserCreateDtos());
        }

        if (this.categoryService.getCategoriesCount() == 0) {
            CategoryCreateRootDto categoryCreateRootDto =
                    this.xmlParser.fromFile(RESOURCE_FILE_PATH + CATEGORIES_FILE_NAME, CategoryCreateRootDto.class);

            this.categoryService.seedCategories(categoryCreateRootDto.getCategoryCreateDtos());
        }

        if (this.productService.getProductsCount() == 0) {
            ProductCreateRootDto productCreateRootDto =
                    this.xmlParser.fromFile(RESOURCE_FILE_PATH + PRODUCTS_FILE_NAME, ProductCreateRootDto.class);

            this.productService.seedProducts(productCreateRootDto.getProductCreateDtos());
        }
    }

    private void usersAndProducts() throws JAXBException, IOException {
        UserWithCountDto userWithCountDto = this.userService.getAllSellersOrderedByProductsSold();

        this.xmlParser.writeToFile(OUTPUT_FOLDER_PATH + USERS_AND_PRODUCTS_FILE_NAME, userWithCountDto);

        printOutputMessage(USERS_AND_PRODUCTS_FILE_NAME);
    }

    private void categoriesByProductsCount() throws IOException, JAXBException {
        CategoryInfoRootDto categoryInfoRootDto = this.categoryService.getInfoAboutAll();

        this.xmlParser.writeToFile(OUTPUT_FOLDER_PATH + CATEGORIES_BY_PRODUCTS_COUNT_FILE_NAME, categoryInfoRootDto);

        printOutputMessage(CATEGORIES_BY_PRODUCTS_COUNT_FILE_NAME);
    }

    private void soldProducts() throws IOException, JAXBException {
        UserWithProductsSoldRootDto userWithProductsSoldRootDto = this.userService.getAllWithProductSold();

        this.xmlParser.writeToFile(OUTPUT_FOLDER_PATH + SOLD_PRODUCTS_FILE_NAME, userWithProductsSoldRootDto);

        printOutputMessage(SOLD_PRODUCTS_FILE_NAME);
    }

    private void productsInRange() throws IOException, JAXBException {
        System.out.println("Please enter the lower price bound:");
        BigDecimal lowerBound = new BigDecimal(reader.readLine());

        System.out.println("Please enter the upper price bound:");
        BigDecimal upperBound = new BigDecimal(reader.readLine());

        ProductInRangeRootDto productInRangeRootDto = this.productService
                .getAllInRange(lowerBound, upperBound);

        this.xmlParser.writeToFile(OUTPUT_FOLDER_PATH + PRODUCTS_IN_RANGE_FILE_NAME, productInRangeRootDto);

        printOutputMessage(PRODUCTS_IN_RANGE_FILE_NAME);
    }

    private void printOutputMessage(String fileName) {
        System.out.printf("Written to file \"%s\" with path: \"%s\".%n",
                fileName, OUTPUT_FOLDER_PATH);
    }
}
