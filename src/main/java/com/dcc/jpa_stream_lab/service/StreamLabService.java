package com.dcc.jpa_stream_lab.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcc.jpa_stream_lab.repository.ProductsRepository;
import com.dcc.jpa_stream_lab.repository.RolesRepository;
import com.dcc.jpa_stream_lab.repository.ShoppingcartItemRepository;
import com.dcc.jpa_stream_lab.repository.UsersRepository;
import com.dcc.jpa_stream_lab.models.Product;
import com.dcc.jpa_stream_lab.models.Role;
import com.dcc.jpa_stream_lab.models.ShoppingcartItem;
import com.dcc.jpa_stream_lab.models.User;

@Transactional
@Service
public class StreamLabService {
	
	@Autowired
	private ProductsRepository products;
	@Autowired
	private RolesRepository roles;
	@Autowired
	private UsersRepository users;
	@Autowired
	private ShoppingcartItemRepository shoppingcartitems;

    public long ProblemOne() {
    	// Write a query that returns the number of users in the Users table.
        //HINT: Use .count()
    	return users.findAll().stream().count();
    }
    
    public List<User> ProblemTwo()
    {
        // Write a query that retrieves all the users from the User tables.
    	return users.findAll();

    }

    public List<Product> ProblemThree()
    {
        // Write a query that gets each product where the products price is greater than $150.
    	return products.findAll().stream().filter(p -> p.getPrice() > 150).toList();
    }

    public List<Product> ProblemFour()
    {
        // Write a query that gets each product that CONTAINS an "s" in the products name.
        // Return the list
    	return products.findAll().stream().filter(p-> p.getName().contains(("S"))).toList();
    }

    public List<User> ProblemFive() throws ParseException {
        // Write a query that gets all of the users who registered BEFORE 2016
        // Return the list
        // Research 'java compare dates'
        // You may need to use the helper classes imported above!
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse("2016-01-01");

        return users.findAll().stream().filter(p-> p.getRegistrationDate().before(d1)).toList();

    }

    public List<User> ProblemSix() throws ParseException {
        // Write a query that gets all of the users who registered AFTER 2016 and BEFORE 2018
        // Return the list
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse("2016-01-01");
        Date d2 = sdf.parse("2018-01-01");

        return users.findAll().stream().filter(p-> p.getRegistrationDate().before(d2)&&p.getRegistrationDate().after(d1)).toList();
    }

    // <><><><><><><><> R Actions (Read) with Foreign Keys <><><><><><><><><>

    public List<User> ProblemSeven()
    {
        // Write a query that retrieves all of the users who are assigned to the role of Customer.
    	Role customerRole = roles.findAll().stream().filter(r -> r.getName().equals("Customer")).findFirst().orElse(null);
    	List<User> customers = users.findAll().stream().filter(u -> u.getRoles().contains(customerRole)).toList();

    	return customers;
    }

    public List<Product> ProblemEight()
    {
        // Write a query that retrieves all of the products in the shopping cart of the user who has the email "afton@gmail.com".
        // Return the list
      User afton = users.findAll().stream().filter(u-> u.getEmail().equals("afton@gmail.com")).findFirst().orElse(null);
      List<ShoppingcartItem> aftoncart =afton.getShoppingcartItems();
      List<Product> aftonproducts =aftoncart.stream().map(p-> p.getProduct()).toList();

    	return aftonproducts;
    }

    public int ProblemNine()
    {
        // Write a query that retrieves all of the products in the shopping cart of the user who has the email "oda@gmail.com" and returns the sum of all of the products prices.
    	// Remember to break the problem down and take it one step at a time!
        User oda = users.findAll().stream().filter(u-> u.getEmail().equals("oda@gmail.com")).findFirst().orElse(null);
        List<ShoppingcartItem> odacart =oda.getShoppingcartItems();
        List<Product> odaproducts =odacart.stream().map(p-> p.getProduct()).toList();
        List<Integer> pricesum = odaproducts.stream().map(s-> s.getPrice()).toList();
        int sum=0;
        for (Integer i : pricesum)
            sum += i;
    	return sum;

    }

    public List<Product> ProblemTen()
    {
        // Write a query that retrieves all of the products in the shopping cart of users who have the role of "Employee".
    	// Return the list
        Role employeeRole = roles.findAll().stream().filter(r -> r.getName().equals("Employee")).findFirst().orElse(null);
        List<User> employee = users.findAll().stream().filter(u -> u.getRoles().contains(employeeRole)).toList();
        List<List<ShoppingcartItem>> employeecart =employee.stream().map(e-> e.getShoppingcartItems()).toList();
        List<ShoppingcartItem> employeeproducts =employeecart.stream().flatMap(p-> p.stream()).collect(Collectors.toList());
        List<Product> allproducts =employeeproducts.stream().map(a-> a.getProduct()).toList();

    	return allproducts;
    }

    // <><><><><><><><> CUD (Create, Update, Delete) Actions <><><><><><><><><>

    // <><> C Actions (Create) <><>

    public User ProblemEleven()
    {
        // Create a new User object and add that user to the Users table.
        User newUser = new User();        
        newUser.setEmail("david@gmail.com");
        newUser.setPassword("DavidsPass123");
        users.save(newUser);
        return newUser;
    }

    public Product ProblemTwelve()
    {
        // Create a new Product object and add that product to the Products table.
        // Return the product
    	Product newProduct = new Product();
        newProduct.setName("Tyrannosaurus Rex Bobblehead");
        newProduct.setDescription("A great looking T-Rex bobblehead that every dinosaur fan would love to have! Fun for your little ones desk or dresser. Perfect for any little dinosaur fan. This apex predator figure stand at 6\" tall.");
        newProduct.setPrice(20);
        products.save(newProduct);

    	return newProduct;

    }

    public List<Role> ProblemThirteen()
    {
        // Add the role of "Customer" to the user we just created in the UserRoles junction table.
    	Role customerRole = roles.findAll().stream().filter(r -> r.getName().equals("Customer")).findFirst().orElse(null);
    	User david = users.findAll().stream().filter(u -> u.getEmail().equals("david@gmail.com")).findFirst().orElse(null);
    	david.addRole(customerRole);
    	return david.getRoles();
    }

    public ShoppingcartItem ProblemFourteen()
    {
    	// Create a new ShoppingCartItem to represent the new product you created being added to the new User you created's shopping cart.
        // Add the product you created to the user we created in the ShoppingCart junction table.
        // Return the ShoppingcartItem
      ShoppingcartItem newItem= new ShoppingcartItem();
        User david = users.findAll().stream().filter(u -> u.getEmail().equals("david@gmail.com")).findFirst().orElse(null);
        newItem.setUser(david);
        Product rex = products.findAll().stream().filter(p-> p.getName().equals("Tyrannosaurus Rex Bobblehead")).findFirst().orElse(null);
        newItem.setProduct(rex);
        newItem.setQuantity(2);
        shoppingcartitems.save(newItem);

    	return newItem;
    	
    }

    // <><> U Actions (Update) <><>

    public User ProblemFifteen()
    {
         //Update the email of the user we created in problem 11 to "mike@gmail.com"
          User user = users.findAll().stream().filter(u -> u.getEmail().equals("david@gmail.com")).findFirst().orElse(null);
          user.setEmail("mike@gmail.com");
          return user;
    }

    public Product ProblemSixteen()
    {
        // Update the price of the product you created to a different value.
        // Return the updated product
        Product rex = products.findAll().stream().filter(p-> p.getName().equals("Tyrannosaurus Rex Bobblehead")).findFirst().orElse(null);
        rex.setPrice(19);
    	return rex;
    }

    public User ProblemSeventeen()
    {
        // Change the role of the user we created to "Employee"
        // HINT: You need to delete the existing role relationship and then create a new UserRole object and add it to the UserRoles table
        Role customerRole = roles.findAll().stream().filter(r -> r.getName().equals("Customer")).findFirst().orElse(null);
        User david = users.findAll().stream().filter(u -> u.getEmail().equals("mike@gmail.com")).findFirst().orElse(null);
        david.removeRole(customerRole);
        Role employeeRole = roles.findAll().stream().filter(r -> r.getName().equals("Employee")).findFirst().orElse(null);
//        User davids = users.findAll().stream().filter(u -> u.getEmail().equals("mike@gmail.com")).findFirst().orElse(null);
        david.addRole(employeeRole);

    	return david;
    }


   public List<Role> ProblemEighteen(){
//Delete the role relationship from the user who has the email "oda@gmail.com"
       Role customerRole = roles.findAll().stream().filter(r -> r.getName().equals("Customer")).findFirst().orElse(null);
       User oda = users.findAll().stream().filter(u -> u.getEmail().equals("oda@gmail.com")).findFirst().orElse(null);
       oda.removeRole(customerRole);
      return oda.getRoles();
}
public int ProblemNineteen(){
//Delete all shopping cart items from the user "oda@gmail.com"
    User oda = users.findAll().stream().filter(u -> u.getEmail().equals("oda@gmail.com")).findFirst().orElse(null);

    List<ShoppingcartItem> odaCart = oda.getShoppingcartItems().stream().toList();

    for (ShoppingcartItem item: odaCart
         ) {
        oda.removeShoppingcartItem(item);
    }
    return 1;
}

public User ProblemTwenty(){
//Delete the "oda@gmail.com" user
    User oda = users.findAll().stream().filter(u -> u.getEmail().equals("oda@gmail.com")).findFirst().orElse(null);
//    List<User> odaCart = oda.getId().stream().toList();
//     oda.remove;


    return null;
}

}
