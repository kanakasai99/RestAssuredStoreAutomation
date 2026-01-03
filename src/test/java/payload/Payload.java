package payload;

import java.util.Random;

import com.github.javafaker.Faker;

import pojo.*;


public class Payload {

        private static final Faker faker=new Faker();
        private static final String categories[]= {"electronics", "furniture", "clothing", "books", "beauty"};

        private static final Random random=new Random();


        //Product
        public static Product productPayload()
        {
            String title=faker.commerce().productName();
            double price=Double.parseDouble(faker.commerce().price());
            String description=faker.lorem().sentence();
            String imageUrl="https://i.pravatar.cc/100";
            String category=categories[random.nextInt(categories.length)];

            return new Product(title, price, description, imageUrl, category);
        }


        //Cart



        //User
        public static User userPayload()
        {
            //name
            String firstname=faker.name().firstName();
            String lastname=faker.name().lastName();

            Name name=new Name(firstname,lastname);

            //location
            String lat=faker.address().latitude();
            String lng=faker.address().longitude();

            Geolocation location=new Geolocation(lat,lng);


            //Address

            String city=faker.address().city();
            String street=faker.address().streetName();
            int number=random.nextInt(100);
            String zipcode=faker.address().zipCode();
            Address address=new Address(city,street,number,zipcode,location);


            //User
            String email=faker.internet().emailAddress();
            String username=faker.name().username();
            String password=faker.internet().password();
            String phonenumber=faker.phoneNumber().cellPhone();

            User user=new User(email,username,password,name,address,phonenumber);

            return user;

        }



        //Login
    }


